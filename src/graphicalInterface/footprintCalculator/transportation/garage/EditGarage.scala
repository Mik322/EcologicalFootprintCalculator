package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, TextField}
import main.footprint.transport.{Car, Fuel, TransportTrip}

class EditGarage {

  private var homePage: HomePage = _

  @FXML
  var choose_car: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  var car_new_name: TextField = _
  @FXML
  var edit_car: Button = _
  @FXML
  var delete_car: Button = _

  def initialize(homePage: HomePage) = {
    this.homePage = homePage
    addCars(homePage.getFootPrint.cars)
  }

  def addCars(list: List[Car]): Unit = list match {
    case ::(head, next) => {
      choose_car.getItems.add(head.name)
      addCars(next)
    }
    case Nil =>
  }

  def edit() = {
    val footPrint = homePage.getFootPrint
    val car = footPrint.cars.find(c => c.name == choose_car.getValue)
    car match {
      case None =>
      case Some(value) => {
        val new_car = value.copy(name = car_new_name.getText)
        val new_cars = footPrint.cars.updated(footPrint.cars.indexOf(value), new_car)
        val new_trips = footPrint.transportTrips.map(t => {
          if(t.mean.isInstanceOf[Car] && t.mean.asInstanceOf[Car].name == value.name){
            TransportTrip(new_car,t.km,t.date)
          }else t
        })
        val new_footPrint = footPrint.copy(cars = new_cars, transportTrips = new_trips)
        homePage.updateFootPrint(new_footPrint)
      }
    }
  }

  def delete() = {
    val footPrint = homePage.getFootPrint
    val car = footPrint.cars.find(c => c.name == choose_car.getValue)
    car match {
      case None =>
      case Some(value) => {
        val new_cars = footPrint.cars.filterNot(c => c == value)
        val new_footPrint = footPrint.copy(cars = new_cars)
        homePage.updateFootPrint(new_footPrint)
      }
    }
  }
}
