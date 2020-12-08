package main.footprint

import main.Date
import main.States.FootPrintState
import main.footprint.energy.Electricity
import main.footprint.transport.{Car, TransportMean, TransportTrip}
import main.footprint.waste.TypeOfWaste.{Food, Recycled}
import main.footprint.waste.{TypeOfWaste, Waste}

object FootPrintOps {

  def addTrip(footPrintState: FootPrintState, transport: TransportMean, km: Double,date: Date): FootPrintState = {
    val trip = TransportTrip(transport,km,date)
    val trips = footPrintState.transportTrips.appended(trip)
    footPrintState.copy(transportTrips = trips)
  }

  def addWaste(footPrintState: FootPrintState, kg: Int, typeOfWaste: TypeOfWaste): FootPrintState ={
    val w = getWasteObject(footPrintState,kg,typeOfWaste)
    footPrintState.copy(waste = w)
  }

  def getWasteObject(footPrintState: FootPrintState, kg:Int, typeOfWaste: TypeOfWaste): Waste ={
    typeOfWaste match {
      case Food => foodWaste(footPrintState, kg)
      case Recycled => recycledWaste(footPrintState,kg)
    }
  }

  def foodWaste(footPrintState: FootPrintState, kg: Int): Waste = {
    val previousWaste = footPrintState.waste.foodWaste
    footPrintState.waste.copy(foodWaste = previousWaste + kg)
  }

  def recycledWaste(footPrintState: FootPrintState, kg: Int): Waste ={
    val previousWaste = footPrintState.waste.recycledWaste
    footPrintState.waste.copy(recycledWaste = previousWaste + kg)
  }

  def getTotalEmissionsString(footPrintState: FootPrintState): String = {
    val transportEmissions = TransportTrip.getTotalEmissions(footPrintState.transportTrips)
    val wasteEmissions = Waste.getTotalEmissions(footPrintState.waste)
    val electricityEmissions = Electricity.getElectricityEmissions(footPrintState.electricity)
    val totalEmissions = transportEmissions + wasteEmissions + electricityEmissions

    s"Your total g of CO2 emissions are ${totalEmissions}"
  }

  def getCars(cars: List[Car]): String = {
    cars.map(c => s"${c.toString}").mkString("\n")
  }
}
