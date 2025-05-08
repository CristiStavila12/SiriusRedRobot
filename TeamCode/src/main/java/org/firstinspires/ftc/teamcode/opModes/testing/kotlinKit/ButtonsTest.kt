package org.firstinspires.ftc.teamcode.opModes.testing.kotlinKit

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.VariableButton
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Disabled
@Deprecated("TESTED AND WORKING")
@TeleOp(name = "Test Buttons", group = "[T] Testing KotlinKit")
class ButtonsTest : OpMode() {

    val button = Button { gamepad1.circle }
    val variableButton = VariableButton { gamepad1.left_trigger }

    var presses = 0
    var relases = 0

    var doublePresses = 0

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",

            "Press play to start testing"
        )
    }

    override fun onMainLoop() {

        if (button.pressed) { presses ++ }
        if (button.released) { relases ++ }
        if (button.doublePressed) { doublePresses ++ }

        Telemetry.addSection(
            "Controls",

            "CIRCLE: Button being tested",
            "LEFT TRIGGER: Variable Button being tested"
        )

        Telemetry.addSection(
            "Testing",

            "Button being hold for: ${button.holdDuration.toInt()} ms",
            "Presses: $presses",
            "Releases: $relases",
            "Double Presses: $doublePresses",
            "Toggled: ${button.toggled}",
            "",
            "Last press ms: ${button.lastPressTime.toInt()}",
            "Last release ms: ${button.lastReleaseTime.toInt()}",
            "",
            "Variable Button value: ${variableButton.value}"
        )
    }

}