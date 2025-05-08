package org.firstinspires.ftc.teamcode.opModes.testing.kotlinKit.routines

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry
import org.firstinspires.ftc.teamcode.kotlinKit.routines.Wait

@Disabled
@Deprecated("TESTED AND WORKING")
@TeleOp(name = "Test Timed Routines", group = "[T] Testing KotlinKit Routines")
class TimedRoutineTest : OpMode(
    enableRoutineLogTelemetry = true
) {

    private class TestRoutine : Wait(1000.0, hiddenInLogs = false)
    val addRoutine = Button { gamepad1.circle }

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",

            "Press play to start testing"
        )
    }

    override fun onMainLoop() {

        if (addRoutine.pressed) { TestRoutine().register() }

        Telemetry.addSection(
            "Controls",

            "CIRCLE: Add timed routine"
        )
    }
}