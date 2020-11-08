package main

import scala.annotation.tailrec
import fileOperations.FileOperations


object Application extends App{

  val footPrintState = FootPrintState(0);
  val calorieCounter = CalorieCounter(0);

  @tailrec
  def main_loop(footPrintState: FootPrintState, calorieCounter: CalorieCounter): Unit = {
    main_loop(footPrintState, calorieCounter);
  }

  main_loop(footPrintState, calorieCounter)

}
