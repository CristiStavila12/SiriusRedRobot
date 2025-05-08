package org.firstinspires.ftc.teamcode.kotlinKit.components.internal

import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.components.InternalComponent
import org.firstinspires.ftc.teamcode.kotlinKit.routines.Routine

object RoutineManager: InternalComponent() {

    var currentRoutines = mutableListOf<Routine>()
        private set
    val log = mutableListOf<String>()

    var routinesRegistered = 0
        private set
    var routinesSkipped = 0
    val routinesRunning get() = currentRoutines.size

    private val routineWaitlist = mutableListOf<Routine>()

    fun register(routine: Routine) {
        routineWaitlist.add(routine)
        if(!routine.hiddenInLogs) { log += routine.name }

        routinesRegistered ++
    }

    override fun telemetry() : String {
        return "$routinesRunning running; " +
                "$routinesRegistered registered; " +
                "$routinesSkipped skipped"
    }

    override fun init(opMode : OpMode) {
        currentRoutines.clear()
        routineWaitlist.clear()
        log.clear()

        routinesSkipped = 0
        routinesRegistered = 0
    }

    override fun loop() {
        currentRoutines.forEach {
            if(!it.initialized) { it.internalInit() }
            if(it.shouldEnd) { it.internalEnd() }
            it.internalRun()
        }

        currentRoutines = currentRoutines.filter { !it.finished }.toMutableList()

        routineWaitlist.forEach { currentRoutines += it }
        routineWaitlist.clear()
    }
}