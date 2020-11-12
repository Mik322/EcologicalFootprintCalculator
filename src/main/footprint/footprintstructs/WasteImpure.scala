package main.footprint.footprintstructs

import main.FootPrintState

object WasteImpure {

  def printWasteEmissions(footPrintState: FootPrintState): Unit ={
    val emissions = footPrintState.carbonFootPrint
    println(s"Your total emissions from Food Waste and Recycling is ${emissions} g CO2")
  }

}
