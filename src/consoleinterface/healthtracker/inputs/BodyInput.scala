package consoleinterface.healthtracker.inputs

import scala.io.StdIn.readLine
import main.healthTracker.Body.{Active, BiologicalSex, ExtremelyActive, Female, Lifestyle, Male, Moderated, Sedentary, VeryActive}

object BodyInput {
  def heightInput(): Int ={
    print("Height(in cm): ")
    try{
      readLine().toInt match {
        case height if height < 0 => heightInput()
        case height => height
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        heightInput()
    }
  }
  def weightInput(): Double ={
    print("Weight(in kg): ")
    try{
      readLine().toDouble match {
        case weight if weight < 0 => weightInput()
        case weight => weight
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        weightInput()
    }
  }

  def biologicalSexInput(): BiologicalSex = {
    println("Biological Sex:\n0.Male\n1.Female")
    try {
      readLine().toInt match {
        case 0 => Male
        case 1 => Female
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        biologicalSexInput()
    }
  }

  def lifestyleInput(): Lifestyle = {
    println("Lifestyle(According to your occupation):\n1. Sedentary(People who work desk jobs and engage in very little exercise or chores.)\n2. Moderated(People who do chores and go on long walks/engage in exercise at least 1 to 3 days in a week.)\n3. Active(People who move a lot during the day and workout (moderate effort) at least 3 to 5 days in a week.)\n4. Very Active(People who play sports or engage in vigorous exercise on most days.)\n5. ExtremelyActive(People who do intense workouts 6 to 7 days a week with work that demands physical activity.)")
    try {
      readLine().toInt match {
        case 1 => Sedentary
        case 2 => Moderated
        case 3 => Active
        case 4 => VeryActive
        case 5 => ExtremelyActive
        case _ =>
          println("Please insert a valid option.")
          lifestyleInput()
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        lifestyleInput()
    }
  }

  def ageInput(): Int = {
    print("What is your age? ")
    try {
      readLine().toInt match {
        case age if (age >= 14 && age <= 100) => age
        case age if (age < 14) =>
          println("You're to young")
          ageInput()
        case _ =>
          println("You're to old")
          ageInput()
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        ageInput()
    }
  }
}
