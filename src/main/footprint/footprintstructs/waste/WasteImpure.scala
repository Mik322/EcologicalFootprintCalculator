package main.footprint.footprintstructs.waste

import main.States.FootPrintState

object WasteImpure {

  def printWasteEmissions(footPrintState: FootPrintState): Unit = {
    val emissions = footPrintState.waste
    footPrintState.waste match {
      case None => println("You don't have any emissions from waste")
      case Some(value) => println(s"Your total emissions from Food Waste and Recycling is ${value.totalEmissions} g CO2")
    }

  }

}
