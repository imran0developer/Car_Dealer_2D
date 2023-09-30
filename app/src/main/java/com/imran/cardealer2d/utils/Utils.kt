package com.imran.cardealer2d.utils

import kotlin.math.roundToInt

object Utils {
    /***
     * get value of a percentage by total value
     * @param percentage, total
     * @return calculated value of percentage
     * example @param percentage = 50.0 @param total = 100 then @return 50
     *
     */
    fun convertPercentageToValue(percentage: Double, total: Int): Int {
        val doubleValue = percentage / 100 * total
        return doubleValue.roundToInt()
    }

    /***
     * get percentage of a value by total value
     * @param value, total
     * converting value to double to get percentage of type double
     * @return calculated percentage of value of type Double
     * example @param value = 25 @param total = 100 then @return 25.0
     *
     */
    fun convertValueToPercentage(value: Int, total: Int): Double {
        return value.toDouble() /total * 100
    }

    /**
     * created a function to take input of type Int
     * we've buit in function readLine() that takes String
     * @return input value
     */
    fun readInput(): Int {
        return readLine()?.toIntOrNull() ?: 0
    }
}