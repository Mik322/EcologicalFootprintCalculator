package main.healthTracker

import consoleinterface.caloriescouter.options.BodyChange
import main.Date
import main.States.HealthTracker
import main.healthTracker.Body._

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

  def changeBody(newParam: BodyChange, counter: HealthTracker): HealthTracker = newParam match {
    case BodyChange.ChangeAge(age) => counter.copy(body = counter.body.copy(age = age))

    case BodyChange.ChangeHeight(height) => counter.copy(body = counter.body.copy(height = height))

    case BodyChange.ChangeLifestyle(lifestyle) => counter.copy(body = counter.body.copy(lifestyle = lifestyle))

    case BodyChange.ChangeWeight(weight, date) =>
      val newBody = counter.body.copy(weight = weight)
      counter.copy(body = newBody, weightHistory = counter.weightHistory.appended((weight, date)))
  }

  def changeWeight(counter: HealthTracker, weight: Double, date: Date): HealthTracker = {
    val newBody = counter.body.copy(weight = weight)
    counter.copy(body = newBody, weightHistory = counter.weightHistory.appended((weight, date)))
  }
}