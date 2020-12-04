package consoleinterface.caloriescouter

import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface.caloriescouter.CaloricInformationConsole.caloricInformationMenu
<<<<<<< HEAD
import consoleinterface.{DateChoice, Quit, UserChoice}
import main.healthTracker.caloricstructures.CaloricMaps
=======
import consoleinterface.{DateChoice, GoToMainMenu, Quit, UserChoice}
import main.calorieCounter.caloricstructures.CaloricMaps
>>>>>>> d8f63bd0c53d82e4912a98ce221a44dd78a2eac2

import scala.io.StdIn.readLine

object CaloriesOptions {
  def caloriesCounterOptions(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add/Change\n2. Caloric Information Menu\n0. Go back")
    val input = readLine()

    input match {
      case "1" => addMenu(caloricMaps)
      case "2" => caloricInformationMenu(caloricMaps)
      case "0" =>
        printOptions()
        getUserChoice(caloricMaps)
      case _ =>
        println("Invalid option please try again")
        caloriesCounterOptions(caloricMaps)
    }
  }

  def addMenu(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add Food\n2. Add Drink\n3. Add Exercise\n4. Set Goal\n5. Change Body Parameters Menu\n6.Add Sleep\n0. Quit")
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

      case "5" => BodyChangeMenu.menu(caloricMaps)

      case "6" => CaloriesConsoleOps.getSleep()
      
      case "0" => caloriesCounterOptions(caloricMaps)

      case _ =>
        println("Invalid option please try again")
        addMenu(caloricMaps)
    }
  }
}
