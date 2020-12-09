package main.footprint.energy

case class ElectricitySource(source: TypeOfElectricitySource, percentage: Double)

object ElectricitySource {

  def getKWhOfSource(electricity: Electricity, electricitySource: ElectricitySource): Double = electricity.monthlyConsumption * electricitySource.percentage

  def setSources(sources: List[(TypeOfElectricitySource, Double)]): List[ElectricitySource] = {
    sources.map(source => ElectricitySource(source._1, source._2))
  }

  def getKWhAndEmission(electricity: Electricity, source: ElectricitySource): (Double, Double) = {
    val kwh = getKWhOfSource(electricity, source)
    val emissions = getEmissionOfSourceGram(source, kwh)
    (kwh, emissions)
  }

  def getSourceString(electricity: Electricity, electricitySource: ElectricitySource): String = {
    val (kwh, emissions) = getKWhAndEmission(electricity, electricitySource)
    s"You consumed ${kwh}KWh and emitted ${emissions}g of CO2 from ${electricitySource.source}"
  }

  def getEmissionOfSourceGram(electricitySource: ElectricitySource, kwh: Double): Double = {
    //https://www.rensmart.com/Calculators/KWH-to-CO2
    val emissionPerKWh = electricitySource.source match {
      case TypeOfElectricitySource.Gas => 417
      case TypeOfElectricitySource.Coal => 1002
      case TypeOfElectricitySource.Oil => 650
      case TypeOfElectricitySource.Biomass => 350
      case TypeOfElectricitySource.Hydro => 5
      case TypeOfElectricitySource.Wind => 5
      case TypeOfElectricitySource.Solar => 58
      case TypeOfElectricitySource.Nuclear => 5
    }
    kwh * emissionPerKWh
  }

  def getTotalEmissions(electricity: Electricity):String = {
    val emissions = electricity.sources.foldRight(0.0)((src, counter) => counter + getKWhAndEmission(electricity,src)._2)
    s"Your total emissions from electricity are ${emissions} g of CO2"
  }

  def getEnergySources(electricity: Electricity): String = {
    electricity.sources.map(src => s"${getSourceString(electricity,src)}").mkString("\n")
  }

  def getEnergySourcesList(electricity: Electricity): List[(TypeOfElectricitySource, Double, Double)] = {
    electricity.sources
      .map(source => {
        val KWhEmission = getKWhAndEmission(electricity, source)
        (source.source, KWhEmission._1, KWhEmission._2)
      })
  }

}
