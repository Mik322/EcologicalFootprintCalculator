package main.footprint.waste

import main.States.FootPrintState

case class Waste(foodWaste: Int, recycledWaste: Int)

object Waste {
  def printWasteEmissions(footPrintState: FootPrintState): String = s"Your total emissions from Food Waste and Recycling is ${getTotalEmissions(footPrintState.waste)} g CO2"

  def getTotalEmissions(waste: Waste): Int = {
    // Waste produced per-capita portugal: 1.5 tons, GHG: 500kg CO2-e
    //https://ec.europa.eu/eurostat/statistics-explained/index.php?title=File:Per-capita_GHG_emissions_1990_and_2008_for_landfill.PNG
    //https://www.pordata.pt/Europa/Produ%C3%A7%C3%A3o+de+res%C3%ADduos+per+capita-3359
    waste.foodWaste * 333 + waste.recycledWaste * 100
  }
}
