package consoleinterface

import scala.io.StdIn.readLine
import consoleinterface.caloriescouter.CaloriesOptions
import main.calorieCounter.CaloricMaps

object ConsoleOps {
  def printWelcome(): Unit = {
    println("Welcome to The Ecological Footprint Calculator and Calorie Counter")
  }

  def printOptions() = {
    println("1. FootPrint Options\n2. CaloriesCounter Options\n3. Save States \n4. LoadStates\n0. Quit")
  }

  def getUserChoice(caloricMaps: CaloricMaps): UserChoice = {
    print("Insert Option Number")
    val input = readLine()
    input match {
      case "2" => CaloriesOptions.caloriesCounterOptions(caloricMaps)
      case "3" => SaveStates
      case "4" => LoadStates

      case "0" => Quit
      case _ => {
        println("Choice not available")
        printOptions()
        getUserChoice(caloricMaps)
      }
    }
  }
}
