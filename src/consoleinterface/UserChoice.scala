package consoleinterface

import main.Date
import main.calorieCounter.caloricstructures.Goal
import main.footprint.TransportMeans.TransportMean
import main.footprint.footprintstructs.energy.EnergySource
import main.footprint.footprintstructs.waste.TypeOfWaste

trait UserChoice

case object SaveStates extends UserChoice
case class SetGoal(goal: Goal.Value) extends UserChoice
case object GetBody extends UserChoice
case class AddSleep(hours: Int, date: Date) extends UserChoice

case class AddTransportTrip(mean: TransportMean, km: Double) extends UserChoice
case object GetTransportEmissions extends UserChoice
case object GetTransportHistory extends UserChoice
case class AddWaste(kg: Int, typeOfWaste: TypeOfWaste) extends UserChoice
case object GetWasteEmissions extends UserChoice
case class SetEnergySource(source: EnergySource) extends UserChoice
case object GetEnergyEmissions extends UserChoice
case class SetWaterConsumption(amount: Double) extends UserChoice
case object GetWaterEmissions extends UserChoice
case object GetEcologicalFootPrint extends UserChoice

case object Quit extends UserChoice