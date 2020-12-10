package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, TextField}
import main.footprint.transport.{Car, TransportTrip}

class EditGarage {
  @FXML
  var choose_car: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  var car_new_name: TextField = _
  @FXML
  var edit_car: Button = _
  @FXML
  var delete_car: Button = _

  @FXML
  def initialize(): Unit = {
    addCars(FxApp.getFootPrint.cars)
  }

  def addCars(list: List[Car]): Unit = list match {
    case ::(head, next) => {
      choose_car.getItems.add(head.name)
      addCars(next)
    }
    case Nil =>
  }

  def edit(): Unit = {
    val footPrint = FxApp.getFootPrint
    val car = footPrint.cars.find(c => c.name == choose_car.getValue)
    car match {
      case None =>
      case Some(value) => {
        val new_car = value.copy(name = car_new_name.getText)
        val new_cars = footPrint.cars.updated(footPrint.cars.indexOf(value), new_car)
        val new_trips = footPrint.transportTrips.map(t => t.mean match {
          case Car(name,_,_) if name == value.name => TransportTrip(new_car, t.km, t.date)
          case _ => t
        })
        val new_footPrint = footPrint.copy(cars = new_cars, transportTrips = new_trips)
        FxApp.updateFootPrint(new_footPrint)
      }
    }
  }

  def delete(): Unit = {
    val footPrint = FxApp.getFootPrint
    val car = footPrint.cars.find(c => c.name == choose_car.getValue)
    car match {
      case None =>
      case Some(value) => {
        val new_cars = footPrint.cars.filterNot(c => c == value)
        val new_footPrint = footPrint.copy(cars = new_cars)
        FxApp.updateFootPrint(new_footPrint)
      }
    }
  }
}
