package main.footprint.footprintstructs.energy

import main.FootPrintState

object EnergyImpure {
  def printEnergySource(source: EnergySource): Unit ={
    println(s"You have a consume of ${source.emissions} g CO2 provenient of ${source.TypeOfSource}")
  }

  def getPrint(sources: List[EnergySource]): Unit ={
    sources match {
      case ::(head, next) => {
        printEnergySource(head)
        getPrint(next)
      }
      case Nil => println("\n")
    }
  }

  def getEnergyEmissions(footPrintState: FootPrintState): Unit ={
    println("Your consume of co2 per month by energy source:")
    getPrint(footPrintState.energySources)
  }
}
