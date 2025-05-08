package org.firstinspires.ftc.teamcode.kotlinKit.routines.groups

import org.firstinspires.ftc.teamcode.kotlinKit.routines.EmptyRoutine
import org.firstinspires.ftc.teamcode.kotlinKit.routines.Routine


open class RepeatedRoutineGroup(
    routine: Routine = EmptyRoutine(),
    count: Int = 1,
    skipIf: () -> Boolean = { false },
    name: String = "Unnamed Repeated Routine Group",
    hiddenInLogs: Boolean = false
) : SequentialRoutineGroup(
    *Array(count) { routine },
    skipIf = skipIf,
    name = name,
    hiddenInLogs = hiddenInLogs
)