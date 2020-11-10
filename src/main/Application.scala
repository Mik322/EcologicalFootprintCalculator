package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface._
import calorieCounter.{CaloricMaps, CalorieCounterOps, CalorieStateOps}



object Application extends App{

  val f = FootPrintState(0, List());
  val c = CalorieCounter(0, 0, None, List(), List(), List());

  val foodMap = FileOperations.loadCaloriesMap("Food.txt")
  val drinksMap = FileOperations.loadCaloriesMap("Drinks.txt")

  val caloricMaps = CaloricMaps(foodMap, drinksMap, None)

  printOptions()

  @tailrec
  def main_loop(footPrintState: FootPrintState, calorieCounter: CalorieCounter): Unit = {

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

      case AddFood(food, quantity) => {
        val newCalorieCounter = CalorieStateOps.addFoodCalories(calorieCounter, AddFood(food, quantity), foodMap)
        main_loop(footPrintState, newCalorieCounter)
      }

      case AddDrink(drink, quantity) => {
        val newCalorieCounter = CalorieStateOps.addDrinkCalories(calorieCounter, AddDrink(drink, quantity), drinksMap)
        main_loop(footPrintState, newCalorieCounter)
      }
    }
  }

  main_loop(f, c)

}
