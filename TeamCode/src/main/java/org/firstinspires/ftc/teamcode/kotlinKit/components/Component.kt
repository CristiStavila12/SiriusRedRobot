package org.firstinspires.ftc.teamcode.kotlinKit.components

import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.LoopTimer

abstract class Component {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    private var loopTimer = LoopTimer()

    open fun internalInit(opMode: OpMode) {
        init(opMode)
        components.forEach { it.internalInit(opMode) }
    }

    fun internalInitLoop() {
        loopTimer.start()

        initLoop()
        loop()
        components.forEach { it.internalInitLoop()  }

        loopTimer.end()
    }

    fun internalPlay() {
        components.forEach { it.internalPlay() }
        play()
    }

    fun internalMainLoop() {
        loopTimer.start()

        mainLoop()
        loop()
        components.forEach { it.internalMainLoop() }

        loopTimer.end()
    }

    fun internalStop() {
        stop()
        components.forEach { it.internalStop() }
    }



    // ---------------------------------------------------------------------------------------------
    // ############################### For inheritance / upstream use ##############################
    // ---------------------------------------------------------------------------------------------

    open val components: MutableList<Component> = mutableListOf()
    val loopTime
        get() = loopTimer.loopDurationMs

    open fun debugTelemetry(){

    }

    open fun init(opMode: OpMode) {}
    open fun initLoop() {}
    open fun play() {}
    open fun mainLoop() {}
    open fun stop() {}

    /**
     * Holds common logic that will be run **both** in the init loop and the main loop, after
     * the dedicated [initLoop] and [mainLoop] functions have been called.
     */
    open fun loop() {}

    /**
     * Returns a string that will be automatically displayed in telemetry, should this component be
     * included in an [OpMode], and should [OpMode.enableComponentsTelemetry] be `true`. If this
     * component is instead a nested component, and [OpMode.enableNestedComponentsTelemetry] is
     * `true`, then its telemetry will be still be displayed, underneath the [telemetry] of the parent component.
     */
    open fun telemetry(): String { return "No telemetry available" }

}