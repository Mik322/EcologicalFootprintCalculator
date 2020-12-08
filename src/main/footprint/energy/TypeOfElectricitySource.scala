package main.footprint.energy

trait TypeOfElectricitySource

object TypeOfElectricitySource {

  case object Gas extends TypeOfElectricitySource {
    override def toString: String = "Natural Gas"
  }

  case object Oil extends TypeOfElectricitySource {
    override def toString: String = "Oil"
  }

  case object Coal extends TypeOfElectricitySource {
    override def toString: String = "Coal"
  }

  case object Biomass extends TypeOfElectricitySource {
    override def toString: String = "Biomass"
  }

  case object Hydro extends TypeOfElectricitySource {
    override def toString: String = "Hydro"
  }

  case object Solar extends TypeOfElectricitySource {
    override def toString: String = "Solar"
  }

  case object Wind extends TypeOfElectricitySource {
    override def toString: String = "Wind"
  }

  case object Nuclear extends TypeOfElectricitySource {
    override def toString: String = "Nuclear"
  }
}

