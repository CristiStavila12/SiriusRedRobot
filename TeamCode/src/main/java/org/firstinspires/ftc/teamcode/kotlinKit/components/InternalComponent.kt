package org.firstinspires.ftc.teamcode.kotlinKit.components

abstract class InternalComponent : Component() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    fun internalPostInitLoop() {
        postInitLoop()
        postLoop()
        components.forEach { if(it is InternalComponent) it.internalPostInitLoop() }
    }

    fun internalPostMainLoop() {
        postMainLoop()
        postLoop()
        components.forEach { if(it is InternalComponent) it.internalPostMainLoop() }
    }



    // ---------------------------------------------------------------------------------------------
    // ############################### For inheritance / upstream use ##############################
    // ---------------------------------------------------------------------------------------------

    open fun postInitLoop() {}
    open fun postMainLoop() {}
    open fun postLoop() {}
}