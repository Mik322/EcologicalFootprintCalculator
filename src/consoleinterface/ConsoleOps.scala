package consoleinterface

import consoleinterface.StartOptions.{LoadState, NewProfile}

import scala.io.StdIn.readLine
import consoleinterface.healthtracker.{HealthTrackerConsoleOps, HealthTrackerOptions}
import consoleinterface.footprint.{FootPrintOptions, FootPrintQuestions}
import main.healthTracker.CaloricMaps
import consoleinterface.UserChoice.{Quit, SaveStates}
import main.footprint.transport.Car

object ConsoleOps {
  def printWelcome(): Unit = {
    println("Welcome to The Ecological Footprint Calculator and Health Tracker")
  }

  def FirstPrompt(): StartOptions = {
    printWelcome()
    println("1. New Profile\n2. Load Profile")
    readLine() match {
      case "1" => newProfile()
      case "2" => LoadState
    }
  }

  def newProfile(): NewProfile = {
    println("Please enter your username:")
    val username = readLine()
    NewProfile(username, HealthTrackerConsoleOps.getBodyInput(),FootPrintQuestions.setFootPrintData())
  }

  def printOptions(): Unit = {
    println("1. FootPrint Calculator\n2. Health Tracker\n3. Save States\n0. Quit")
  }

  @scala.annotation.tailrec
  def getUserChoice(caloricMaps: CaloricMaps, cars: List[Car]): UserChoice = {
    print("Insert Option Number")
    val input = readLine()
    input match {
      case "1" => FootPrintOptions.footPrintOptions(cars)
      case "2" => HealthTrackerOptions.healthTrackerOptions(caloricMaps)
      case "3" => SaveStates
      case "0" => Quit
      case _ =>
        println("Choice not available")
        printOptions()
        getUserChoice(caloricMaps, cars)
    }
  }
}
