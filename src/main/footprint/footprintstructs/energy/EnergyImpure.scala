package main.footprint.footprintstructs.energy

import main.FootPrintState

object EnergyImpure {
  def getPrint(sources: List[EnergySource]): Unit ={
    sources match {
      case ::(head, next) => {
        println(s"You have a consume of ${head.emissions} g CO2 provenient of ${head.TypeOfSource}")
        getPrint(next)
      }
      case Nil => println("")
    }
  }

  def getTotal(sources: List[EnergySource]): Double ={
    sources match {
      case ::(head, next) => {
        val emissions = head.emissions + getTotal(next)
        emissions
      }
      case Nil => 0
    }
  }

  def getEnergyEmissions(footPrintState: FootPrintState): Unit ={
    println("Your consume of co2 per month by energy source:")
    getPrint(footPrintState.energySources)
    println(s"With a total consumption of ${getTotal(footPrintState.energySources)}")
  }
}
