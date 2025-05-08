package org.firstinspires.ftc.teamcode.opModes.testing.hardware

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.CRServo
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.cachingPower
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.VariableButton
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Config
@TeleOp(name = "Test CRServo", group = "[T] Testing Hardware")
class TestCRServo : OpMode() {

    private lateinit var crServo: CRServo
    val positivePower = VariableButton { gamepad1.right_trigger }
    val negativePower = VariableButton { gamepad1.left_trigger }

    companion object {
        @JvmField
        var SERVO_NAME = "intakeRollers"
    }

    override fun onInit() {
        crServo = hardwareMap.crservo[SERVO_NAME]
    }

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",

            "Press play to start testing"
        )
    }

    override fun onMainLoop() {
        crServo.cachingPower = positivePower.value - negativePower.value

        Telemetry.addSection(
            "Controls",

            "RIGHT TRIGGER: Increase power",
            "LEFT TRIGGER: Decrease power",
        )

        Telemetry.addSection(
            "Testing",

            "CRServo Power: ${crServo.power}"
        )
    }


}