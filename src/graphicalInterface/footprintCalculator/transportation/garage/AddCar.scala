package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, Label, TextField}
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
  @FXML
  var error_message: Label = _
  @FXML
  var missing_values: Label = _
  @FXML
  var invalid_char: Label = _

  @FXML
  def initialize = {
    car_fuel.setItems(typeOfFuel)
  }

  def AddCar() = {
    if (car_name.getText().isBlank || car_consumption.getText().isBlank || car_fuel.getValue == null) MissingValues()
    else {
      missing_values.setText("")
      invalid_char.setText("")
      try{
      val car = Car(car_name.getText(), car_consumption.getText().toDouble, car_fuel.getValue)
      val footPrint = homePage.getFootPrint
      val exists_car = footPrint.cars.find(c => c.name == car_name.getText)
      exists_car match {
        case None => {
          val new_cars = footPrint.cars.appended(car)
          val new_footPrint = footPrint.copy(cars = new_cars)
          homePage.updateFootPrint(new_footPrint)
          error_message.setText("")
        }
        case Some(value) => ExistingCar()
      }
    }catch {
        case _: NumberFormatException => InvalidChar()
      }
    }
  }

  def InvalidChar() = {
    invalid_char.setText("Invalid Character. Please try again")
  }

  def MissingValues() = {
    missing_values.setText("You need to fill every parameter in order to add a car")
  }

  def ExistingCar() = {
    error_message.setText("You already have a car with that name. Please try a new one")
  }

}
