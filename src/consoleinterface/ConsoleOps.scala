package consoleinterface

import consoleinterface.StartOptions.{LoadState, StartOptions}

import scala.io.StdIn.readLine
import consoleinterface.caloriescouter.{CaloriesConsoleOps, CaloriesOptions}
import main.calorieCounter.caloricstructures.CaloricMaps
import consoleinterface.footprint.FootPrintOptions

object ConsoleOps {
  def printWelcome(): Unit = {
    println("Welcome to The Ecological Footprint Calculator and Calorie Counter")
  }

  def FirstPrompt(): StartOptions = {
    printWelcome()
    println("1. New Profile\n2. Load Profile")

    readLine() match {
      case "1" => CaloriesConsoleOps.getBodyInput()
      case "2" => LoadState
    }

  }

  def printOptions() = {
    println("1. FootPrint Options\n2. CaloriesCounter Options\n3. Save States\n0. Quit")
  }

  def getUserChoice(caloricMaps: CaloricMaps): UserChoice = {
    print("Insert Option Number")
    val input = readLine()
    input match {
      case "1" => FootPrintOptions.footPrintOptions()
      case "2" => CaloriesOptions.caloriesCounterOptions(caloricMaps)
      case "3" => SaveStates

      case "0" => Quit
      case _ => {
        println("Choice not available")
        printOptions()
        getUserChoice(caloricMaps)
      }
    }
  }
}
