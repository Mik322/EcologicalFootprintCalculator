package main.footprint.footprintstructs

import main.States.FootPrintState


object FootPrintDataImpure {

  def printResults(footPrintState: FootPrintState): Unit ={
    val points = footPrintState.footPrintData.points
    if(points < 60) println("Congratulations")
    if(points > 60 && points < 120) println("We would need an extra planet")
    if(points > 120 && points < 180) println("We would need two extra planets")
    if(points > 180) println("We would need four extra planets")
  }
}
