package consoleinterface

import scala.io.StdIn.readLine
import consoleinterface.caloriecounter.Calories._

object ConsoleOps {
  def printWelcome(): Unit = {
    println("Welcome to The Ecological Footprint Calculator")
  }

  def printOptions() = {
    println("SaveStates\nLoadStates\nAddFood\nAddSport\nQuit")
  }

  def getUserChoice(foodList: List[String], sportList: List[String]): UserChoice = {
    val input = readLine()
    input match {
      case "SaveStates" => SaveStates
      case "LoadStates" => LoadStates
      case "AddFood" => {
        printFoodOptions(foodList)
        val food = getFoodInput()
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
