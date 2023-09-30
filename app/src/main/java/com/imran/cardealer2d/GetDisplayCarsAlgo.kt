package com.imran.cardealer2d



import com.imran.cardealer2d.data.CarsList
import com.imran.cardealer2d.helper.Constants.ECONOMY
import com.imran.cardealer2d.helper.Constants.ECONOMY_HIGH_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.ECONOMY_LOW_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.ECONOMY_MID_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.HIGH_END
import com.imran.cardealer2d.helper.Constants.HIGH_END_CARS
import com.imran.cardealer2d.helper.Constants.HIGH_END_CARS_LIMIT
import com.imran.cardealer2d.helper.Constants.HIGH_END_CAR_INDEXES
import com.imran.cardealer2d.helper.Constants.LOW_END
import com.imran.cardealer2d.helper.Constants.LOW_END_CARS
import com.imran.cardealer2d.helper.Constants.LOW_END_CARS_LIMIT
import com.imran.cardealer2d.helper.Constants.LOW_END_CAR_INDEXES
import com.imran.cardealer2d.helper.Constants.MID_END
import com.imran.cardealer2d.helper.Constants.MID_END_CARS
import com.imran.cardealer2d.helper.Constants.MID_END_CARS_LIMIT
import com.imran.cardealer2d.helper.Constants.MID_END_CAR_INDEXES
import com.imran.cardealer2d.helper.Constants.POOR
import com.imran.cardealer2d.helper.Constants.POOR_HIGH_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.POOR_LOW_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.POOR_MID_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.RICH
import com.imran.cardealer2d.helper.Constants.RICH_HIGH_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.RICH_LOW_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.RICH_MID_END_CARS_PERCENTAGE
import com.imran.cardealer2d.helper.Constants.TOTAL_DISPLAY_CARS
import com.imran.cardealer2d.helper.Constants.USER_WORTH
import com.imran.cardealer2d.model.Car
import com.imran.cardealer2d.utils.Utils.convertPercentageToValue
import kotlin.random.Random


/**
 * get list of display cars
 */

fun main(){
   val displayCars = getDisplayCars()
    for (car in displayCars){
        println(car)
    }
    println("Total cars in market ${displayCars.size}")

}

// initialize variables
var totalLowEndCars = 0
var totalMidEndCars = 0
var totalHighEndCars = 0

/**
 * get cars from CarsList.kt
 * filter cars by category
 * get total size of each categories
 * retrieve indexes from getCarsIndexesByCategory
 * populate list of cars that needs to be displayed
 * by adding cars of respective category to display cars by the index from indexes list
 * @return List of Cars
 */
fun getDisplayCars(): List<Car> {
    val carsList = CarsList

    // filter carsList by category
    val lowEndCars = carsList.filter { it.category.lowercase() == LOW_END}
    val midEndCars = carsList.filter { it.category.lowercase() == MID_END}
    val highEndCars = carsList.filter { it.category.lowercase() == HIGH_END}

    totalLowEndCars =  lowEndCars.size
    totalMidEndCars =  midEndCars.size
    totalHighEndCars = highEndCars.size

    val lowEndCarIndexes: ArrayList<Int> =getCarsIndexesByCategory(LOW_END_CARS)[LOW_END_CAR_INDEXES] ?: ArrayList()
    val midEndCarIndexes: ArrayList<Int> = getCarsIndexesByCategory(MID_END_CARS)[MID_END_CAR_INDEXES] ?: ArrayList()
    val highEndCarIndexes: ArrayList<Int> = getCarsIndexesByCategory(HIGH_END_CARS)[HIGH_END_CAR_INDEXES] ?: ArrayList()

    val displayCars = ArrayList<Car>()
    for (lowEndCarIndex in lowEndCarIndexes){
        if (lowEndCarIndex<lowEndCars.size){
            displayCars.add(lowEndCars[lowEndCarIndex])
        }
    }
    for (midEndCarIndex in midEndCarIndexes){
        if (midEndCarIndex<midEndCars.size){
            displayCars.add(midEndCars[midEndCarIndex])
        }
    }
    for (highEndCarIndex in highEndCarIndexes){
        if (highEndCarIndex<highEndCars.size){
            displayCars.add(highEndCars[highEndCarIndex])
        }
    }
    return displayCars
}
/**
 * Here we return the lists of indexes for each categories
 * these indexes will be used to get cars of respective index from cars list
 * each index is randomly selected within the limit of percentage calculated by user's worth
 * @param category example(Low end, Mid end, High end)
 * @return Map<String,ArrayList<Int>> example (key = LOW_END_CAR_INDEXES : value = [2,5,8,13,16])
 */
fun getCarsIndexesByCategory(category:String): Map<String,ArrayList<Int>> {

    when (category) {
        LOW_END_CARS -> {
            val lowLimit = getLimitByUsersWorth()[LOW_END_CARS_LIMIT]!!
            val lowIndexes = ArrayList<Int>()
            for (totalDisplayCar in 0..TOTAL_DISPLAY_CARS) {
                if (totalDisplayCar < lowLimit) {
                    val randomLowEndCarIndex = Random.nextInt(totalLowEndCars + 1)
                    lowIndexes.add(randomLowEndCarIndex)
                }
            }
            return mapOf(
                LOW_END_CAR_INDEXES to lowIndexes
            )
        }
        MID_END_CARS -> {
            val midLimit = getLimitByUsersWorth()[MID_END_CARS_LIMIT]!!
            val midIndexes = ArrayList<Int>()
            for (totalDisplayCar in 0..TOTAL_DISPLAY_CARS) {
                if (totalDisplayCar < midLimit) {
                    val randomMidEndCarIndex = Random.nextInt(totalMidEndCars + 1)
                    midIndexes.add(randomMidEndCarIndex)
                }
            }
            return mapOf(
                MID_END_CAR_INDEXES to midIndexes
            )
        }
        HIGH_END_CARS -> {
            val highLimit = getLimitByUsersWorth()[HIGH_END_CARS_LIMIT]!!
            val highIndexes = ArrayList<Int>()
            for (totalDisplayCar in 0..TOTAL_DISPLAY_CARS) {
                if (totalDisplayCar < highLimit) {
                    val randomHighEndCarIndex = Random.nextInt(totalHighEndCars + 1)
                    highIndexes.add(randomHighEndCarIndex)
                }
            }
            return mapOf(
                HIGH_END_CAR_INDEXES to highIndexes
            )
        }
        else -> return emptyMap()
    }
}

/**
 * user will have a worth in game
 * which will be calculated by present user's wallet money
 * @return Map<String, Int> example()
 */
fun getLimitByUsersWorth(): Map<String, Int> {
    when (USER_WORTH) {
        POOR -> {
            return mapOf(
               LOW_END_CARS_LIMIT to convertPercentageToValue(POOR_LOW_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS),
               MID_END_CARS_LIMIT to convertPercentageToValue(POOR_MID_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS),
               HIGH_END_CARS_LIMIT to convertPercentageToValue(POOR_HIGH_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS)
            )
        }
        ECONOMY -> {
            return mapOf(
               LOW_END_CARS_LIMIT to convertPercentageToValue(ECONOMY_LOW_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS),
               MID_END_CARS_LIMIT to convertPercentageToValue(ECONOMY_MID_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS),
               HIGH_END_CARS_LIMIT to convertPercentageToValue(ECONOMY_HIGH_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS)
            )
        }
        RICH -> {
            return mapOf(
               LOW_END_CARS_LIMIT to convertPercentageToValue(RICH_LOW_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS),
               MID_END_CARS_LIMIT to convertPercentageToValue(RICH_MID_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS),
               HIGH_END_CARS_LIMIT to convertPercentageToValue(RICH_HIGH_END_CARS_PERCENTAGE, TOTAL_DISPLAY_CARS)
            )
        }
        else -> return emptyMap()
    }
}








