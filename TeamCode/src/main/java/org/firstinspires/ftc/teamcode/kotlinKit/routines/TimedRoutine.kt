package org.firstinspires.ftc.teamcode.kotlinKit.routines

open class TimedRoutine(
    override var run: () -> Unit = { },

    open val durationMs: Double,

    override var runWhile: () -> Boolean = { true },

    override var init: () -> Unit = { },
    override val end: () -> Unit = { },

    override val skipIf: () -> Boolean = { false },

    override val name: String = "Unnamed ${durationMs.toInt()} ms Routine",
    override val hiddenInLogs: Boolean = (name == "Unnamed ${durationMs.toInt()} ms Routine"),
) : Routine(run, runWhile, init, end, skipIf, name, hiddenInLogs) {

    val timedOut get() = timeSinceRegister >= durationMs
    override val shouldEnd get() = timedOut || super.shouldEnd
}