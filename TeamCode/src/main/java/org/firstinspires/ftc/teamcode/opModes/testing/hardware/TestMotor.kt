package org.firstinspires.ftc.teamcode.opModes.testing.hardware

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.cachingPower
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.VariableButton
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Config
@TeleOp(name = "Test Motor", group = "[T] Testing Hardware")
class TestMotor : OpMode() {

    private lateinit var motor: DcMotor
    val positivePower = VariableButton { gamepad1.right_trigger }
    val negativePower = VariableButton { gamepad1.left_trigger }

    companion object {
        @JvmField
        var MOTOR_NAME = "leftFront"
    }

    override fun onInit() {
        motor = hardwareMap.dcMotor[MOTOR_NAME]
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",

            "Press play to start testing"
        )
    }

    override fun onMainLoop() {

        motor.cachingPower = positivePower.value - negativePower.value

        Telemetry.addSection(
            "Controls",

            "RIGHT TRIGGER: Increase power",
            "LEFT TRIGGER: Decrease power",
        )

        Telemetry.addSection(
            "Testing",

            "Motor Power: ${motor.cachingPower}"
        )
    }
}