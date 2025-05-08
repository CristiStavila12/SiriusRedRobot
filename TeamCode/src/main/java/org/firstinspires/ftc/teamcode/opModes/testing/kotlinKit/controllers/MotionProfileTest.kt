package org.firstinspires.ftc.teamcode.opModes.testing.kotlinKit.controllers

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.controllers.MotionProfile
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Match
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@Disabled
@Deprecated("TESTED AND WORKING")
@TeleOp(name = "Test Motion Profile", group = "[T] Testing KotlinKit Controllers")
class MotionProfileTest : OpMode() {
    val distance = 0.6
    var profile = MotionProfile(
        maxAcceleration = 0.1,
        maxVelocity = 0.1
    )
    var startTime = 0.0
    var endTime = 0.0

    override fun onInit() {
        startTime = Match.now
        profile.init(
            distance
        )
        endTime = Match.now
    }

    override fun onMainLoop() {
        val calculation = profile.getCalculation(distance)
        Telemetry.addSection(
            "Motion Profile Testing",

            "Acceleration Time: ${calculation.accelTime}",
            "Acceleration Distance: ${calculation.accelDistance}",
            "Total Time: ${calculation.totalTime}",
            "Maximum Velocity: ${calculation.actualMaxVelocity}",
            "Time to compute: ${endTime - startTime}"
        )

        Telemetry.dashboard.addData("Acceleration Time", calculation.accelTime)
        Telemetry.dashboard.addData("Acceleration Distance", calculation.accelDistance)
        Telemetry.dashboard.addData("Total Time", calculation.totalTime)
        Telemetry.dashboard.addData("Maximum Velocity", calculation.actualMaxVelocity)
        Telemetry.dashboard.addData("Time to compute", endTime - startTime)
    }
}