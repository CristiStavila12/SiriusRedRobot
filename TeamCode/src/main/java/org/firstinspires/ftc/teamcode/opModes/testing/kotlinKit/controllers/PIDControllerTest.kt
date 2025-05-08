package org.firstinspires.ftc.teamcode.opModes.testing.kotlinKit.controllers

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.controllers.PIDController
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Config
@Disabled
@Deprecated("TESTED AND WORKING")
@TeleOp(name = "Test PID Controller", group = "[T] Testing KotlinKit Controllers")
class PIDControllerTest : OpMode() {
    companion object {
        @JvmField var targetTicks = 1000
        @JvmField var coeffs = PIDController.Coefficients(
            p = 0.0065,
            i = 0.06,
            d = 0.0,
            f = 0.0
        )
    }

    var error = -1
    val currentTicks
        get () = motor.currentPosition
    var power = 0.0

    var controller = PIDController(
        coeffs
    )

    lateinit var motor: DcMotorEx

    override fun onInit() {
        motor = hardwareMap.get(DcMotorEx::class.java, "outtakeExtension")
        motor.direction = DcMotorSimple.Direction.REVERSE
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    override fun onMainLoop() {
        targetTicks = targetTicks.coerceIn(0, 1000)
        error = targetTicks - currentTicks
        power = controller.calculate(currentTicks, targetTicks)

        motor.power = power

        Telemetry.addData("Power", power)
        Telemetry.addData("Error", error)
        Telemetry.addData("Current Ticks", currentTicks)
        Telemetry.addData("Target Ticks", targetTicks)
    }
}