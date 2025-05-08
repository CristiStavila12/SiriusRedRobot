package org.firstinspires.ftc.teamcode.opModes.testing.kotlinKit.controllers

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.controllers.MotionProfile
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry
import kotlin.math.abs
import kotlin.math.sign

@Config
//@Disabled
//@Deprecated("TESTED AND WORKING")
@TeleOp(name = "Test Motion Profile on Servo", group = "[T] Testing KotlinKit Controllers")
class MotionProfileExample : OpMode(){
    companion object {
        @JvmField var maxAccel = 4.0
        @JvmField var maxSpeed = 2.5
    }
    lateinit var servo: Servo
    lateinit var profile: MotionProfile

    override fun onInit() {
        profile = MotionProfile(
            maxAcceleration = maxAccel,
            maxVelocity = maxSpeed
        )
        servo = hardwareMap.get(Servo::class.java, "joint")
        profile.init(0.3)
        servo.position = 0.5
    }

    var firstPos = 0.5
    var secondPos = 0.3

    var lastTarget = firstPos
    var target = firstPos

    override fun onMainLoop() {
        if(profile.state == MotionProfile.State.FINISHED){
            if(gamepad1.dpad_up){
                lastTarget = target
                target = firstPos
                profile.reset()
            }else if(gamepad1.dpad_down){
                lastTarget = target
                target = secondPos
                profile.reset()
            }
        }
        if(profile.state == MotionProfile.State.FINISHED){
            servo.position = target
        }else{
            val currentPos = lastTarget + profile.calculate(abs(target - lastTarget)) * sign(target - lastTarget)
            servo.position = currentPos
        }
        Telemetry.addData("MP State", profile.state)
        Telemetry.addData("MP Output", profile.calculate(abs(target - lastTarget)))
        val calculation = profile.getCalculation(abs(target - lastTarget))
        Telemetry.addSection(
            "Motion Profile Testing",
            "DeltaPos ${target - lastTarget}",
            "lastTarget $lastTarget",
            "target $target",
            "Acceleration Time: ${calculation.accelTime}",
            "Acceleration Distance: ${calculation.accelDistance}",
            "Total Time: ${calculation.totalTime}",
            "Maximum Velocity: ${calculation.actualMaxVelocity}",
        )
    }
}