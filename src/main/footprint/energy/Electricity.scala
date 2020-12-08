package main.footprint.energy

import consoleinterface.UserChoice.SetElectricitySources

case class Electricity(monthlyConsumption: Double, sources: List[ElectricitySource]) {
  def getEmissions: Double = Electricity.getElectricityEmissions(this)
}

object Electricity {
  def getElectricityBill(electricity: Electricity, electricityCost: Double): Double = electricity.monthlyConsumption * electricityCost

  def getRequiredSolarPanels(electricity: Electricity, solarPanelPower: Double, dailySunLightHours: Int): Int = {
    val requiredProduction = (electricity.monthlyConsumption * 1.25) / (30 * dailySunLightHours)
    (requiredProduction*1000 / solarPanelPower).toInt + 1
  }

  def getSolarPanelsString(panels: Int): String = {
    s"You would need ${panels} solar panels to sustent your needs"
  }

  def getDailySolarPanelsProduction(power: Double, dailySunLightHours: Int, numberOfPanels: Int): Int = (power * numberOfPanels * dailySunLightHours).toInt

  def getElectricityEmissions(electricity: Electricity): Double = {
    electricity.sources
      .map(s => ElectricitySource.getKWhAndEmission(electricity, s)._2)
      .foldRight(0.0)(((total, emission) => total + emission))
  }

  def setElectricitySources(electricity: Electricity, setElectricitySources: SetElectricitySources): Electricity = {
    val sources = ElectricitySource.setSources(setElectricitySources.sources)
    electricity.copy(sources = sources)
  }

}
