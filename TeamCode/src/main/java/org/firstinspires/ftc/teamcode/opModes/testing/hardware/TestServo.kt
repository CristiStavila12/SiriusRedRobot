package org.firstinspires.ftc.teamcode.opModes.testing.hardware

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Config
@TeleOp(name = "Test Servo", group = "[T] Testing Hardware")
class TestServo : OpMode() {

    private lateinit var servo: Servo
    val increasePosition = Button { gamepad1.left_bumper }
    val decreasePosition = Button { gamepad1.right_bumper }

    companion object {
        @JvmField
        var STARTING_POSITION = 0.5
        @JvmField
        var INCREMENT = 0.05
        @JvmField
        var SERVO_NAME = "SERVO"
    }

    override fun onInit() {
        servo = hardwareMap.servo[SERVO_NAME]
        servo.position = STARTING_POSITION
    }

    override fun onInitLoop() {
        Telemetry.addSection(
            "Testing",
            "Press play to start testing"
        )
    }

    override fun onMainLoop() {

        if (increasePosition.pressed) {
            servo.position += INCREMENT
        } else if (decreasePosition.pressed) {
            servo.position -= INCREMENT
        }

        Telemetry.addSection(
            "Controls",

            "LEFT BUMPER: Increase position",
            "RIGHT BUMPER: Decrease position"
        )

        Telemetry.addSection(
            "Testing",

            "Servo Position: ${servo.position}"
        )

    }
}