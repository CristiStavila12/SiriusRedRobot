package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.controllers

import org.firstinspires.ftc.teamcode.kotlinKit.components.internal.Match
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt

class FancyPIDController(var coefficients: (currentValue: Double, targetValue: Double) -> Coefficients) {
    constructor(coefficients: Coefficients) : this({ _, _ -> coefficients })
    constructor(coefficients: (currentValue: Double, targetValue: Double) -> Coefficients, tolerance: Double, targetTolerance: Double) : this(coefficients) {
        this.tolerance = tolerance
        this.targetTolerance = targetTolerance
    }
    constructor(coefficients: Coefficients, tolerance: Double) : this(coefficients) {
        this.tolerance = tolerance
        this.targetTolerance = tolerance
    }
    constructor(coefficients: Coefficients, tolerance: Double, targetTolerance: Double) : this({ _, _ -> coefficients }, tolerance, targetTolerance)
    data class Coefficients(
        @JvmField var p: Double,
        @JvmField var i: Double,
        @JvmField var d: Double,
        @JvmField var startPower: Double,
        @JvmField var maxAccel: Double,
    )
    var tolerance: Double = 25.0
    var targetTolerance: Double = 50.0
    val fTolerance: () -> Double = { 1e-6 }
    val maxIntegral: () -> Double = { 0.1 }
    val maxOutput: () -> Double = { 1.0 }
    var error = 0.0
        private set
    var lastError = 0.0
        private set
    var integralSum = 0.0
        private set
    var derivative = 0.0
        private set

    var targetPosition = 0.0
        private set
    var currentPostion = 0.0
        private set
    var power = 0.0
        private set

    private var lastTime = Match.now

    var maxTime = 0.0

    fun reset() {
        error = 0.0
        lastError = 0.0
        integralSum = 0.0
        derivative = 0.0
        lastTime = Match.now
    }

    fun calculate(currentValue: Int, targetValue: Int, seconds: Double): Double {
        return calculate(currentValue.toDouble(), targetValue.toDouble(), seconds)
    }

    fun calculate(currentValue: Double, targetValue: Double, seconds: Double, coeffs: Coefficients): Double {
        this.coefficients = { _, _ -> coeffs }
        return calculate(currentValue, targetValue, seconds)
    }

    fun calculate(currentValue: Double, targetValue: Double, seconds: Double): Double {
        targetPosition = targetValue
        currentPostion = currentValue

        val coeffs = coefficients(currentValue, targetValue)
        val currentTime = Match.now
        val deltaTime = (currentTime - lastTime) / 1000.0
        lastTime = currentTime

        lastError = error
        error = targetValue - currentValue

        if (abs(error) < tolerance) {
            integralSum += error
        }
        integralSum = integralSum.coerceIn(-maxIntegral(), maxIntegral())

        derivative = if (deltaTime > 1e-6) (error - lastError) / deltaTime else 0.0

        val kP = coeffs.p * max(coeffs.startPower + coeffs.maxAccel * (1 - error), 1.0)
        val kI = coeffs.i * (seconds/maxTime + 1)
        val kD = coeffs.d / (seconds/maxTime + 1).pow(4)


        power = (kP * error + kI * sqrt(integralSum) + kD * derivative).coerceIn(-maxOutput(), maxOutput())

        return power
    }

    val onTarget
        get() = abs(error) < targetTolerance
}
