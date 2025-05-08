package org.firstinspires.ftc.teamcode.opModes.testing.kotlinKit.routines.groups

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry
import org.firstinspires.ftc.teamcode.kotlinKit.routines.Wait
import org.firstinspires.ftc.teamcode.kotlinKit.routines.groups.RaceRoutineGroup

@Disabled
@Deprecated("TESTED AND WORKING")
@TeleOp(name = "Test Routine Groups: Race", group = "[T] Testing KotlinKit Routines")
class RaceRoutineGroupTest : OpMode(
    enableRoutineLogTelemetry = true
) {

    class TestRoutine : Wait(1000.0, hiddenInLogs = false)
    class LongTestRoutine : Wait(5000.0, hiddenInLogs = false)

    class TestRoutineGroup : RaceRoutineGroup(
        TestRoutine(),
        LongTestRoutine(),
        LongTestRoutine()
    )

    val addRoutineGroup = Button { gamepad1.circle }

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",

            "Press play to start testing"
        )
    }

    override fun onMainLoop() {

        if (addRoutineGroup.pressed) {
            TestRoutineGroup().register()
        }

        Telemetry.addSection(
            "Controls",

            "CIRCLE: Add routine group"
        )
    }
}