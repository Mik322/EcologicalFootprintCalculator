package consoleinterface

import main.calorieCounter.{Gender, Lifestyle}
import main.calorieCounter.Body._
import main.footprint.TransportMeans.TransportMean
import main.footprint.footprintstructs.TypeOfWaste

trait UserChoice

case object SaveStates extends UserChoice
case object LoadStates extends UserChoice
case class SetBodyParams(height: Int, weight: Int, age: Int, gender: Gender, lifestyle: Lifestyle) extends UserChoice
case class AddFood(food: String, quantity: Int) extends UserChoice
case class AddSport(sport: String, time: Int) extends UserChoice
case class AddDrink(drink: String, quantity: Int) extends UserChoice
case object GetCalories extends UserChoice
case class AddTransportTrip(mean: TransportMean, km: Double) extends UserChoice
case object GetTransportEmissions extends UserChoice
case object GetTransportHistory extends UserChoice
case class AddWaste(kg: Int, typeOfWaste: TypeOfWaste) extends UserChoice
case object GetWasteEmissions extends UserChoice



case object Quit extends UserChoice