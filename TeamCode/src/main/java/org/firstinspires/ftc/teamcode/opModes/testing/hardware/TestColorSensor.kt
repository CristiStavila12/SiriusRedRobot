package org.firstinspires.ftc.teamcode.opModes.testing.hardware

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.hardware.rev.RevColorSensorV3
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Config
@TeleOp(name = "Test Color Sensor", group = "[T] Testing Hardware")
class TestColorSensor : OpMode() {

    private lateinit var color: RevColorSensorV3

    companion object {
        @JvmField
        var COLOR_NAME = "COLOR"
        @JvmField
        var GAIN: Float = 300.0f
    }

    override fun onInit() {
        color = hardwareMap.get(RevColorSensorV3::class.java, COLOR_NAME)
    }

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",
            "Press play to start testing"
        )
    }

    override fun onMainLoop() {
        color.gain = GAIN
        Telemetry.addSection(
            "Testing",

            "Distance: ${color.getDistance(DistanceUnit.INCH)}",
            "Red: ${color.normalizedColors.red}",
            "Green: ${color.normalizedColors.green}",
            "Blue: ${color.normalizedColors.blue}",
            "Alpha: ${color.normalizedColors.alpha}"
        )

    }
}