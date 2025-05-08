package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = 0.0029700576266654343;
        ThreeWheelConstants.strafeTicksToInches = 0.0030115986021466454;
        ThreeWheelConstants.turnTicksToInches = 0.01;
        ThreeWheelConstants.leftY = 19;
        ThreeWheelConstants.rightY = -19;
        ThreeWheelConstants.strafeX = 0;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "frontLeft";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "rearRight";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "frontRight";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.REVERSE;
//        ThreeWheelIMUConstants.IMU_HardwareMapName = "imu";
//        ThreeWheelIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
    }
}




