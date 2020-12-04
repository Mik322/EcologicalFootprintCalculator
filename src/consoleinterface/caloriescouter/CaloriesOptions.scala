package consoleinterface.caloriescouter

import consoleinterface.caloriescouter.CaloricInformationConsole.caloricInformationMenu
import consoleinterface.{DateChoice, Quit, UserChoice}
import main.healthTracker.caloricstructures.CaloricMaps

import scala.io.StdIn.readLine

object CaloriesOptions {
  def caloriesCounterOptions(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add/Change\n2. Caloric Information Menu\n0. Quit")
    val input = readLine()

    input match {
      case "1" => addMenu(caloricMaps)
      case "2" => caloricInformationMenu()
      case "0" => Quit
    }
  }

  def addMenu(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add Food\n2. Add Drink\n3. Add Exercise\n4. Set Goal\n5. Changed Body Parameters Menu\n0. Quit")
    val input = readLine()

    input match {
      case "1" => {
        val date = DateChoice.getUserDate()
        lazy val foodList = caloricMaps.foodMap.keys.toList
        CaloriesConsoleOps.printList(foodList, 0)
        val food = CaloriesConsoleOps.getActivityInput(foodList, CaloriesConsoleOps.addFoodChoice, date)
        food
      }

      case "2" => {
        val date = DateChoice.getUserDate()
        lazy val drinksList = caloricMaps.drinksMap.keys.toList
        CaloriesConsoleOps.printList(drinksList, 0)
        val drink = CaloriesConsoleOps.getActivityInput(drinksList,CaloriesConsoleOps.addDrinkChoice, date)
        drink
      }

      case "3" => {
        val date = DateChoice.getUserDate()
        lazy val exercisesList = caloricMaps.exercisesMap.keys.toList
        CaloriesConsoleOps.printList(exercisesList, 0)
        CaloriesConsoleOps.getActivityInput(exercisesList, CaloriesConsoleOps.addSportChoice, date)
      }
      case "4" => CaloriesConsoleOps.getUserGoal()

      case "5" => BodyChangeMenu.menu()

      case _ => Quit
    }
  }
}
