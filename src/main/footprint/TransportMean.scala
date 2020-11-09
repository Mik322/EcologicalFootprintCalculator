package main.footprint

trait TransportMean {

  case class Car(consumption: Double, km: Double, fuel: Int) extends TransportMean {
    def calculateCarConsumption(): Unit ={
      //0 -> Diesel , 1 -> Petrol
      if(fuel == 0) {
        //An average consumption of 5 liters/100 km then corresponds to 5 l x 2640 g/l / 100 (per km) = 132 g CO2/km
        return consumption * 2640 / 100 * km
      }
      if(fuel == 1){
        //An average consumption of 5 liters/100 km then corresponds to 5 l x 2392 g/l / 100 (per km) = 120 g CO2/km.
        return consumption * 2392 / 100 * km
      }
    }
  }

  case class Plane(km: Double) extends TransportMean

  case class Boat(km: Double) extends TransportMean

  case class Bus(km: Double) extends TransportMean

  case class SubWay(km: Double) extends TransportMean

  case class Train(km: Double) extends TransportMean
}
