package consoleinterface.healthtracker

import consoleinterface.healthtracker.options.HealthInformation._
import consoleinterface.DateChoice.getUserDate
import consoleinterface.UserChoice
import consoleinterface.UserChoice._
import consoleinterface.healthtracker.options.HealthInformation
import consoleinterface.healthtracker.HealthTrackerOptions.healthTrackerOptions
import main.Date
import main.healthTracker.CaloricMaps

import scala.io.StdIn.readLine

object HealthTrackerInformationConsole {
  def healthTrackerInformationMenu(caloricMaps : CaloricMaps): UserChoice = {
    println("1. Get total calories in a day\n2. Get list of caloric activities\n3. Get the net calories in the last number of days")
    println("4. See the list of caloric activities in a date range\n5. See your water needs\n6. See your weight evolution\n7. See how you are keeping up to your goal")
    println("8. See the time necessary for you to sleep well\n9. Get your sleep time in a specific date\n10. Get your average sleep between two dates\n11. See your body parameters\n0. Go back")

    readLine() match {
      case "1" => GetCaloriesInDay(getUserDate())
      case "2" => GetListCaloricActivities
      case "3" => caloriesInTheLastNDays()
      case "4" => getListCaloricActivitiesDateRange()
      case "5" => GetWaterNeeds(Date.today())
      case "6" => GetWeightHistory
      case "7" => GetWeightTrack
      case "8" => GetNecessarySleep
      case "9" => GetSleepInDay(getUserDate())
      case "10" => getAverageSleepDateRange()
      case "11" => GetBodyParameters
      case "0" => healthTrackerOptions(caloricMaps)
      case _ => healthTrackerInformationMenu(caloricMaps)
    }
  }

  def getListCaloricActivitiesDateRange(): GetListCaloricActivitiesInDays = {
    println("Start Date")
    val startDate = getUserDate()
    println("End Date")
    val endDate = getUserDate()
    if (startDate>endDate) {
      println("Start Date needs to be previous to End Date,please try again")
      getListCaloricActivitiesDateRange()
    }
    GetListCaloricActivitiesInDays(startDate, endDate)
  }

  def caloriesInTheLastNDays(): GetLastDaysCalories = {
    println("How many days do you want to see?")
    try {
      GetLastDaysCalories(readLine().toInt)
    } catch {
      case e: NumberFormatException => {
        println("Invalid input")
        caloriesInTheLastNDays()
      }
    }
  }
  def getAverageSleepDateRange() : GetAverageSleepInDays = {
    println("Start Date")
    val startDate = getUserDate()
    println("End Date")
    val endDate = getUserDate()
    if (startDate>endDate) {
      println("Start Date needs to be previous to End Date,please try again")
      getAverageSleepDateRange()
    }
    GetAverageSleepInDays(startDate, endDate)
  }
}
