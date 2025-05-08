package org.firstinspires.ftc.teamcode.kotlinKit.components.internal

import com.acmerobotics.dashboard.FtcDashboard
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.components.Component
import org.firstinspires.ftc.teamcode.kotlinKit.components.InternalComponent

object Telemetry : InternalComponent() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    // ----------------------------------------- Telemetry -----------------------------------------

    fun clear(){
        driverStation.clear()
        dashboard.clear()
    }

    fun clearAll(){
        driverStation.clearAll()
        dashboard.clearAll()
    }

    fun addData(caption: String, format: String, vararg args: Any){
        driverStation.addData(caption, format, args)
        dashboard.addData(caption, format, args)
    }

    fun addData(key: String, value: Any){
        driverStation.addData(key, value)
        dashboard.addData(key, value)
    }

    fun addLine(){
        driverStation.addLine()
        dashboard.addLine()
    }

    fun addLine(line: String = ""){
        driverStation.addLine(line)
        dashboard.addLine(line)
    }

    fun addSection(
        title: String,
        vararg lines: String
    ){
        addLine("--- " + title.uppercase() + " ---")
        addLine()
        for(line in lines){
            addLine(line)
        }
        addLine()
    }

    fun update(){
        driverStation.update()
        dashboard.update()
    }

    // ------------------------------------ Automatic Telemetry ------------------------------------

    private fun displayWarnings(){
//        if(!Match.data.hasCache) {
//            addSection(
//                "WARNING",
//
//                "Match data has NEVER been cached",
//                "Please run a PreMatchOpMode to configure the match data"
//            )
//        }
    }

    private fun displayMatchInfo() {
//        addSection("${Match.alliance} Alliance Match",
        addSection("Match",

            "Currently in " + when {
                Match.isAutonomous -> "AUTONOMOUS"
                Match.secondsLeft <= 0 -> "POST-MATCH"
                Match.secondsLeft <= 30 -> "TELEOP: ENDGAME"
                else -> "TELEOP"
            },

            if(Match.ended) "MATCH ENDED (${-Match.secondsLeft}s ago)"
            else "${Match.secondsPassed}s/${Match.duration}; ${Match.secondsLeft}s left",
        )
    }


    private fun displayInitMatchInfo() {
//        addSection("${Match.alliance} Alliance Match",
        addSection("Match",
            "INITIALIZATION",
            "Time Passed: ${Match.secondsPassed}s",
        )
    }

    private fun displayComponent(component: Component, level: Int = 0){
        val componentName = component::class.simpleName.toString()
        var line = ""

        for(i in 1..level) { line += "  " }
        if(level > 0) line += "|-  "

        line += "$componentName "

        if(displayComponentsLoopTime) { line += "(${((component.loopTime) * 100).toInt() / 100.0}ms)" }
        line += ": ${component.telemetry()}"
        addLine(line)

        if(displayNestedComponents) {
            if(component.components.isEmpty()) return
            component.components.forEach { displayComponent(it, level + 1) }
        }
    }


    private fun displayComponents(){
        if(opModeComponents.isEmpty()) return

        addLine("--- COMPONENTS ---")
        addLine()
        opModeComponents.forEach { displayComponent(it) }
        addLine()
    }

    private fun displayLoopTime(){
        addLine("--- LOOP TIME ---")
        addLine()
        addData("Loop Time (ms)", Match.totalLoopTime)
        addData("Loop Frequency (Hz)", 1000.0 / Match.totalLoopTime)
        addLine()
    }

    private fun displayRoutineLog(){
        addLine("--- ROUTINE LOG ---")
        addLine()
        displayComponent(RoutineManager)
        addLine()
        if(RoutineManager.routinesRunning == 0) { addLine("No routines running") }
        else {
            var hiddenRoutines = 0
            addLine("Running routines:")
            for(routine in RoutineManager.currentRoutines){
                if(routine.hiddenInLogs) { hiddenRoutines ++ }
                else {
                    var line = routine.name + " (${(routine.timeSinceRegister / 1000).toInt()}s)"
                    line += if(routine.initialized) " (ACTV)" else ""
                    line += if(routine.finished) " (END)" else ""
                    addLine(line)
                }
            }
            if (hiddenRoutines > 0) { addLine("..and $hiddenRoutines hidden routines") }

        }
        addLine()
        if(RoutineManager.log.isEmpty()) { addLine("No past routines have been logged") }
        else {
            addLine("Routine log:")
            for(routineName in RoutineManager.log){
                addLine(routineName)
            }
        }
    }

    // ----------------------------------------- Variables -----------------------------------------

    lateinit var driverStation: Telemetry
    lateinit var dashboard: Telemetry

    private lateinit var opModeComponents: MutableList<Component>
    private var displayComponents: Boolean = true
    private var displayNestedComponents: Boolean = true
    private var displayComponentsLoopTime: Boolean = true

    private var displayLoopTime: Boolean = true

    private var displayMatchInfo: Boolean = true

    private var displayRoutineLog: Boolean = true



    // ---------------------------------------------------------------------------------------------
    // ######################################### Overrides #########################################
    // ---------------------------------------------------------------------------------------------

    override fun init(opMode: OpMode) {
        driverStation = opMode.telemetry
        dashboard = FtcDashboard.getInstance().telemetry

//        driverStation.msTransmissionInterval = 500
//        dashboard.msTransmissionInterval = 1000

        displayComponents = opMode.enableComponentsTelemetry
        if(displayComponents) {
            opModeComponents = opMode.components

            displayNestedComponents = opMode.enableNestedComponentsTelemetry
            displayComponentsLoopTime = opMode.enableComponentsLoopTimeTelemetry
        }

        displayLoopTime = opMode.enableLoopTimeTelemetry

        displayMatchInfo = opMode.enableMatchTelemetry

        displayRoutineLog = opMode.enableRoutineLogTelemetry
    }

    override fun initLoop() {
        displayWarnings()
        if(displayMatchInfo) { displayInitMatchInfo() } // Display match info as first telemetry
    }

    override fun mainLoop() {
        displayWarnings()
        if(displayMatchInfo) { displayMatchInfo() } // Display match info as first telemetry
    }

    override fun loop() {
    }

    override fun postLoop() {
        if(displayComponents) { displayComponents() }
        if(displayLoopTime) { displayLoopTime() }
        if(displayRoutineLog) { displayRoutineLog() }

        update()
    }
}