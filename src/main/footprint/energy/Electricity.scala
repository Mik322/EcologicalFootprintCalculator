package main.footprint.energy

import main.States.FootPrintState
import main.footprint.FootPrintOps.getEnergyEmissionsOfType

case class Electricity(monthlyConsumption: Double, sources: List[EnergySource])

object Electricity {
  def getElectricityBill(electricity: Electricity, electricityCost: Double): Double = electricity.monthlyConsumption * electricityCost

  def getRequiredSolarPanels(electricity: Electricity, solarPanelPower: Double, dailySunLightHours: Int): Int = {
    val panelPowerPerHour = solarPanelPower * 3600
    val requiredProduction = getKWattsPerHour(electricity)*1000/dailySunLightHours
    (requiredProduction / panelPowerPerHour).toInt
  }

  def getDailySolarPanelsProduction(power: Double, dailySunLightHours: Int, numberOfPanels: Int): Int = (power * numberOfPanels * dailySunLightHours).toInt

  private def getKWattsPerHour(electricity: Electricity): Double = electricity.monthlyConsumption/30/24*1.25

  def setEnergySource(footPrintState: FootPrintState, source: EnergySource): FootPrintState ={
    val emissions = getEnergyEmissionsOfType(source)
    val totalEmissions = emissions + footPrintState.ecologicalFootPrint
    val newSource = source.copy(emissions = emissions)
    val energySources = footPrintState.electricity.sources.appended(newSource)
    val electricity = footPrintState.electricity.copy(sources = energySources)
    footPrintState.copy(ecologicalFootPrint = totalEmissions, electricity = electricity)
  }
}
