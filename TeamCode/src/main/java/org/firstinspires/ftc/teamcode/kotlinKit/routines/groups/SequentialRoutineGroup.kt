package org.firstinspires.ftc.teamcode.kotlinKit.routines.groups

import org.firstinspires.ftc.teamcode.kotlinKit.routines.Routine

open class SequentialRoutineGroup(
    vararg routines: Routine,

    override val skipIf: () -> Boolean = { false },

    override val name: String = "Unnamed Sequential Routine Group",
    override val hiddenInLogs: Boolean = (name == "Unnamed Sequential Routine Group")

) : Routine(
    skipIf = skipIf,
    name = name,
    hiddenInLogs = hiddenInLogs
) {

    var currentRoutineIndex = 0
        private set

    override var run: () -> Unit = {
        if (currentRoutineIndex < routines.size) {
            if (routines[currentRoutineIndex].finished) {
                currentRoutineIndex ++

                if(currentRoutineIndex < routines.size){
                    routines[currentRoutineIndex].register()
                }
            }
        }
    }

    override var runWhile = { currentRoutineIndex < routines.size }

    override var init = {
        currentRoutineIndex = 0
        if(routines.isNotEmpty()) routines[currentRoutineIndex].register()
    }
}