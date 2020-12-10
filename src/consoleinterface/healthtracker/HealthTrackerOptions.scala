package consoleinterface.healthtracker

import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface.UserChoice.GoToMainMenu
import consoleinterface.healthtracker.HealthTrackerInformationConsole.healthTrackerInformationMenu
import consoleinterface.healthtracker.options.AddCaloricActivity.AddWaterCup
import consoleinterface.{DateChoice, UserChoice}
import main.Date
import main.footprint.transport.Car
import main.healthTracker.CaloricMaps

import scala.io.StdIn.readLine

object HealthTrackerOptions {
  @scala.annotation.tailrec
  def healthTrackerOptions(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add/Change\n2. Health Tracker Information\n0. Go back")
    val input = readLine()

    input match {
      case "1" => addMenu(caloricMaps)
      case "2" => healthTrackerInformationMenu(caloricMaps)
      case "0" =>
        printOptions()
        GoToMainMenu
      case _ =>
        println("Invalid option please try again")
        healthTrackerOptions(caloricMaps)
    }
  }

  @scala.annotation.tailrec
  def addMenu(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add Food\n2. Add Drink\n3. Add Exercise\n4. Add Sleep\n5. Add Cup of Water(250ml)\n6. Set Goal\n7. Change Body Parameters\n0. Go Back")
    val input = readLine()

    input match {
      case "1" =>
        val date = DateChoice.getUserDate()
        lazy val foodList = caloricMaps.foodMap.keys.toList
        HealthTrackerConsoleOps.printList(foodList, 0)
        val food = HealthTrackerConsoleOps.getActivityInput(foodList, HealthTrackerConsoleOps.addFoodChoice, date)
        food

      case "2" =>
        val date = DateChoice.getUserDate()
        lazy val drinksList = caloricMaps.drinksMap.keys.toList
        HealthTrackerConsoleOps.printList(drinksList, 0)
        val drink = HealthTrackerConsoleOps.getActivityInput(drinksList,HealthTrackerConsoleOps.addDrinkChoice, date)
        drink

      case "3" =>
        val date = DateChoice.getUserDate()
        lazy val exercisesList = caloricMaps.exercisesMap.keys.toList
        HealthTrackerConsoleOps.printList(exercisesList, 0)
        HealthTrackerConsoleOps.getActivityInput(exercisesList, HealthTrackerConsoleOps.addSportChoice, date)
      case "4" => HealthTrackerConsoleOps.getSleep()

      case "5" => AddWaterCup(Date.today)

      case "6" => HealthTrackerConsoleOps.getUserGoal()

      case "7" => BodyChangeMenu.menu(caloricMaps)

      case "0" => healthTrackerOptions(caloricMaps)

      case _ =>
        println("Invalid option please try again")
        addMenu(caloricMaps)
    }
  }
}
