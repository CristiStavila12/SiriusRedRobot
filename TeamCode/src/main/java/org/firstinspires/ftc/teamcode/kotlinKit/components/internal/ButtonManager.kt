package org.firstinspires.ftc.teamcode.kotlinKit.components.internal

import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.components.InternalComponent

object ButtonManager : InternalComponent(), MutableList<Button> by mutableListOf() {

    override fun postLoop() {
        for (button in this) { button.update() }
    }

    override fun stop() {
        this.clear()
    }
}