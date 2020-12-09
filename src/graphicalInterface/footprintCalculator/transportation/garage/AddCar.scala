package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, TextField}
import main.footprint.transport.Fuel.{Diesel, Electric, Hydrogen, Petrol}
import main.footprint.transport.{Car, Fuel}

class AddCar {
  private var homePage: HomePage = _

  def initialize(homePage: HomePage) = this.homePage = homePage

  private var typeOfFuel: ObservableList[Fuel] = FXCollections.observableArrayList(Diesel, Petrol, Electric, Hydrogen)

  @FXML
  var car_name: TextField = _
  @FXML
  var car_consumption: TextField = _
  @FXML
  var car_fuel: ChoiceBox[Fuel] = new ChoiceBox[Fuel]()
  @FXML
  var add_car: Button = _

  def initialize = {
    car_fuel.setItems(typeOfFuel)
  }

  def AddCar() = {
    val car = Car(car_name.getText(), car_consumption.getText().toDouble, car_fuel.getValue)
    val footPrint = homePage.getFootPrint
    val new_cars = footPrint.cars.appended(car)
    val new_footPrint = footPrint.copy(cars = new_cars)
    homePage.updateFootPrint(new_footPrint)
  }

}
