package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface.AddCaloricActivity
import consoleinterface._
import calorieCounter.{AddCaloricActivityOps, CaloricImpure, CaloricMaps, CalorieStateOps}
import calorieCounter.caloricstructures.Historic



object Application extends App{

  val f = FootPrintState(0, List());
  val c = CalorieCounter(0, 0, None, Historic(List(), List(), List()));

  val foodMap = FileOperations.loadCaloriesMap("Food.txt")
  val drinksMap = FileOperations.loadCaloriesMap("Drinks.txt")

  val caloricMaps = CaloricMaps(foodMap, drinksMap, None)

  @tailrec
  def main_loop(footPrintState: FootPrintState, calorieCounter: CalorieCounter): Unit = {
    printOptions()
    val userChoice = getUserChoice(caloricMaps)

    userChoice match {
      case Quit => {}

      case SaveStates => {
        FileOperations.saveStates(footPrintState, calorieCounter)
        main_loop(footPrintState, calorieCounter)
      }

      case LoadStates => {
        val states = FileOperations.loadStates()
        states match {
          case None =>
          case Some(value) => main_loop(value._1, value._2)
        }
      }

      case activity: AddCaloricActivity => {
        val newCalorieCounter = AddCaloricActivityOps.addCaloricActityToState(activity, calorieCounter, caloricMaps)
        main_loop(footPrintState, newCalorieCounter)
      }

      case GetCalories => {
        CaloricImpure.printCaloricInformation(calorieCounter)
        main_loop(footPrintState, calorieCounter)
      }
    }
  }

  main_loop(f, c)

}
