package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface.AddCaloricActivity
import consoleinterface._
import calorieCounter.{AddCaloricActivityOps, CaloricInformationOps}
import calorieCounter.CalorieCounterOps.{calculateBurnedCalories, calculateConsumedCalories}
import calorieCounter.caloricstructures.CaloricMaps
import calorieCounter.caloricstructures.GoalType.KeepWeight


object Application extends App {

  val f = FootPrintState(0, List());
  val c = CalorieCounter(None, List(), KeepWeight);

  val foodMap = FileOperations.loadCaloriesMap("Food.txt")
  val drinksMap = FileOperations.loadCaloriesMap("Drinks.txt")

  val caloricMaps = CaloricMaps(foodMap, drinksMap, None)

  @tailrec
  def main_loop(footPrintState: FootPrintState, calorieCounter: CalorieCounter): Unit = {
    printOptions()
    val userChoice = getUserChoice(caloricMaps)

    userChoice match {
      case Quit => {}

      // Saves the current states to a file
      case SaveStates => {
        FileOperations.saveStates(footPrintState, calorieCounter)
        main_loop(footPrintState, calorieCounter)
      }

      // Loads the states that are in the file States in the project directory
      case LoadStates => {
        val states = FileOperations.loadStates()
        states match {
          case None =>
          case Some(value) => main_loop(value._1, value._2)
        }
      }

      // Adds a caloric activity (Food, Drink or Sport) to the calorie counter
      case activity: AddCaloricActivity => {
        val newCalorieCounter = AddCaloricActivityOps.addCaloricActityToState(activity, calorieCounter, caloricMaps)
        main_loop(footPrintState, newCalorieCounter)
      }

      // Sets the Weight goal
      case SetGoal(goal) => {
        val newCalorieCounter = calorieCounter.copy(goal = goal)
        main_loop(footPrintState, newCalorieCounter)
      }

      // Handles all types of caloric Information requests
      case information: CaloricInformation => {
        CaloricInformationOps.getCaloricInformation(information, calorieCounter)
        main_loop(footPrintState, calorieCounter)
      }
    }
  }

  main_loop(f, c)

}
