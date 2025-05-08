package org.firstinspires.ftc.teamcode.kotlinKit.routines

import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Match
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.RoutineManager

open class Routine(
    protected open var run: () -> Unit = { },

    open var runWhile: () -> Boolean = { true },

    protected open var init: () -> Unit = { },
    protected open val end: () -> Unit = { },

    open val skipIf: () -> Boolean = { false },

    open val name: String = "Unnamed Routine",
    open val hiddenInLogs: Boolean = (name == "Unnamed Routine")

): () -> Unit {

    val validated: Boolean
        get() {
            if(skipIf()) {
                finished = true
                return false
            }
            return true
        }

    var registered = false
        private set
    var registerTime = -1.0
        private set
    val timeSinceRegister: Double
        get() = if (registered) Match.now - registerTime else -1.0

    var initialized = false
        private set
    var initTime = -1.0
        private set


    var finished = false
    var endTime = -1.0
        private set
    open val shouldEnd
        get() = finished || !runWhile()

    open fun internalInit() {
        this.init()
        initTime = Match.now // Only after the routine has been initialized is the time
                                    // at which it was initialized recorded
        initialized = true
    }

    open fun internalRun() {
        this.run()
    }

    open fun internalEnd() {
        this.end()
        endTime = Match.now  // Only after the routine has been stopped is the time
                                    // at which it was stopped recorded
        finished = true
    }

    fun reset() {
        initTime = -1.0
        endTime = -1.0

        initialized = false
        registered = false
        finished = false
    }
    val copy
        get() = Routine(run, runWhile, init, end, skipIf, name, hiddenInLogs)

    fun copyAndModify(
        newRun : () -> Unit = run,
        newRunWhile: () -> Boolean = runWhile,
        newInit: () -> Unit = init,
        newEnd: () -> Unit = end,
        newSkipIf: () -> Boolean = skipIf,
        newName: String = name,
        newHiddenInLogs: Boolean = (newName == "Unnamed Routine")
    ) : Routine {
        return Routine(newRun, newRunWhile, newInit, newEnd, newSkipIf, newName, newHiddenInLogs)
    }

    fun register() {
        if(!finished && registered) { this.cancel() }   // If the routine is already registered, it
                                                        // is cancelled before being re-registered
        if(validated) {
            this.reset()
            registerTime = Match.now
            registered = true
            RoutineManager.register(this)
        }
        else { RoutineManager.routinesSkipped ++ }
    }

    fun cancel() { if(registered) { this.internalEnd() } }

    override fun invoke() { this.register() }
}