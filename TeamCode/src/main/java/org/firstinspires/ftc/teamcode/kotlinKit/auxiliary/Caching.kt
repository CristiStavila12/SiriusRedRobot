package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary

import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import kotlin.math.abs

var DcMotor.cachingPower
    get() = power
    set(value) { if(abs(value - power) > 0.001) { power = value } }

var Servo.cachingPosition
    get() = position
    set(value) { if(abs(value - position) > 0.001) { position = value } }

var CRServo.cachingPower
    get() = power
    set(value) { if(abs(value - power) > 0.001) { power = value } }
