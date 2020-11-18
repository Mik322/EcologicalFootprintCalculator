package consoleinterface

import main.calorieCounter.caloricstructures.{Gender, Lifestyle}
import main.calorieCounter.caloricstructures.GoalType.Goal
import main.footprint.TransportMeans.TransportMean
import main.footprint.footprintstructs.energy.EnergySource
import main.footprint.footprintstructs.waste.TypeOfWaste

trait UserChoice

case object SaveStates extends UserChoice
case object LoadStates extends UserChoice
case class SetBodyParams(height: Int, weight: Double, age: Int, gender: Gender, lifestyle: Lifestyle) extends UserChoice
trait AddCaloricActivity extends UserChoice
case class SetGoal(goal: Goal) extends UserChoice
trait CaloricInformation extends UserChoice
case object GetBody extends UserChoice

case class AddTransportTrip(mean: TransportMean, km: Double) extends UserChoice
case object GetTransportEmissions extends UserChoice
case object GetTransportHistory extends UserChoice
case class AddWaste(kg: Int, typeOfWaste: TypeOfWaste) extends UserChoice
case object GetWasteEmissions extends UserChoice
case class SetEnergySource(source: EnergySource) extends UserChoice
case object GetEnergyEmissions extends UserChoice
case class SetWaterConsumption(amount: Double) extends UserChoice
case object GetWaterEmissions extends UserChoice

case object Quit extends UserChoice