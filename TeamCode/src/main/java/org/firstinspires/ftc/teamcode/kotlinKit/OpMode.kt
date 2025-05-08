package org.firstinspires.ftc.teamcode.kotlinKit

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.ServoImplEx
import org.firstinspires.ftc.teamcode.kotlinKit.components.Component
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.ButtonManager
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.CacheManager
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Match
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.RoutineManager
import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Telemetry
import org.firstinspires.ftc.teamcode.kotlinKit.routines.EmptyRoutine
import org.firstinspires.ftc.teamcode.kotlinKit.routines.Routine

@Config
abstract class OpMode(
    vararg components: Component,
    var initRoutine: Routine = EmptyRoutine(),
    var playRoutine: Routine = EmptyRoutine(),

    var enableMatchTelemetry: Boolean = false,

    var enableComponentsTelemetry: Boolean = true,
    var enableNestedComponentsTelemetry: Boolean = true,
    var enableComponentsLoopTimeTelemetry: Boolean = true,

    var enableLoopTimeTelemetry: Boolean = true,

    var enableRoutineLogTelemetry: Boolean = false,

    var isAutonomous: Boolean = false,
) : OpMode() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    // --------------------------------- FTC SDK OpMode inheritance --------------------------------

    final override fun init() {
        if(isAutonomous){
            Match.setToAuto()
        }
        allComponents = (components + internalComponents).toMutableList()

        allComponents.forEach { it.internalInit(this) }
        this.onInit()

        initRoutine.register()
    }

    final override fun init_loop() {
        allComponents.forEach { it.internalInitLoop() }
        this.onInitLoop()
        internalComponents.forEach { it.internalPostInitLoop()}
    }

    final override fun start() {
        allComponents.forEach { it.internalPlay() }
        this.onPlay()

        playRoutine.register()
    }

    final override fun loop() {
        allComponents.forEach { it.internalMainLoop() }
        this.onMainLoop()
        internalComponents.forEach { it.internalPostMainLoop()}
    }

    final override fun stop() {
        allComponents.forEach { it.internalStop() }
        this.onStop()

        for(motor in hardwareMap.dcMotor){
            motor.power = 0.0
            (motor as DcMotorEx).setMotorDisable()
        }

        for(servo in hardwareMap.servo){
            (servo as ServoImplEx).setPwmDisable()
        }
    }

    // ----------------------------------------- Components ----------------------------------------

    private lateinit var allComponents: MutableList<Component>
    private val internalComponents = mutableListOf(
        Match,
        RoutineManager,
        CacheManager,
        ButtonManager,
        Telemetry,
        // TODO: Add other internal components here
    )



    // ---------------------------------------------------------------------------------------------
    // ############################### For inheritance / upstream use ##############################
    // ---------------------------------------------------------------------------------------------

    open val components: MutableList<Component> = components.toMutableList()

    @Throws(InterruptedException::class) open fun onInit(){}
    @Throws(InterruptedException::class) open fun onInitLoop(){}
    @Throws(InterruptedException::class) open fun onPlay(){}
    @Throws(InterruptedException::class) open fun onMainLoop(){}
    @Throws(InterruptedException::class) open fun onStop(){}
}