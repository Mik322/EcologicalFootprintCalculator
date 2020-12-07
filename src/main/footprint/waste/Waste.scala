package main.footprint.waste

import main.States.FootPrintState

case class Waste(foodWaste: Int, recycledWaste: Int, totalEmissions: Double)

object Waste {
  def printWasteEmissions(footPrintState: FootPrintState): String = {
    footPrintState.waste match {
      case None => "You don't have any emissions from waste"
      case Some(value) => s"Your total emissions from Food Waste and Recycling is ${value.totalEmissions} g CO2"
    }

  }
}
