package org.firstinspires.ftc.teamcode.kotlinKit.routines

open class Wait(
    override val durationMs: Double,
    override val hiddenInLogs: Boolean = true,
) : TimedRoutine(
    durationMs = durationMs,
    name = "${durationMs.toInt()} ms wait",
    hiddenInLogs = hiddenInLogs,
) {
    constructor(
        durationMs: Int,
        hiddenInLogs: Boolean = true,
    ) : this(
        durationMs = durationMs.toDouble(),
        hiddenInLogs = hiddenInLogs
    )
}