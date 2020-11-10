package consoleinterface.caloriescouter

import scala.io.StdIn.readLine
import consoleinterface._
import main.calorieCounter.CaloricMaps

object CaloriesOptions {
  def caloriesCounterOptions(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add/Change\n0. Quit")
    val input = readLine()

    input match {
      case "1" => addMenu(caloricMaps)
    }
  }

  def addMenu(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add Food\n2. Add Drink\n0. Quit")
    val input = readLine()

    input match {
      case "1" => {
        val foodList = caloricMaps.foodMap.keys.toList
        CaloriesConsoleOps.printList(foodList, 0)
        val food = CaloriesConsoleOps.getActivityInput(foodList, CaloriesConsoleOps.addFoodChoice)
        food
      }
      case "2" => {
        val drinksList = caloricMaps.drinksMap.keys.toList
        CaloriesConsoleOps.printList(drinksList, 0)
        val drink = CaloriesConsoleOps.getActivityInput(drinksList,CaloriesConsoleOps.addDrinkChoice)
        drink
      }
      case _ => Quit
    }
  }
}
