package org.firstinspires.ftc.teamcode.opModes.testing.kotlinKit.routines

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry
import org.firstinspires.ftc.teamcode.kotlinKit.routines.InstantRoutine

@Disabled
@Deprecated("TESTED AND WORKING")
@TeleOp(name = "Test Routines", group = "[T] Testing KotlinKit Routines")
class RoutineTest : OpMode(
    enableRoutineLogTelemetry = true
) {

    private class TestRoutine : InstantRoutine({}, hiddenInLogs = false)
    val addRoutine = Button { gamepad1.circle }
    var buttonPresses = 0

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",

            "Press play to start testing"
        )
    }

    override fun onMainLoop() {

        if (addRoutine.pressed) {
            TestRoutine().register()
            buttonPresses ++
        }

        Telemetry.addSection(
            "Controls",

            "CIRCLE: Add routine"
        )

        Telemetry.addSection(
            "Testing",

            "$buttonPresses routines that should have been registered",
        )

    }

}