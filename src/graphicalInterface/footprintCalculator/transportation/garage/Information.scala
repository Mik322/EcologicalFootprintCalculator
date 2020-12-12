package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Button, DatePicker, Label}
import main.Date
import main.footprint.FootPrintOps.gramOrKg
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

  def calculateFunc() = {
    if (date_picker.getValue == null) missing_values.setText("You need to fill every parameter in order to see the information")
    else {
      if (Date(date_picker.getValue) > Date.today()) missing_values.setText("You can't choose a future date!")
      else {
        missing_values.setText("")
        val fuelConsumption = Car.getMonthFuelConsumption(FxApp.getFootPrint, Date(date_picker.getValue))
        consumption.setText(s"You consumed a total of ${fuelConsumption.toInt} liters of fuel in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
        consumption.setVisible(true)
        val gasEmissions = Car.getMonthlyCarEmission(FxApp.getFootPrint, Date(date_picker.getValue)).toInt
        emissions.setText(s"You emitted a total of ${gramOrKg(gasEmissions)} CO2 in ${date_picker.getValue.getMonth.toString.toLowerCase.capitalize} of ${date_picker.getValue.getYear}")
        emissions.setVisible(true)
      }
    }
  }

}
