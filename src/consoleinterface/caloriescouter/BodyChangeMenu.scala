package consoleinterface.caloriescouter

import consoleinterface.UserChoice
import consoleinterface.caloriescouter.inputs.BodyInput
import consoleinterface.caloriescouter.options.BodyChange
import consoleinterface.caloriescouter.options.BodyChange.{ChangeAge, ChangeHeight, ChangeLifestyle, ChangeWeight}
import consoleinterface.caloriescouter.CaloriesOptions.addMenu
import main.Date
import main.calorieCounter.caloricstructures.CaloricMaps

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object BodyChangeMenu {

  def menu(caloricMaps: CaloricMaps): UserChoice = {
    println("1. Change your height\n2. Change your weight\n3. Change your age\n4. Change your lifestyle\n0. Go back")

    try {
      readLine().toInt match {
        case 1 => changeHeight()
        case 2 => changeWeight()
        case 3 => changeAge()
        case 4 => changeLifestyle()
        case 0 => addMenu(caloricMaps)
        case _ => menu(caloricMaps)
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid Format")
        menu(caloricMaps)
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
