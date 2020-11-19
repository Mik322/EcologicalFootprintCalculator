package consoleinterface.caloriescouter

import consoleinterface.CaloricInformation
import consoleinterface.caloriescouter.CaloricInformation._
import consoleinterface.DateChoice.getUserDate
import main.Date

import scala.io.StdIn.readLine

object CaloricInformationConsole {
  def caloricInformationMenu(): CaloricInformation = {
    println("1. Get total calories in a day\n2. Get list of caloric activities\n3. Get the net calories in the last number of days")
    println("4. See the list of caloric activities in a date range\n5. See your water needs")

    readLine() match {
      case "1" => GetCaloriesInDay(getUserDate())
      case "2" => GetListCaloricActivities
      case "3" => caloriesInTheLastNDays()
      case "4" => getListCaloricActivitiesDateRange()
      case "5" => GetWaterNeeds(Date.today())
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
