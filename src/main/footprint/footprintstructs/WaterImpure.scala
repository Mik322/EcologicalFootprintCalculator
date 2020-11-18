package main.footprint.footprintstructs

import main.FootPrintState

object WaterImpure {

  def printWaterImpure(footPrintState: FootPrintState): Unit ={
    footPrintState.water match {
      case None => println("You don't have any emissions from water consumption.")
      case Some(value) => println(s"Your total emissions from residential water use are ${value} g CO2")
    }
  }
}
