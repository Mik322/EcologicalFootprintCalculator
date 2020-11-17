package consoleinterface

import main.calorieCounter.caloricstructures.{Gender, Lifestyle}
import main.calorieCounter.caloricstructures.GoalType.Goal

trait UserChoice

case object SaveStates extends UserChoice
case object LoadStates extends UserChoice
case class SetBodyParams(height: Int, weight: Double, age: Int, gender: Gender, lifestyle: Lifestyle) extends UserChoice
trait AddCaloricActivity extends UserChoice
case class SetGoal(goal: Goal) extends UserChoice
trait CaloricInformation extends UserChoice
case object GetBody extends UserChoice

case object Quit extends UserChoice