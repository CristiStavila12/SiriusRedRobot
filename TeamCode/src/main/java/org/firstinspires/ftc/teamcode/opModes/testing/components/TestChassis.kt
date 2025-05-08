package org.firstinspires.ftc.teamcode.opModes.testing.components

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.components.chassis.Chassis
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode

@TeleOp(name = "Test Chassis", group = "[T] Testing Components")
class TestChassis : OpMode(Chassis){
    override fun onInit() {
    }

    override fun onMainLoop() {
        Chassis.drive(gamepad1)
    }
}