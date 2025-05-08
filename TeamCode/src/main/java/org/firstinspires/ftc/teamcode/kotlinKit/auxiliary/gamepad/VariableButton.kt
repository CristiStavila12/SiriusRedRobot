package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad

import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.Constants

class VariableButton(
    val variableButton: () -> Float,
) : Button({ variableButton() > Constants.VARIABLE_BUTTON_PRESS_THRESHOLD }) {

    var value = 0.0
        private set

    override fun update() {
        super.update()
        value = variableButton().toDouble()
    }
}