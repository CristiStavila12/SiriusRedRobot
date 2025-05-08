package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.hardware.data.RGBColorThresholds

@Config
object Constants {
    @JvmField var BREAKBEAM_INTERRUPT_VOLTAGE = 0.5
    @JvmField var DOUBLE_CLICK_INTERVAL = 300
    @JvmField var VARIABLE_BUTTON_PRESS_THRESHOLD = 0.2

    @JvmField var RED = RGBColorThresholds(
        0.7, 1.0,
        0.0, 0.8,
        0.0, 0.5,
    )
    @JvmField var BLUE = RGBColorThresholds(
        0.0, 0.5,
        0.0, 0.7,
        0.8, 1.0,
    )
    @JvmField var YELLOW = RGBColorThresholds(
        0.9, 1.0,
        0.9, 1.0,
        0.3, 0.8,
    )

    object Field {
        const val TILE_SIZE = 24.0
        const val TOTAL_WIDTH = TILE_SIZE * 6
    }

    const val DEGREES = Math.PI / 180
}