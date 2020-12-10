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

  //TODO: Correct error handling

  def calcSolarPanels(): Unit = {
    val num = Electricity.getRequiredSolarPanels(FxApp.getFootPrint.electricity, getPanelPower, getSunlight)
    necessaryPanels.setText(s"$num solar panels")
  }

  def calcTotalPower(): Unit = {
    val panels =numberPanels.getText.toInt
    val power = Electricity.getDailySolarPanelsProduction(getPanelPower, getSunlight, panels)
    totalPower.setText(s"You produce $power Wh per day")
  }

  private def getSunlight: Int = sunlight.getText.toInt
  private def getPanelPower: Int = panelPower.getText.toInt
}
