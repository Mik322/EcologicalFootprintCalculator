package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Button, DatePicker, Label}
import main.Date
import main.footprint.transport.Car

class Information {

  @FXML
  var date_picker: DatePicker = _
  @FXML
  var calculate: Button = _
  @FXML
  var consumption: Label = _
  @FXML
  var emissions: Label = _
  @FXML
  var missing_values: Label = _

<<<<<<< HEAD:src/graphicalInterface/footprintCalculator/transportation/garage/Information.scala
  def calculateFunc(): Unit = {
    val fuelConsumption = Car.getMonthFuelConsumption(FxApp.getFootPrint, Date(date_picker.getValue))
    consumption.setText(s"You consumed a total of ${fuelConsumption.toInt} liters of fuel in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
    consumption.setVisible(true)
    val gasEmissions = Car.getMonthlyCarEmission(FxApp.getFootPrint, Date(date_picker.getValue))
    emissions.setText(s"You emitted a total of $gasEmissions g CO2 of in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
    emissions.setVisible(true)
=======
  private var homePage: HomePage = _

  def initialize(homePage: HomePage) = {
    this.homePage = homePage
  }

  def calculateFunc() = {
    if (date_picker.getValue == null) missing_values.setText("You need to fill every parameter in order to see the information")
    else {
      missing_values.setText("")
      val fuelConsumption = Car.getMonthFuelConsumption(homePage.getFootPrint, Date(date_picker.getValue))
      consumption.setText(s"You consumed a total of ${fuelConsumption.toInt} liters of fuel in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
      consumption.setVisible(true)
      val gasEmissions = Car.getMonthlyCarEmission(homePage.getFootPrint, Date(date_picker.getValue))
      emissions.setText(s"You emitted a total of ${gasEmissions} g CO2 of in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
      emissions.setVisible(true)
    }
>>>>>>> Afonso:src/graphicalInterface/footprintCalculator/transportation/garage/Informations.scala
  }

}
