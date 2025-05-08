package org.firstinspires.ftc.teamcode.kotlinKit

import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.KeyBind
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.gamepad.Button
import org.firstinspires.ftc.teamcode.kotlinKit.components.ConfigurableComponent
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry

class PreMatchOpMode(
    switchAlliance: KeyBind = KeyBind.OPTIONS_1,
    switchSide: KeyBind = KeyBind.SHARE_1,
) : OpMode() {

    private fun KeyBind.toButton(): Button {
        return when(this) {
            KeyBind.DPAD_UP_1 -> Button { gamepad1.dpad_up }
            KeyBind.DPAD_DOWN_1 -> Button { gamepad1.dpad_down }
            KeyBind.DPAD_LEFT_1 -> Button { gamepad1.dpad_left }
            KeyBind.DPAD_RIGHT_1 -> Button { gamepad1.dpad_right }
            KeyBind.CIRCLE_1 -> Button { gamepad1.circle }
            KeyBind.CROSS_1 -> Button { gamepad1.cross }
            KeyBind.SQUARE_1 -> Button { gamepad1.square }
            KeyBind.TRIANGLE_1 -> Button { gamepad1.triangle }
            KeyBind.A_1 -> Button { gamepad1.a }
            KeyBind.B_1 -> Button { gamepad1.b }
            KeyBind.X_1 -> Button { gamepad1.x }
            KeyBind.Y_1 -> Button { gamepad1.y }
            KeyBind.LEFT_BUMPER_1 -> Button { gamepad1.left_bumper }
            KeyBind.RIGHT_BUMPER_1 -> Button { gamepad1.right_bumper }
            KeyBind.OPTIONS_1 -> Button { gamepad1.options }
            KeyBind.SHARE_1 -> Button { gamepad1.share }
            KeyBind.PS_1 -> Button { gamepad1.ps }
            KeyBind.TOUCHPAD_1 -> Button { gamepad1.touchpad }
            KeyBind.LEFT_STICK_BUTTON_1 -> Button { gamepad1.left_stick_button }
            KeyBind.RIGHT_STICK_BUTTON_1 -> Button { gamepad1.right_stick_button }

            KeyBind.DPAD_UP_2 -> Button { gamepad2.dpad_up }
            KeyBind.DPAD_DOWN_2 -> Button { gamepad2.dpad_down }
            KeyBind.DPAD_LEFT_2 -> Button { gamepad2.dpad_left }
            KeyBind.DPAD_RIGHT_2 -> Button { gamepad2.dpad_right }
            KeyBind.CIRCLE_2 -> Button { gamepad2.circle }
            KeyBind.CROSS_2 -> Button { gamepad2.cross }
            KeyBind.SQUARE_2 -> Button { gamepad2.square }
            KeyBind.TRIANGLE_2 -> Button { gamepad2.triangle }
            KeyBind.A_2 -> Button { gamepad2.a }
            KeyBind.B_2 -> Button { gamepad2.b }
            KeyBind.X_2 -> Button { gamepad2.x }
            KeyBind.Y_2 -> Button { gamepad2.y }
            KeyBind.LEFT_BUMPER_2 -> Button { gamepad2.left_bumper }
            KeyBind.RIGHT_BUMPER_2 -> Button { gamepad2.right_bumper }
            KeyBind.OPTIONS_2 -> Button { gamepad2.options }
            KeyBind.SHARE_2 -> Button { gamepad2.share }
            KeyBind.PS_2 -> Button { gamepad2.ps }
            KeyBind.TOUCHPAD_2 -> Button { gamepad2.touchpad }
            KeyBind.LEFT_STICK_BUTTON_2 -> Button { gamepad2.left_stick_button }
            KeyBind.RIGHT_STICK_BUTTON_2 -> Button { gamepad2.right_stick_button }
        }
    }

    private val switchAlliance = switchAlliance.toButton()
    private val switchSide = switchSide.toButton()

    private inner class ComponentSwitch(
        val button: Button,
        val component: ConfigurableComponent<*>,
    )

    private val switchesForComponents: List<ComponentSwitch> =
        Configurables.map { ComponentSwitch(it.second.toButton(), it.first) }

    private fun saveChanges() {
//        Match.data = MatchData(
//            Match.data[0] as Match.Alliance,
//            Match.data[1] as Match.Side,
//            *switchesForComponents.map { it.component.mode }.toTypedArray()
//        )
    }



    override fun onInit() {
//        if(Match.data.hasCache) { Match.data.loadFromFile() }
//        else {
//            val componentsPreConfigModes = switchesForComponents.map {
//                it.component.mode.javaClass.enumConstants.first()
//            }.toTypedArray()
//            val preConfigData = MatchData(
//                Match.Alliance.RED,
//                Match.Side.RIGHT,
//                *componentsPreConfigModes
//            )
//            Match.data.load(preConfigData)
//            Match.data.cacheToFile()
//        }
    }

    override fun onInitLoop() {
        Telemetry.addSection(
            "PRE-MATCH CONFIGURATION",

            "Press play to start testing"
        )
    }

    override fun onMainLoop() {
        if (switchAlliance.pressed) {
//            Match.data[0] = when (Match.data[0] as Match.Alliance) {
//                Match.Alliance.RED -> Match.Alliance.BLUE
//                Match.Alliance.BLUE -> Match.Alliance.RED
//            }
        }

        if (switchSide.pressed) {
//            Match.data[1] = when (Match.data[1] as Match.Side) {
//                Match.Side.LEFT -> Match.Side.RIGHT
//                Match.Side.RIGHT -> Match.Side.LEFT
//            }
        }

        switchesForComponents.forEach {
            if (it.button.pressed) {
                it.component.toNextLooping()
            }
        }

        val telemetryLines: MutableList<String> = mutableListOf()
//        telemetryLines += "$switchAlliance: Switch alliance (${Match.data[0]})"
//        telemetryLines += "$switchSide: Switch side (${Match.data[1]})"
        switchesForComponents.forEach {
            telemetryLines += "${it.button}: Switch ${it.component::class.simpleName} (${it.component.mode})"
        }

        Telemetry.addSection(
            "PRE-MATCH CONFIGURATION",

            *telemetryLines.toTypedArray()
        )
    }

    override fun onStop() {
        saveChanges()
//        Match.data.cacheToFile()
    }
}