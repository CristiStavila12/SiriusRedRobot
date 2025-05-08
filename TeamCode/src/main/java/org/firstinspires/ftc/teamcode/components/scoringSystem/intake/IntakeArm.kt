package org.firstinspires.ftc.teamcode.components.scoringSystem.intake

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.cachingPosition
import org.firstinspires.ftc.teamcode.kotlinKit.components.StatefulComponent

object IntakeArm : StatefulComponent<IntakeArm.State>({ State.TRANSFER }){
    enum class State {
        LOWERED,
        TRANSFER
    }

    object Config {
        fun getLeftPos(state: State) = when (state) {
            State.LOWERED -> 0.36
            State.TRANSFER -> 0.09
        }
//        fun getRightPos(state: State) = when (state) {
//            State.LOWERED -> 0.44
//            State.TRANSFER -> 0.07
//        }
    }

    lateinit var leftServo: Servo
//    lateinit var rightServo: Servo

    override fun init(opMode: OpMode) {
        leftServo = opMode.hardwareMap.servo.get("intakeArmLeft")
        //rightServo = opMode.hardwareMap.servo.get("intakeArmRight")

        leftServo.position = Config.getLeftPos(currentState)
        //rightServo.position = Config.getRightPos(currentState)
    }

    override fun loop() {
        leftServo.cachingPosition = Config.getLeftPos(targetState)
        //rightServo.cachingPosition = Config.getRightPos(targetState)
    }

    override fun transitionTime(currentState: State, targetState: State): Double {
        return 3000.0 // ms
    }

//    class Lowering : SwitchStateRoutine(State.LOWERED)
//    class Transfer : SwitchStateRoutine(State.TRANSFER)

    class Toggle : SwitchStateRoutine(
        when (currentState) {
            State.LOWERED -> State.TRANSFER
            State.TRANSFER -> State.LOWERED
        }
    )
}