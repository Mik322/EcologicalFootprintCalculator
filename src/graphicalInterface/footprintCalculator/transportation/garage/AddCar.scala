package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.{FxApp, HomePage}
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, Label, TextField}
import main.footprint.transport.Fuel.{Diesel, Electric, Hydrogen, Petrol}
import main.footprint.transport.{Car, Fuel}

class AddCar {
  private val typeOfFuel: ObservableList[Fuel] = FXCollections.observableArrayList(Diesel, Petrol, Electric, Hydrogen)

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
  var success_label: Label = _

  @FXML
  def initialize: Unit = {
    car_fuel.setItems(typeOfFuel)
  }

  def addCar(): Unit = {
    if (car_name.getText().isBlank || car_consumption.getText().isBlank || car_fuel.getValue == null) missingValues()
    else {
      missing_values.setText("")
      invalid_char.setText("")
      try {
        if (car_consumption.getText.toDouble < 0) {
          invalidChar()
        } else {
          val car = Car(car_name.getText(), car_consumption.getText().toDouble, car_fuel.getValue)
          val footPrint = FxApp.getFootPrint
          val exists_car = footPrint.cars.find(c => c.name == car_name.getText)
          exists_car match {
            case None => {
              val new_cars = footPrint.cars.appended(car)
              val new_footPrint = footPrint.copy(cars = new_cars)
              FxApp.updateFootPrint(new_footPrint)
              error_message.setText("")
              success_label.setText("Your car has been added with success!")
            }
          }
        }
      } catch {
        case _: NumberFormatException => invalidChar()
      }
    }
  }

  def invalidChar(): Unit = {
    invalid_char.setText("Invalid Character. Please try again")
    success_label.setText("")
  }

  def missingValues(): Unit = {
    missing_values.setText("You need to fill every parameter in order to add a car")
    success_label.setText("")
  }

  def existingCar(): Unit = {
    error_message.setText("You already have a car with that name. Please try a new one")
    success_label.setText("")
  }

}
