package org.firstinspires.ftc.teamcode.kotlinKit.routines.groups

import org.firstinspires.ftc.teamcode.kotlinKit.routines.Routine

open class RaceRoutineGroup(
    vararg routines: Routine,

    override val skipIf: () -> Boolean = { false },

    override val name: String = "Unnamed Race Routine Group",
    override val hiddenInLogs: Boolean = (name == "Unnamed Race Routine Group")

) : Routine(
    skipIf = skipIf,
    name = name,
    hiddenInLogs = hiddenInLogs
) {
    override var runWhile = { !routines.any { it.finished } }

    override var init = {
        if(routines.isNotEmpty()) routines.forEach { it.register() }
    }

    override val end = {
        routines.forEach { it.cancel() }
    }
}