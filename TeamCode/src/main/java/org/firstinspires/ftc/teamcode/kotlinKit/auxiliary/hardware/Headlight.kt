package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.hardware

import com.qualcomm.robotcore.hardware.Servo

class Headlight(
    private val pwm: Servo
) {

    var brightness: Double
        get() = pwm.position
        set(value) {
            pwm.position = value
        }
}