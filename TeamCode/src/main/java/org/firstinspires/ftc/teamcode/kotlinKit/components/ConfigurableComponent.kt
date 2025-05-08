package org.firstinspires.ftc.teamcode.kotlinKit.components

import org.firstinspires.ftc.teamcode.kotlinKit.Configurables
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode

class ConfigurableComponent<E: Enum<E>>(
    preConfigDefault : E
) : MultiModeComponent<E>({ preConfigDefault }) {

    fun toNextLooping() {
        val modes = mode.javaClass.enumConstants
        val nextIndex = (mode.ordinal + 1) % modes.size
        mode = modes[nextIndex]
    }

    override fun internalInit(opMode : OpMode) {
        val configurableComponents = Configurables.map { it.first }
        val index = configurableComponents.indexOf(this)
//        if (index != -1 && Match.hasReadFromCache) {
//            defaultMode = { Match.data[index + 2] as E }
//        }
        super.internalInit(opMode)
    }
}