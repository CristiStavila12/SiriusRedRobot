package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.hardware


import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

class Encoder(
    val motor: DcMotor,
    var direction: DcMotorSimple.Direction = DcMotorSimple.Direction.FORWARD
) {

    var alpha: Double = 1.0
    var beta: Int = 0

    var position: Int = 0
        get() = (((motor.currentPosition + field) * alpha + beta).toInt()) *
                if(direction == motor.direction) 1 else -1
        set(value) {
            field -= motor.currentPosition
            field += value
        }

    init {
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    fun reset() {
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        position = 0
    }

    fun reverse() {
        direction = when(direction) {
            DcMotorSimple.Direction.FORWARD -> DcMotorSimple.Direction.REVERSE
            DcMotorSimple.Direction.REVERSE -> DcMotorSimple.Direction.FORWARD
            else -> DcMotorSimple.Direction.FORWARD
        }
    }
}