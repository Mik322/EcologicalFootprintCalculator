package consoleinterface.caloriescouter

import scala.io.StdIn.readLine
import consoleinterface._

object CaloriesOptions {
  def caloriesCounterOptions(foodList: List[String], sportList: List[String]): UserChoice = {
    println("1. Add/Change\n0. Quit")
    val input = readLine()

    input match {
      case "1" => addMenu(foodList, sportList)
    }
  }

  def addMenu(foodList: List[String], sportList: List[String]): UserChoice = {
    println("1. Add Food\n0. Quit")
    val input = readLine()

    input match {
      case "1" => {
        CaloriesConsoleOps.printList(foodList, 0)
        val food = CaloriesConsoleOps.getFoodInput(foodList)
        food
      }
      case _ => Quit
    }
  }
}
