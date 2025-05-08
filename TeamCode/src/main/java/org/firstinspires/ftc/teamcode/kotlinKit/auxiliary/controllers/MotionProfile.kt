package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.controllers

import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Match
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class MotionProfile(
    val maxVelocity: Double,
    val maxAcceleration: Double,
) {
    enum class State{
        FINISHED,
        WAITING,
        ACCELERATING,
        CRUISING,
        DECELERATING
    }
    var state: State = State.FINISHED
        private set
    var precalculations = HashMap<Double, Precalculation>()
    var startedTimestamp = -1.0

    val isTransitioning: Boolean
        get() = (state == State.ACCELERATING || state == State.CRUISING || state == State.DECELERATING)

    fun init(vararg distances: Double){
        distances.forEach { preCalculate(it) }
    }

    fun reset(){
        startedTimestamp = -1.0
        state = State.WAITING
    }

    fun getCalculation(distance: Double): Precalculation {
        return precalculations.getOrPut(distance) { preCalculate(distance) }
    }

    fun initAllDistances(vararg distances: Double){
        precalculations.clear()
        for (distance in distances){
            for (secondDistance in distances){
                val deltaDistance = abs(secondDistance - distance)
                init(deltaDistance)
            }
        }
    }

    fun calculate(distance: Double): Double {
        if(distance == 0.0) return 0.0
        if(startedTimestamp == -1.0) startedTimestamp = Match.now
        val elapsedTime = (Match.now - startedTimestamp) / 1000.0

        val calculation = getCalculation(distance)

        return if (elapsedTime > calculation.totalTime) {
            state = State.FINISHED
            calculation.distance
        } else if (elapsedTime <= calculation.accelTime) {
            state = State.ACCELERATING
            calculation.calculateAcceleration(elapsedTime)
        } else if (elapsedTime >= calculation.totalTime - calculation.decelTime) {
            state = State.DECELERATING
            calculation.calculateDeceleration(elapsedTime)
        } else {
            state = State.CRUISING
            calculation.calculateCruise(elapsedTime)
        }
    }

    private fun preCalculate(distance: Double): Precalculation {
        val halfDistance = distance / 2
        val accelTime = minOf(maxVelocity / maxAcceleration, sqrt(halfDistance / (0.5 * maxAcceleration)))
        var accelDistance = 0.5 * maxAcceleration * accelTime.pow(2)
        val actualMaxVelocity = maxAcceleration * accelTime

        val decelTime = accelTime
        val decelDistance = accelDistance

        val cruiseDistance = distance - accelDistance - decelDistance
        val cruiseTime = if (cruiseDistance > 0) cruiseDistance / actualMaxVelocity else 0.0

        val totalTime = accelTime + cruiseTime + decelTime

        return Precalculation(
            distance,
            accelTime,
            accelDistance,
            decelTime,
            decelDistance,
            cruiseTime,
            cruiseDistance,
            actualMaxVelocity,
            totalTime
        )
    }

    inner class Precalculation(
        val distance: Double = 0.0,
        val accelTime: Double = 0.0,
        val accelDistance: Double = 0.0,
        val decelTime: Double = 0.0,
        val decelDistance: Double = 0.0,
        val cruiseTime: Double = 0.0,
        val cruiseDistance: Double = 0.0,
        val actualMaxVelocity: Double = 0.0,
        val totalTime: Double = 0.0
    ){
        fun calculateAcceleration(elapsedTime: Double): Double {
            return 0.5 * maxAcceleration * elapsedTime.pow(2)
        }
        fun calculateCruise(elapsedTime: Double): Double {
            val timeInCruise = elapsedTime - accelTime
            return accelDistance + actualMaxVelocity * timeInCruise
        }
        fun calculateDeceleration(elapsedTime: Double): Double {
            val timeInDecel = elapsedTime - (accelTime + cruiseTime)
            return accelDistance + cruiseDistance + (actualMaxVelocity * timeInDecel - 0.5 * maxAcceleration * timeInDecel.pow(2))
        }
    }
}
