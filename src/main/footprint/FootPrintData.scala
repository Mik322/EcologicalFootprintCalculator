package main.footprint

import main.Date
import main.States.FootPrintState
import main.footprint.transport.Car

case class FootPrintData(points: Int, electricityPerMonth: Double, kmByCarPerMonth: Double, consumptionCar: Double)

object FootPrintData {
  def getElectricityBill(footPrintData: FootPrintData, electricityCost: Double): Double = footPrintData.electricityPerMonth * electricityCost

  def getFuelConsumedComparedAverage(footPrintState: FootPrintState, month: Date): Double = {
    val monthFuelConsumption = footPrintState.transportTrips
      .filter(t => t.mean.isInstanceOf[Car] && t.date.getMonth() == month.getMonth())
      .foldLeft(0.0)((counter, transport) => counter + Car.getCarConsumptionInTrip(transport))

    val supposedFuelConsumption = footPrintState.footPrintData.consumptionCar/100 * footPrintState.footPrintData.kmByCarPerMonth

    monthFuelConsumption - supposedFuelConsumption
  }

  def getPriceSavedFromFuel(footPrintState: FootPrintState, month: Date, fuelPrice: Double): Double = getFuelConsumedComparedAverage(footPrintState, month) * fuelPrice

  def getEarthsConsumedString(footPrintState: FootPrintState): String ={
    val points = footPrintState.footPrintData.points
    if(points < 60) "Congratulations"
    else if(points > 60 && points < 120) "We would need an extra planet"
    else if(points > 120 && points < 180) "We would need two extra planets"
    else "We would need four extra planets"
  }
}
