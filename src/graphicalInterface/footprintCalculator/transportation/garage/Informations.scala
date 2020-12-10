package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Button, DatePicker, Label}
import main.Date
import main.footprint.transport.Car

class Informations {

  @FXML
  var date_picker: DatePicker = _
  @FXML
  var calculate: Button = _
  @FXML
  var consumption: Label = _
  @FXML
  var emissions: Label = _

  def calculateFunc(): Unit = {
    val fuelConsumption = Car.getMonthFuelConsumption(FxApp.getFootPrint, Date(date_picker.getValue))
    consumption.setText(s"You consumed a total of ${fuelConsumption.toInt} liters of fuel in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
    consumption.setVisible(true)
    val gasEmissions = Car.getMonthlyCarEmission(FxApp.getFootPrint, Date(date_picker.getValue))
    emissions.setText(s"You emitted a total of ${gasEmissions} g CO2 of in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
    emissions.setVisible(true)
  }

}
