package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad

import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.Constants
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.ButtonManager
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Match

open class Button(
    val button: () -> Boolean
): () -> Boolean {

    init {
        register()
    }

    var lastPressTime = 0.0
        private set
    var lastReleaseTime = 0.0
        private set

    var holdDuration = 0.0
        private set

    private var previouslyHeld = false
    var held = false
        private set

    val pressed
        get() = held && !previouslyHeld

    val released
        get() = !held && previouslyHeld

    var toggled = false
        get() {
            if(pressed){
                field = !field
            }
            return field
        }
        private set

    val doublePressed
        get() = pressed && (Match.now - lastReleaseTime <= Constants.DOUBLE_CLICK_INTERVAL)

    open fun update() {
        previouslyHeld = held
        held = button()
        if(pressed) {lastPressTime = Match.now}
        if(released) {lastReleaseTime = Match.now}

        holdDuration = if(held) {
            Match.now - lastPressTime
        } else {
            0.0
        }
    }

    private fun register() {
        ButtonManager += this
    }

    override fun invoke() = held
}