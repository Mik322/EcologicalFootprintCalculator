package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.Label
import main.footprint.transport.Car

class CarTemplate {

  @FXML
  var car: Label = _
  @FXML
  var emissions: Label = _
  @FXML
  var kms: Label = _

  def initialize(c: Car, car_emissions: Double, car_kms: Double) = {
    car.setText(c.name)
    emissions.setText(car_emissions.toString)
    kms.setText(car_kms.toString)
  }

}
