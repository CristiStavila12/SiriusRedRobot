package org.firstinspires.ftc.teamcode.kotlinKit.components

import org.firstinspires.ftc.teamcode.kotlinKit.OpMode

abstract class SimpleStatefulComponent<T>(
    val defaultState: () -> T
) : Component() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    open var currentState: T = defaultState()

    // ---------------------------------------------------------------------------------------------
    // ############################### For inheritance / upstream use ##############################
    // ---------------------------------------------------------------------------------------------

    override fun internalInit(opMode: OpMode) {
        currentState = defaultState()
        super.internalInit(opMode)
    }
}