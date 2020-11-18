package main.fileOperations

import main.{CalorieCounter, FootPrintState, States}
import java.io._

import scala.annotation.tailrec
import scala.io.Source

object FileOperations {
  def saveStates(footPrintState: FootPrintState, calorieCounter: CalorieCounter) = {
    val states = States(footPrintState, calorieCounter)
    val out = new ObjectOutputStream(new FileOutputStream(new File("States")))
    out.writeObject(states)

  }

  def loadStates(): Option[(FootPrintState, CalorieCounter)] = {
    try {
      val in = new ObjectInputStream(new FileInputStream(new File("States")))
      val states = in.readObject().asInstanceOf[States]
      Some((states.footPrintState, states.calorieCounter))
    } catch {
      case _ : Throwable => None
    }
  }

  def processLines[A](lines: List[String], convert: String => A): Map[String, A] = {
    @tailrec
    def loop(lines: List[String], map: Map[String, A]): Map[String, A] = {
      lines match {
        case ::(head, next) => {
          val line = head.split(":")
          loop(next, map + (line(0) -> convert(line(1))))
        }
        case Nil => map
      }
    }
    loop(lines, Map())
  }

  def loadCaloriesMap[A](name: String, convert: String => A): Map[String, A] = {
    val file = Source.fromFile(name)
    val map = processLines(file.getLines.toList, convert)
    file.close()
    map
  }
}
