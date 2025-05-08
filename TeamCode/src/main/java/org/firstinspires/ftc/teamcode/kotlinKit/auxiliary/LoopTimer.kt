package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary

import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Match

class LoopTimer {
    private var loopStartTimeMs = 0.0
    private var loopEndTimeMs = 0.0

    var loopDurationMs = 0.0
        private set
    var loopFrequencyHz = 0.0
        private set

    fun start(){
        loopStartTimeMs = Match.now
    }
    fun end(){
        loopEndTimeMs = Match.now

        loopDurationMs = loopEndTimeMs - loopStartTimeMs
        loopFrequencyHz = if (loopDurationMs > 0.0) 1000.0 / loopDurationMs else 0.0
    }
}