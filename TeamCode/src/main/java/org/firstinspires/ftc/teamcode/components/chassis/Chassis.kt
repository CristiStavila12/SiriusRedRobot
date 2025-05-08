package org.firstinspires.ftc.teamcode.components.chassis

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.cachingPower
import org.firstinspires.ftc.teamcode.kotlinKit.components.Component
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sign

object Chassis : Component() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    lateinit var leftFront: DcMotor
    lateinit var rightFront: DcMotor
    lateinit var leftRear: DcMotor
    lateinit var rightRear: DcMotor
    lateinit var motors: List<DcMotor>

    override fun init(opMode : OpMode) {

        leftFront = opMode.hardwareMap.dcMotor["frontLeft"]
        rightFront = opMode.hardwareMap.dcMotor["frontRight"]
        leftRear = opMode.hardwareMap.dcMotor["rearLeft"]
        rightRear = opMode.hardwareMap.dcMotor["rearRight"]

        rightFront.direction = DcMotorSimple.Direction.REVERSE
        rightRear.direction = DcMotorSimple.Direction.REVERSE

        motors = listOf(
            leftFront,
            leftRear,
            rightFront,
            rightRear
        )

        for (motor in motors) {
            motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        }

        if (false) { // TODO: Unlock for testing
            for (motor in motors) {
                val motorConfigurationType = motor.motorType.clone()
                motorConfigurationType.achieveableMaxRPMFraction = 1.0
                motor.motorType = motorConfigurationType
            }
        }
    }

    fun Double.signedPow(exp: Double): Double = sign(this) * abs(this).pow(exp)

    // ---------------------------------------------------------------------------------------------
    // ######################################### Routines ##########################################
    // ---------------------------------------------------------------------------------------------

    fun drive(
        gamepadXAxis: Double,
        gamepadYAxis: Double,
        gamepadRotationAxis: Double,
        gamepadSlowMotion: Boolean
    ) {
        val multiplier = if (gamepadSlowMotion) 0.5 else 1.0
        var y: Double = gamepadYAxis.signedPow(1.0)
        var x: Double = -1 * gamepadXAxis.signedPow(1.0) * 1.1
        var rx: Double = -gamepadRotationAxis.signedPow(1.0)
        val denominator = max(abs(y) + abs(x) + abs(rx), 1.0)

        leftFront.cachingPower = (y + x + rx) * multiplier / denominator
        leftRear.cachingPower = (y - x + rx) * multiplier / denominator
        rightFront.cachingPower = (y - x - rx) * multiplier / denominator
        rightRear.cachingPower = (y + x - rx) * multiplier / denominator
    }

    fun drive(gamepad: Gamepad){
        drive(
            gamepad.left_stick_x.toDouble(),
            gamepad.left_stick_y.toDouble(),
            gamepad.right_stick_x.toDouble(),
            gamepad.left_bumper
        )
    }
}