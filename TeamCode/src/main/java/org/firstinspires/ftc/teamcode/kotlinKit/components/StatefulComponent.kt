package org.firstinspires.ftc.teamcode.kotlinKit.components

import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.routines.InstantRoutine
import org.firstinspires.ftc.teamcode.kotlinKit.routines.Routine
import org.firstinspires.ftc.teamcode.kotlinKit.routines.Wait
import org.firstinspires.ftc.teamcode.kotlinKit.routines.groups.RaceRoutineGroup
import org.firstinspires.ftc.teamcode.kotlinKit.routines.groups.SequentialRoutineGroup

abstract class StatefulComponent<T>(
    val defaultState: () -> T,
    val enableRoutineLogging: Boolean = true,
    val enableAdvancedRoutineLogging: Boolean = false
) : Component() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    open var previousState: T = defaultState()
    open var currentState: T = defaultState()
    open var targetState: T = defaultState()
    val isTransitioning
        get() = (currentState != targetState)

    /**
     * Used to add timeouts for state switching.
     * Can either return a `Double` or calculate a timeout based on the current and target states.
     */
    open fun transitionTime(currentState: T, targetState: T) : Double{
        return 0.0
    }

    fun transition(){
        previousState = currentState
        currentState = targetState
    }

    // ---------------------------------------------------------------------------------------------
    // ############################### For inheritance / upstream use ##############################
    // ---------------------------------------------------------------------------------------------

    override fun telemetry() : String {
        if(currentState != targetState) {
            return "$currentState -> $targetState"
        }
        return currentState.toString()
    }

    override fun internalInit(opMode: OpMode) {
        currentState = defaultState()
        targetState = defaultState()
        previousState = defaultState()
        super.internalInit(opMode)
    }

    open inner class SwitchStateRoutine(
        private val newState: T,
        vararg routines: Routine,

        skipIf: () -> Boolean = { newState == currentState }
    ) : SequentialRoutineGroup(

        InstantRoutine(
            { targetState = newState },
            name = if(enableRoutineLogging && enableAdvancedRoutineLogging)
                "${this.javaClass.simpleName}: Transition started" else "Unnamed Instant Routine"
        ),
        *routines,
        InstantRoutine(
            { transition() },
            name = if(enableRoutineLogging && enableAdvancedRoutineLogging)
                "${this.javaClass.simpleName}: Transition ended" else "Unnamed Instant Routine"
        ),

        skipIf = skipIf,
        name = if(enableRoutineLogging)
            "${this.javaClass.simpleName}: ${currentState.toString()} -> $newState"
        else "Unnamed Sequential Routine Group"
    )

    open inner class TimedSwitchStateRoutine(
        private val newState: T,
        vararg routines: Routine,

        skipIf: () -> Boolean = { newState == currentState }
    ) : SequentialRoutineGroup(

        InstantRoutine(
            { targetState = newState },
            name = if(enableRoutineLogging && enableAdvancedRoutineLogging)
                "${this.javaClass.simpleName}: Transition started" else "Unnamed Instant Routine"
        ),
        RaceRoutineGroup(
            Wait(transitionTime(currentState, newState)),
            SequentialRoutineGroup(*routines)
        ),
        InstantRoutine(
            { transition() },
            name = if(enableRoutineLogging && enableAdvancedRoutineLogging)
                "${this.javaClass.simpleName}: Transition ended" else "Unnamed Instant Routine"
        ),

        skipIf = skipIf,
        name = if(enableRoutineLogging)
            "${this.javaClass.simpleName}: ${currentState.toString()} -> $newState"
        else "Unnamed Sequential Routine Group"
    )
}