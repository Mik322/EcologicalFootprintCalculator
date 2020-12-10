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
        missing_values_1.setText("")
        val num = Electricity.getRequiredSolarPanels(FxApp.getFootPrint.electricity, getPanelPower, getSunlight)
        necessaryPanels.setText(s"You would need ${num} solar panels to cover your needs.")
      }catch {
        case _: NumberFormatException => missing_values_1.setText("You have entered an invalid number, please try again")
      }
    }
  }

  def calcTotalPower(): Unit = {
    if (numberPanels.getText.isBlank) missing_values_2.setText("You have to fill all parameters in order to calculate")
    else {
      try {
        missing_values_2.setText("")
        val panels = numberPanels.getText.toInt
        val power = Electricity.getDailySolarPanelsProduction(getPanelPower, getSunlight, panels)
        totalPower.setText(s"You produce ${power} Wh per day")
      }catch {
        case _: NumberFormatException => missing_values_2.setText("You have entered an invalid number, please try again")
      }
    }
  }

  private def getSunlight: Int = sunlight.getText.toInt
  private def getPanelPower: Int = panelPower.getText.toInt

}
