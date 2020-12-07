package main.footprint

import main.States.FootPrintState

object Water {

  def getWaterString(footPrintState: FootPrintState): String = {
    footPrintState.water match {
      case None => "You don't have any emissions from water consumption."
      case Some(value) => s"Your total emissions from residential water use are ${value} g CO2"
    }
  }
}
