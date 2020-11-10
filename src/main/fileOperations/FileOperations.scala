package main.fileOperations

import main.FootPrintState
import main.CalorieCounter
import java.io._
import scala.io.Source


case class States(footPrintState: FootPrintState, calorieCounter: CalorieCounter)

object FileOperations {
  def saveStates(footPrintState: FootPrintState, calorieCounter: CalorieCounter) = {
    val states = States(footPrintState, calorieCounter)
    try {
      val out = new ObjectOutputStream(new FileOutputStream(new File("States")))
      out.writeObject(states)
    }
  }

  def loadStates(): Option[(FootPrintState, CalorieCounter)] = {
    try {
      val in = new ObjectInputStream(new FileInputStream(new File("States")))
      val states = in.readObject().asInstanceOf[States]
      Some((states.footPrintState, states.calorieCounter))
    } catch {
      case _ => None
    }
  }

  def processLines(lines: List[String]): Map[String, Int] = {
    lines match {
      case ::(head, next) =>{
        val line = head.split(":")
        processLines(next) + (line(0) -> line(1).toInt)
      }
      case Nil => Map()
    }
  }

  def processLinesDouble(lines:List[String]): Map[String,Double]={
    lines match {
      case ::(head, next) =>{
        val line = head.split(":")
        processLinesDouble(next) + (line(0) -> line(1).toDouble)
      }
      case Nil => Map()
    }
  }


  def loadCaloriesMap(name: String): Map[String, Int] = {
    processLines(Source.fromFile(name).getLines.toList)
  }

  def loadCaloriesMapDouble(name: String): Map[String, Double] = {
    processLinesDouble(Source.fromFile(name).getLines.toList)
  }


}
