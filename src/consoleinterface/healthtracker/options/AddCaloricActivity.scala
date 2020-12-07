package consoleinterface.healthtracker.options

import consoleinterface.UserChoice
import main.Date

trait AddCaloricActivity extends UserChoice

object AddCaloricActivity {
  case class AddFood(food: String, quantity: Int, date: Date) extends AddCaloricActivity
  case class AddSport(sport: String, minutes: Int, date: Date) extends AddCaloricActivity
  case class AddDrink(drink: String, quantity: Int, date: Date) extends AddCaloricActivity
  case class AddWaterCup(date: Date) extends AddCaloricActivity
}
