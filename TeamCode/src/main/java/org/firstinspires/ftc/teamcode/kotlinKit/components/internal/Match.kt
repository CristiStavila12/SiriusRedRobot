package org.firstinspires.ftc.teamcode.kotlinKit.components.internal

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.components.InternalComponent

object Match: InternalComponent() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    enum class Alliance {
        RED,
        BLUE
    }

    enum class Side {
        LEFT,
        RIGHT
    }

    private val timer = ElapsedTime()
    private var previousLoopStart = 0.0

    /** Returns the current time in milliseconds (ms) */
    val now get() = timer.milliseconds()
    val seconds get() = timer.seconds()

    /** The total time spent by the OpMode in the loop() method, in milliseconds (ms) */
    var totalLoopTime = 0.0
        private set
    val totalLoopHz get() = if(totalLoopTime > 0.0) 1000 / totalLoopTime else 0.0

    /** The duration of the current match period in seconds */
    var duration = 0
        private set

    /** The time left in the current match period, in seconds */
    val secondsPassed get() = seconds.toInt()
    val secondsLeft get() = duration - seconds.toInt()


    var isAutonomous = false
        private set
    fun setToAuto(){
        isAutonomous = true
    }
    val ended get() = secondsLeft < 0

//    var data: MatchData = MatchData( // TODO: Make default values come from outside the library
//        Alliance.RED,
//        Side.RIGHT,
//    )
//    val alliance: Alliance = data[0] as Alliance
//    val side: Side = data[1] as Side



    // ---------------------------------------------------------------------------------------------
    // ######################################### Overrides #########################################
    // ---------------------------------------------------------------------------------------------

    override fun init(opMode: OpMode) {
   //     if(!data.hasCache) {
   //         hasReadFromCache = true
   //         data.loadFromFile()
   //     }
        timer.reset()
        duration = if (opMode.isAutonomous) 30 else 120
        isAutonomous = opMode.isAutonomous
    }

    override fun play(){
        timer.reset()
    }

    override fun loop() {
        totalLoopTime = now - previousLoopStart
        previousLoopStart = now
    }
}