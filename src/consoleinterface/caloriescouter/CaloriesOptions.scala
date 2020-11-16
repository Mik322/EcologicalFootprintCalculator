package consoleinterface.caloriescouter

import scala.io.StdIn.readLine
import consoleinterface._
import main.calorieCounter.caloricstructures.CaloricMaps

object CaloriesOptions {
  def caloriesCounterOptions(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add/Change\n2. Get CaloricInformation's\n3. Get List Of Caloric Activities\n0. Quit")
    val input = readLine()

    input match {
      case "1" => addMenu(caloricMaps)
      case "2" => GetCalories
      case "3" => GetListCaloricActivities
      case "0" => Quit
    }
  }
  def addMenu(caloricMaps: CaloricMaps): UserChoice = {

    println("1. Add Food\n2. Add Drink\n0. Quit")
    val input = readLine()

    val date = DateChoice.getUserDate()

    input match {
      case "1" => {
        val foodList = caloricMaps.foodMap.keys.toList
        CaloriesConsoleOps.printList(foodList, 0)
        val food = CaloriesConsoleOps.getActivityInput(foodList, CaloriesConsoleOps.addFoodChoice, date)
        food
      }
      case "2" => {
        val drinksList = caloricMaps.drinksMap.keys.toList
        CaloriesConsoleOps.printList(drinksList, 0)
        val drink = CaloriesConsoleOps.getActivityInput(drinksList,CaloriesConsoleOps.addDrinkChoice, date)
        drink
      }
      case _ => Quit
    }
  }
}
