package main.footprint.waste

import main.States.FootPrintState

case class Waste(foodWaste: Int, recycledWaste: Int)

object Waste {
  def printWasteEmissions(footPrintState: FootPrintState): String = s"Your total emissions from Food Waste and Recycling is ${getTotalEmissions(footPrintState.waste)} g CO2"

  def getTotalEmissions(waste: Waste) = {
    //valor de recycled a alterar
    waste.foodWaste * 1900 + waste.recycledWaste * 100
  }
}
