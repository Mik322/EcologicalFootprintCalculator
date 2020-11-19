package consoleinterface.caloriescouter.options

import consoleinterface.UserChoice
import main.Date
import main.calorieCounter.caloricstructures.Lifestyle

trait BodyChange extends UserChoice

object BodyChange {
  case class ChangeWeight(weight: Double, date: Date) extends BodyChange
  case class ChangeHeight(height: Int) extends BodyChange
  case class ChangeAge(age: Int) extends BodyChange
  case class ChangeLifestyle(lifestyle: Lifestyle) extends BodyChange
}
