package main.footprint

import main.Date
import main.States.FootPrintState
import main.footprint.transport.Car

case class StaticData(points: Int, kmByCarPerMonth: Double, consumptionCar: Double)

object StaticData {


  def getMonthFuelConsumption(footPrintState: FootPrintState, month: Date) = {
    footPrintState.transportTrips
      .filter(t => t.mean.isInstanceOf[Car] && t.date.getMonth() == month.getMonth())
      .foldLeft(0.0)((counter, transport) => counter + Car.getCarConsumptionInTrip(transport))
  }


  def getFuelConsumedComparedAverage(footPrintState: FootPrintState, month: Date): Double = {
    val monthFuelConsumption = getMonthFuelConsumption(footPrintState, month)
    val supposedFuelConsumption = footPrintState.staticData.consumptionCar/100 * footPrintState.staticData.kmByCarPerMonth

    monthFuelConsumption - supposedFuelConsumption
  }

  def getPriceSavedFromFuel(footPrintState: FootPrintState, month: Date, fuelPrice: Double): Double = getFuelConsumedComparedAverage(footPrintState, month) * fuelPrice

  def getPriceSavedFromAnotherCar(footPrintState: FootPrintState, month: Date, fuelPrice: Double)={}

  def getEarthsConsumedString(footPrintState: FootPrintState): String ={
    val points = footPrintState.staticData.points
    if(points < 60) "Congratulations"
    else if(points > 60 && points < 120) "We would need an extra planet"
    else if(points > 120 && points < 180) "We would need two extra planets"
    else "We would need four extra planets"
  }
}
