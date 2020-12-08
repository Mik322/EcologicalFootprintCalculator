package consoleinterface

import main.Date
import main.footprint.transport.{Fuel, TransportMean}
import main.footprint.energy.{ElectricitySource, TypeOfElectricitySource}
import main.footprint.waste.TypeOfWaste
import main.healthTracker.Goal

trait UserChoice
object UserChoice {
  case object SaveStates extends UserChoice
  case class ChangeWeight(weight: Double, date: Date) extends UserChoice
  trait AddCaloricActivity extends UserChoice
  case class SetGoal(goal: Goal.Value,date: Date) extends UserChoice
  case object GetBody extends UserChoice
  case class AddSleep(hours: Int, date: Date) extends UserChoice

  case class AddCar(name: String, consumption: Double, fuel: Fuel) extends UserChoice
  case class AddTransportTrip(mean: TransportMean, km: Double, date: Date) extends UserChoice
  case object GetTransportEmissions extends UserChoice
  case object GetTransportHistory extends UserChoice
  case class AddWaste(kg: Int, typeOfWaste: TypeOfWaste) extends UserChoice
  case object GetWasteEmissions extends UserChoice
  case class SetElectricitySources(sources: List[(TypeOfElectricitySource, Double)]) extends UserChoice
  case object GetEnergyEmissions extends UserChoice
  case object GetEcologicalFootPrint extends UserChoice
  case object GetTotalEmissions extends UserChoice

  case object GoToMainMenu extends UserChoice

  case object Quit extends UserChoice
}