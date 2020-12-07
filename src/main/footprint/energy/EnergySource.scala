package main.footprint.energy

import main.States.FootPrintState
import main.footprint.energy.TypeOfEnergySource.{Coal, Electricity, Gas, Oil, Wood}

case class EnergySource(TypeOfSource: TypeOfEnergySource, amount: Double)

object EnergySource {
  def getSourcesString(sources: List[EnergySource]): String = sources.map(src => s"You have a consume of ${getEnergyEmissionsOfType(src)} g CO2 provenient of ${src.TypeOfSource}").mkString("\n")

  def getTotalEmissions(sources: List[EnergySource]): Double = sources.foldRight(0.0)((source, counter) => counter + getEnergyEmissionsOfType(source))

  def getEnergyEmissionsOfType(source: EnergySource): Double ={
    source.TypeOfSource match {
      case Electricity => 256 * source.amount
      case Gas => 184 * source.amount
      case Oil => 314 * source.amount
      case Wood => 7 * source.amount
      case Coal => 414 * source.amount
    }
  }

  def getEnergyEmissionsString(footPrintState: FootPrintState): String = {
    val header = "Your consume of co2 per month by energy source:\n"
    val sources = getSourcesString(footPrintState.electricity.sources)
    val total = s"\nWith a total consumption of ${EnergySource.getTotalEmissions(footPrintState.electricity.sources)}"
    header + sources + total
  }
}
