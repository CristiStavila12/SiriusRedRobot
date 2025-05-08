package org.firstinspires.ftc.teamcode.opModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.components.chassis.Chassis
import org.firstinspires.ftc.teamcode.components.scoringSystem.intake.IntakeArm
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

@TeleOp(name = "|> TELEOP <|", group = "[A] Main")
class TeleOp : OpMode(
    Chassis,
    enableMatchTelemetry = true,
    enableRoutineLogTelemetry = true
) {

    val actionButton = Button { gamepad1.left_trigger > 0.2 }
    val toggleElement = Button { gamepad1.square }

    val toggleIntake = Button { gamepad1.right_trigger > 0.2 }
    val collectSample = Button { gamepad1.right_bumper }
    val changeIntakeOrientation = Button { gamepad1.cross }


    override fun onInitLoop() {

    }

    override fun onMainLoop() {
        Chassis.drive(gamepad1)

        if(toggleElement.pressed){
            IntakeArm.Toggle().register();
        }
    }

}