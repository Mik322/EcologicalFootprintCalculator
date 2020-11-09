package main.fileOperations

import main.FootPrintState
import main.CalorieCounter
import java.io._


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
}
