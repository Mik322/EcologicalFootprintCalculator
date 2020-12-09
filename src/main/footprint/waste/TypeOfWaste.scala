package main.footprint.waste

trait TypeOfWaste

object TypeOfWaste {

  case object Food extends TypeOfWaste {
    override def toString: String = "Organic"
  }

  case object Recycled extends TypeOfWaste

}
