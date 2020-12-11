package graphicalInterface.footprintCalculator.electricity

import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import main.footprint.energy.Electricity

class SolarPanels {
  @FXML
  private var sunlight: TextField = _
  @FXML
  private var panelPower: TextField = _
  @FXML
  private var numberPanels: TextField = _
  @FXML
  private var necessaryPanels: Label = _
  @FXML
  private var totalPower: Label = _
  @FXML
  private var missing_values_1: Label = _
  @FXML
  private var missing_values_2: Label = _

  def calcSolarPanels(): Unit = {
    if (sunlight.getText.isBlank || panelPower.getText.isBlank) missing_values_1.setText("You have to fill all parameters in order to calculate")
    else {
      try {
        val validPanelPower = panelPower.getText.toInt
        val validSunLight = sunlight.getText.toInt
        if (validPanelPower < 0 || validSunLight < 0 || validSunLight > 24) missing_values_1.setText("You entered an invalid number")
        else {
          missing_values_1.setText("")
          val num = Electricity.getRequiredSolarPanels(FxApp.getFootPrint.electricity, validPanelPower, validSunLight)
          necessaryPanels.setText(s"You would need ${num} solar panels to cover your needs.")
        }
      } catch {
        case _: NumberFormatException => missing_values_1.setText("You have entered an invalid number, please try again")
      }
    }
  }

  def calcTotalPower(): Unit = {
    if (numberPanels.getText.isBlank || panelPower.getText.isBlank || sunlight.getText.isBlank) missing_values_2.setText("You have to fill all parameters in order to calculate")
    else {
      try {
        val validPanelPower = panelPower.getText.toInt
        val validSunLight = sunlight.getText.toInt
        val validPanels = numberPanels.getText.toInt
        if (validPanelPower < 0 || validSunLight < 0 || validSunLight > 24) missing_values_1.setText("You entered an invalid number")
        else {
          if (validPanels < 0) missing_values_2.setText("You entered an invalid number")
          else {
            missing_values_2.setText("")
            val power = Electricity.getDailySolarPanelsProduction(validPanelPower, validSunLight, validPanels)
            totalPower.setText(s"You produce ${power} Wh per day")
          }
        }
      } catch {
        case _: NumberFormatException => missing_values_2.setText("You have entered an invalid number, please try again")
      }
    }
  }


}
