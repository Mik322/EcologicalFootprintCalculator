package consoleinterface.caloriescouter

import consoleinterface.caloriescouter.inputs.BodyInput
import consoleinterface.caloriescouter.options.BodyChange
import consoleinterface.caloriescouter.options.BodyChange.{ChangeAge, ChangeHeight, ChangeLifestyle, ChangeWeight}
import main.Date

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object BodyChangeMenu {

  def menu(): BodyChange = {
    println("")

    try {
      readLine().toInt match {
        case 1 => changeHeight()
        case 2 => changeHeight()
        case 3 => changeAge()
        case 4 => changeLifestyle()
        case _ => menu()
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid Format")
        menu()
    }
  }

  @tailrec
  private def changeHeight(): ChangeHeight = {
    print("Insert height (in cm): ")
    try {
      val height = readLine().toInt
      ChangeHeight(height)
    } catch {
      case _: NumberFormatException =>
        println("Invalid Format")
        changeHeight()
    }
  }

  @tailrec
  private def changeWeight(): ChangeWeight = {
    print("Insert weight (in kg): ")
    try {
      val weight = readLine().toInt
      ChangeWeight(weight, Date.today())
    } catch {
      case _: NumberFormatException =>
        println("Invalid Format")
        changeWeight()
    }
  }

  private def changeAge(): ChangeAge = ChangeAge(BodyInput.ageInput())

  private def changeLifestyle(): ChangeLifestyle = ChangeLifestyle(BodyInput.lifestyleInput())

}
