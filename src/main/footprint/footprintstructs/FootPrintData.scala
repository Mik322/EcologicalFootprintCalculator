package main.footprint.footprintstructs

import main.Date
import main.States.FootPrintState
import main.footprint.TransportMeans.Car

case class FootPrintData(points: Int, electricityPerMonth: Double, kmByCarPerMonth: Double, consumptionCar: Double)

object FootPrintData {
  def getElectricityBill(footPrintData: FootPrintData, electricityCost: Double): Double = footPrintData.electricityPerMonth * electricityCost

  def getCurrentTransportEmissionsDifference(footPrintState: FootPrintState, month: Date, fuelPrice: Double): Double = {
    val monthFuelConsumption = footPrintState.transportTrips
      .filter(t => t.mean.isInstanceOf[Car] && t.date.getMonth() == month.getMonth())
      .foldLeft(0.0)((counter, transport) => counter + (transport.km * transport.asInstanceOf[Car].consumption/100))

    val supposedFuelConsumption = footPrintState.footPrintData.consumptionCar/100 * footPrintState.footPrintData.kmByCarPerMonth

    (monthFuelConsumption - supposedFuelConsumption) * fuelPrice
  }
}
