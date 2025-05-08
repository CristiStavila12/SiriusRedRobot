package org.firstinspires.ftc.teamcode.kotlinKit.components.internal

import com.qualcomm.hardware.lynx.LynxModule
import org.firstinspires.ftc.teamcode.kotlinKit.OpMode
import org.firstinspires.ftc.teamcode.kotlinKit.components.InternalComponent

object CacheManager: InternalComponent() {

    // ---------------------------------------------------------------------------------------------
    // ####################################### Internal logic ######################################
    // ---------------------------------------------------------------------------------------------

    private lateinit var hubs: List<LynxModule>
    private var loopCount = 0

    private const val LOOPS_BEFORE_CACHING = 5



    // ---------------------------------------------------------------------------------------------
    // ######################################### Overrides #########################################
    // ---------------------------------------------------------------------------------------------\

    override fun init(opMode: OpMode) {
        hubs = opMode.hardwareMap.getAll(LynxModule::class.java)
        hubs.forEach { it.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL }

        loopCount = 0
    }

    override fun loop() {
        loopCount ++

        if(loopCount >= LOOPS_BEFORE_CACHING) {
            hubs.forEach { it.clearBulkCache() }
            loopCount = 0
        }
    }
}