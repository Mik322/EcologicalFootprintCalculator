package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, Label, TextField}
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
  @FXML
  var missing_values: Label = _
  @FXML
  var success_label: Label = _
  @FXML
  var delete_success_label: Label = _

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
    if (car_new_name.getText().isBlank || choose_car.getValue == null) MissingValues()
    else {
      val footPrint = homePage.getFootPrint
      val car = footPrint.cars.find(c => c.name == choose_car.getValue)
      car match {
        case None =>
        case Some(value) => {
          val exists_car = footPrint.cars.find(c => c.name == car_new_name.getText)
          exists_car match {
            case None => {
              val new_car = value.copy(name = car_new_name.getText)
              val new_cars = footPrint.cars.updated(footPrint.cars.indexOf(value), new_car)
              val new_trips = footPrint.transportTrips.map(t => {
                if (t.mean.isInstanceOf[Car] && t.mean.asInstanceOf[Car].name == value.name) {
                  TransportTrip(new_car, t.km, t.date)
                } else t
              })
              val new_footPrint = footPrint.copy(cars = new_cars, transportTrips = new_trips)
              homePage.updateFootPrint(new_footPrint)
              updateCarChoice()
              success_label.setText("Your car has been edited with success!")
              missing_values.setText("")
              delete_success_label.setText("")
            }
            case Some(value) => ExistingCar()
          }
        }
      }
    }
  }

  def updateCarChoice() = {
    choose_car.getItems.clear()
    addCars(homePage.getFootPrint.cars)
    car_new_name.clear()
  }

  def delete() = {
    if (choose_car.getValue == null) MissingCar()
    else {
      val footPrint = homePage.getFootPrint
      val car = footPrint.cars.find(c => c.name == choose_car.getValue)
      car match {
        case None =>
        case Some(value) => {
          val new_cars = footPrint.cars.filterNot(c => c == value)
          val new_footPrint = footPrint.copy(cars = new_cars)
          homePage.updateFootPrint(new_footPrint)
          delete_success_label.setText("Your car has been removed with success!")
          missing_values.setText("")
          updateCarChoice()
        }
      }
    }
  }

  def ExistingCar() = {
    missing_values.setText("You already have a car with that name. Please try a new name")
    success_label.setText("")
  }

  def MissingValues() = {
    missing_values.setText("You need to fill every parameter in order to edit a car")
    success_label.setText("")
  }

  def MissingCar() = {
    missing_values.setText("You need to choose a car to delete")
    success_label.setText("")
  }
}
