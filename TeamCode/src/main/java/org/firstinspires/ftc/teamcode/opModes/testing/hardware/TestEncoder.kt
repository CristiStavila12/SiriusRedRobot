package org.firstinspires.ftc.teamcode.opModes.testing.hardware

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Config
@TeleOp(name = "Test Encoder", group = "[T] Testing Hardware")
class TestEncoder : OpMode() {

    private lateinit var motor: DcMotorEx

    companion object {
        @JvmField
        var ENCODER_NAME = "ENCODER"
    }

    override fun onInit() {
        motor = hardwareMap.get(DcMotorEx::class.java, ENCODER_NAME)
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",
            "Press play to start testing"
        )
    }

    override fun onMainLoop() {
        Telemetry.addSection(
            "Testing",

            "Encoder Reading: ${motor.currentPosition}"
        )

    }
}