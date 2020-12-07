package main.footprint.energy

import main.States.FootPrintState

case class EnergySource(TypeOfSource: TypeOfEnergySource, amount: Double, emissions: Double)

object EnergySource {
  def getSourcesString(sources: List[EnergySource]): String = sources.map(src => s"You have a consume of ${src.emissions} g CO2 provenient of ${src.TypeOfSource}").mkString("\n")

  def getTotal(sources: List[EnergySource]): Double = sources.foldRight(0.0)((source, counter) => counter + source.emissions)

  def getEnergyEmissionsString(footPrintState: FootPrintState): String = {
    val header = "Your consume of co2 per month by energy source:\n"
    val sources = getSourcesString(footPrintState.electricity.sources)
    val total = s"\nWith a total consumption of ${EnergySource.getTotal(footPrintState.electricity.sources)}"
    header + sources + total
  }
}
