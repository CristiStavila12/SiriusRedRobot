package org.firstinspires.ftc.teamcode.kotlinKit.routines

open class InstantRoutine(
    override var run: () -> Unit,

    override val skipIf: () -> Boolean = { false },

    override val name: String = "Unnamed Instant Routine",
    override val hiddenInLogs: Boolean = (name == "Unnamed Instant Routine"),
) : Routine(
    init = run,

    runWhile = { false },

    skipIf = skipIf,

    name = name,
    hiddenInLogs = hiddenInLogs,
)