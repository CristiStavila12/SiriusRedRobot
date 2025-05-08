package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.hardware

import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.Constants

class BreakBeam(
    private val sensor: AnalogInput
) {
    val interrupted: Boolean
        get() = sensor.voltage > Constants.BREAKBEAM_INTERRUPT_VOLTAGE
}