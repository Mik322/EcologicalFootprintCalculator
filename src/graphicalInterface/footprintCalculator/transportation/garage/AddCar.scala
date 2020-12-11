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
    error_message.setVisible(false)
    success_label.setVisible(false)
    missing_values.setVisible(false)
    invalid_char.setVisible(false)
    if (car_name.getText().isBlank || car_consumption.getText().isBlank || car_fuel.getValue == null)
      missing_values.setVisible(true)
    else {
      try {
        if (car_consumption.getText.toDouble <= 0)
          invalid_char.setVisible(true)
        else {
          val car = Car(car_name.getText(), car_consumption.getText().toDouble, car_fuel.getValue)
          val footPrint = FxApp.getFootPrint
          val exists_car = footPrint.cars.find(c => c.name == car_name.getText)
          exists_car match {
            case None => {
              val new_cars = footPrint.cars.appended(car)
              val new_footPrint = footPrint.copy(cars = new_cars)
              FxApp.updateFootPrint(new_footPrint)
              success_label.setVisible(true)
            }
            case Some(_) => error_message.setVisible(true)
          }
        }
      } catch {
        case _: NumberFormatException => invalid_char.setVisible(true)
      }
    }
  }


}
