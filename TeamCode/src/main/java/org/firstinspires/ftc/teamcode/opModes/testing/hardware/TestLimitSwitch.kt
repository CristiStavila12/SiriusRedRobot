package org.firstinspires.ftc.teamcode.opModes.testing.hardware

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DigitalChannel
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Config
@TeleOp(name = "Test Limit Switch", group = "[T] Testing Hardware")
class TestLimitSwitch : OpMode() {

    private lateinit var limit: DigitalChannel

    companion object {
        @JvmField
        var DIGITAL_NAME = "DIGITAL"
    }

    override fun onInit() {
        limit = hardwareMap.get(DigitalChannel::class.java, DIGITAL_NAME)
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

            "Digital Output: ${limit.state}"
        )

    }
}