package consoleinterface.caloriescouter

import consoleinterface.CaloricInformation
import consoleinterface.caloriescouter.CaloricInformation._
import consoleinterface.DateChoice.getUserDate

import scala.io.StdIn.readLine

object CaloricInformationConsole {
  def caloricInformationMenu(): CaloricInformation = {
    println("1. Get total calories\n2. Get list of caloric activities\n3. Get calories in a certain day\n4. Get the net calories in the last number of days")
    println("5. See the list of caloric activities in a date range")

    readLine() match {
      case "1" => GetTotalCalories
      case "2" => GetListCaloricActivities
      case "3" => GetCaloriesInDay(getUserDate())
      case "4" => caloriesInTheLastNDays()
      case "5" =>getListCaloricActivitiesDateRange()
      case _ => caloricInformationMenu()
    }
  }

  def getListCaloricActivitiesDateRange(): GetListCaloricActivitiesInDays = {
    println("Start Date")
    val startDate = getUserDate()
    println("End Date")
    val endDate = getUserDate()
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
}
