package consoleinterface

import scala.io.StdIn.readLine
import consoleinterface.caloriescouter.CaloriesConsoleOps._
import consoleinterface.caloriescouter.CaloriesOptions

object ConsoleOps {
  def printWelcome(): Unit = {
    println("Welcome to The Ecological Footprint Calculator and Calorie Counter")
  }

  def printOptions() = {
    println("1. FootPrint Options\n2. CaloriesCounter Options\n 3. Save States \n4. LoadStates\n0. Quit")
  }

  def getUserChoice(foodList: List[String], sportList: List[String]): UserChoice = {
    print("Insert Option Number")
    val input = readLine()
    input match {
      case "2" => CaloriesOptions.caloriesCounterOptions(foodList, sportList)
      case "SaveStates" => SaveStates
      case "LoadStates" => LoadStates
      case "AddFood" => {
        printList(foodList, 0)
        val food = getFoodInput(foodList)
        food
      }

      case "Quit" => Quit
      case _ => {
        println("Choice not available")
        printOptions()
        getUserChoice(foodList, sportList)
      }
    }
  }
}
