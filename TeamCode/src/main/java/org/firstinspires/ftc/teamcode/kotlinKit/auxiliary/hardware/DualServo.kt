package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.hardware

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.cachingPosition

class DualServo(
    val leftServo: Servo,
    val rightServo: Servo
) {
    init {
        rightServo.direction = Servo.Direction.REVERSE
    }

    data class DualPos(
        val leftPos: Double,
        val rightPos: Double
    )

    var position: Double
        get() = leftServo.position
        set(value) {
            leftServo.position = value
            rightServo.position = value
        }

    var dualPosition: DualPos
        get() = DualPos(leftServo.position, rightServo.position)
        set(value) {
            leftServo.position = value.leftPos
            rightServo.position = value.rightPos
        }


    var cachingPosition: Double
        get() = leftServo.position
        set(value) {
            leftServo.cachingPosition = value
            rightServo.cachingPosition = value
        }

    var dualCachingPosition: DualPos
        get() = DualPos(leftServo.position, rightServo.position)
        set(value) {
            leftServo.cachingPosition = value.leftPos
            rightServo.cachingPosition = value.rightPos
        }

    var direction: Servo.Direction = Servo.Direction.FORWARD
        set(value) {
            when (value) {
                Servo.Direction.FORWARD -> {
                    leftServo.direction = Servo.Direction.FORWARD
                    rightServo.direction = Servo.Direction.REVERSE
                }

                Servo.Direction.REVERSE -> {
                    leftServo.direction = Servo.Direction.REVERSE
                    rightServo.direction = Servo.Direction.FORWARD
                }
            }
            field = value
        }
}