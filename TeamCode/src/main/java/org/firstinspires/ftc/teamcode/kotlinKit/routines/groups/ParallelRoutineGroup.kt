package org.firstinspires.ftc.teamcode.kotlinKit.routines.groups

import org.firstinspires.ftc.teamcode.kotlinKit.routines.Routine

open class ParallelRoutineGroup(
    vararg routines: Routine,

    override val skipIf: () -> Boolean = { false },

    override val name: String = "Unnamed Parallel Routine Group",
    override val hiddenInLogs: Boolean = (name == "Unnamed Parallel Routine Group")

) : Routine(
    skipIf = skipIf,
    name = name,
    hiddenInLogs = hiddenInLogs
) {
    override var runWhile = { routines.any { !it.finished } }

    override var init = {
        if(routines.isNotEmpty()) routines.forEach { it.register() }
    }
}