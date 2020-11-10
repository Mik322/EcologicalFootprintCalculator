package consoleinterface.caloriescouter

import scala.io.StdIn.readLine
import consoleinterface._
import main.calorieCounter.CaloricMaps

object CaloriesOptions {
  def caloriesCounterOptions(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add/Change\n2. Get CaloricInformation's\n3. Get Body Information\n0. Quit")
    val input = readLine()

    input match {
      case "1" => addMenu(caloricMaps)
      case "2" => GetCalories
      case "3" => GetBody
    }
  }

  def addMenu(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Add Body Information\n2. Add Food\n3. Add Drink\n4. Add Exercise\n0. Quit")
    val input = readLine()

    input match {
      case "1" => {
        val body = CaloriesConsoleOps.getBodyInput()
        body
      }
      case "2" => {
        val foodList = caloricMaps.foodMap.keys.toList
        CaloriesConsoleOps.printList(foodList, 0)
        val food = CaloriesConsoleOps.getActivityInput(foodList, CaloriesConsoleOps.addFoodChoice)
        food
      }
      case "3" => {
        val drinksList = caloricMaps.drinksMap.keys.toList
        CaloriesConsoleOps.printList(drinksList, 0)
        val drink = CaloriesConsoleOps.getActivityInput(drinksList,CaloriesConsoleOps.addDrinkChoice)
        drink
      }
      case "4" => {
        val exercisesList = caloricMaps.exercisesMap.keys.toList
        CaloriesConsoleOps.printList(exercisesList,0)
        val exercise = CaloriesConsoleOps.getActivityInput(exercisesList,CaloriesConsoleOps.addExerciseChoice)
        exercise
      }
      case _ => Quit
    }
  }
}
