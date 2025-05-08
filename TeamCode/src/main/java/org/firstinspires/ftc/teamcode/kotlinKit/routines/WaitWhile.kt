package org.firstinspires.ftc.teamcode.kotlinKit.routines

class WaitWhile(
    override var runWhile: () -> Boolean,
    override val name: String = "Unnamed conditional wait",
    override val hiddenInLogs: Boolean = (name == "Unnamed conditional wait"),
) : Routine(
    runWhile = runWhile,
    name = name,
    hiddenInLogs = hiddenInLogs,
)