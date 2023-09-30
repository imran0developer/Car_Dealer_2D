package com.imran.cardealer2d

import com.imran.cardealer2d.helper.Constants.ACCEPTING
import com.imran.cardealer2d.helper.Constants.BUYING
import com.imran.cardealer2d.helper.Constants.FIXED_PERCENTAGE_LIMIT
import com.imran.cardealer2d.helper.Constants.FIXED_PERCENTAGE_START
import com.imran.cardealer2d.utils.Utils.convertPercentageToValue
import com.imran.cardealer2d.utils.Utils.convertValueToPercentage
import com.imran.cardealer2d.utils.Utils.readInput
import kotlin.random.Random


/**
 * Bargaining Algorithm testing file
 */

fun main() {

    println("I'm selling it for 300, Whats your offer offer ?")
    print("My offer is ")
    val input = readInput()
    bargain(input,300, BUYING)
}


fun bargain(offer: Int, price: Int, intent: String){
    val percentage = convertValueToPercentage(offer,price)
    if (percentage < FIXED_PERCENTAGE_LIMIT){
        val finalPricePercentage = Random.nextInt(FIXED_PERCENTAGE_START.toInt(), FIXED_PERCENTAGE_LIMIT.toInt()).toDouble()
        val finalPrice = convertPercentageToValue(finalPricePercentage,price)
        println("final price $finalPrice")
        if (intent == BUYING){
            if (finalPrice<=offer){
                ACCEPTING = true
                print("your offer again : ")
                val input = readInput()
                bargain(input,100, BUYING)
            }else{
                print("You got it")
            }
        }
    }else{
        ACCEPTING = true
        val input = readInput()
        bargain(input,100, BUYING)
    }
}
