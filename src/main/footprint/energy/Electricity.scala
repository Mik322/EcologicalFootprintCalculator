package main.footprint.energy

import consoleinterface.UserChoice.SetElectricitySources

case class Electricity(monthlyConsumption: Double, sources: List[ElectricitySource]) {
  def getEmissions: Double = Electricity.getElectricityEmissions(this)
}

object Electricity {
  def getElectricityBill(electricity: Electricity, electricityCost: Double): Double = electricity.monthlyConsumption * electricityCost

  def getRequiredSolarPanels(electricity: Electricity, solarPanelPower: Double, dailySunLightHours: Int): Int = {
    val panelPowerPerHour = solarPanelPower * 3600
    val requiredProduction = getKWattsPerHour(electricity)*1000/dailySunLightHours
    (requiredProduction / panelPowerPerHour).toInt
  }

  def getDailySolarPanelsProduction(power: Double, dailySunLightHours: Int, numberOfPanels: Int): Int = (power * numberOfPanels * dailySunLightHours).toInt

  def getElectricityEmissions(electricity: Electricity): Double = {
    electricity.sources
      .map(s => ElectricitySource.getKWhAndEmission(electricity, s)._2)
      .foldRight(0.0)(((total, emission) => total + emission))
  }

  private def getKWattsPerHour(electricity: Electricity): Double = electricity.monthlyConsumption/30/24*1.25

  def setElectricitySources(electricity: Electricity, setElectricitySources: SetElectricitySources): Electricity = {
    val sources = ElectricitySource.setSources(setElectricitySources.sources)
    electricity.copy(sources = sources)
  }
}
