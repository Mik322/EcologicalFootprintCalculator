package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface._
import consoleinterface.caloriecounter.Calories


object Application extends App{

  val f = FootPrintState(0);
  val c = CalorieCounter(0, 0, None);

  printOptions()

  @tailrec
  def main_loop(footPrintState: FootPrintState, calorieCounter: CalorieCounter): Unit = {

    getUserChoice(List("Water"), List()) match {
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
      }
    }
  }

  main_loop(f, c)

}
