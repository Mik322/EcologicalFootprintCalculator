package main.healthTracker

import consoleinterface.healthtracker.options.BodyChange
import main.States.HealthTracker
import main.healthTracker.Body.{BiologicalSex, Lifestyle}

case class Body(height: Int, weight: Double, age: Int, biologicalSex: BiologicalSex, lifestyle: Lifestyle)

object Body {
  trait BiologicalSex
  case object Male extends BiologicalSex
  case object Female extends BiologicalSex

  trait Lifestyle
  case object Sedentary extends Lifestyle
  case object Moderated extends Lifestyle
  case object Active extends Lifestyle
  case object VeryActive extends Lifestyle
  case object ExtremelyActive extends Lifestyle

  def changeBody(newParam: BodyChange, tracker: HealthTracker): HealthTracker = newParam match {
    case BodyChange.ChangeAge(age) => tracker.copy(body = tracker.body.copy(age = age))

    case BodyChange.ChangeHeight(height) => tracker.copy(body = tracker.body.copy(height = height))

    case BodyChange.ChangeLifestyle(lifestyle) => tracker.copy(body = tracker.body.copy(lifestyle = lifestyle))

    case BodyChange.ChangeWeight(weight, date) =>
      val newBody = tracker.body.copy(weight = weight)
      tracker.copy(body = newBody, weightHistory = tracker.weightHistory.appended((weight, date)))
  }

  def createBody(height: Int, weight: Double, age: Int, biologicalSex: BiologicalSex, lifestyle: Lifestyle): Body = Body(height, weight, age, biologicalSex, lifestyle)

  def getBodyInformationString(body: Body): String = {
    val s1 = s"Your height: ${body.height}cm\n"
    val s2 = s"Your weight: ${body.weight}kg\n"
    val s3 = s"Your age: ${body.age}\n"
    val s4 = s"Your biological sex: ${body.biologicalSex}\n"
    val s5 = s"Your lifestyle: ${body.lifestyle}"
    s1 + s2 + s3 + s4 + s5
  }

  def calculateBMR(body: Body): Int = {
    val multiplier = body.lifestyle match {
      case Sedentary => 1.2
      case Moderated => 1.375
      case Active => 1.55
      case VeryActive => 1.725
      case ExtremelyActive => 1.9
    }
    calculateBiologicalSexBMR(body, multiplier)
  }

  def calculateBiologicalSexBMR(body: Body, multiplier: Double): Int = body.biologicalSex match {
    case Male => ((66.47 + (13.75 * body.weight) + (5.003 * body.height) - (6.755 * body.age)) * multiplier).asInstanceOf[Int]
    case Female => ((655.1 + (9.563 * body.weight) + (1.85 * body.height) - (4.676 * body.age)) * multiplier).asInstanceOf[Int]
  }
}