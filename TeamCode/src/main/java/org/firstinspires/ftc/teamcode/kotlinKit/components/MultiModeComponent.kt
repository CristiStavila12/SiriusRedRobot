package org.firstinspires.ftc.teamcode.kotlinKit.components

import org.firstinspires.ftc.teamcode.kotlinKit.OpMode

abstract class MultiModeComponent<E : Enum<E>>(
    var defaultMode: () -> E,
) : Component() {
    var mode: E = defaultMode()

    override fun internalInit(opMode : OpMode) {
        mode = defaultMode()
        super.internalInit(opMode)
    }
}