package graphicalInterface.footprintCalculator.electricity

import graphicalInterface.FxApp
import graphicalInterface.FxApp.loadPage
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class ElectricityMenu {
  @FXML
  var pane: Pane = _
  @FXML
  var electricityLabel: Label = _
  @FXML
  def initialize(): Unit = electricityLabel.setText(s"${FxApp.getFootPrint.electricity.monthlyConsumption} kWh")

  def setElectricitySourcesDisplay(): Unit ={
    loadPage[SetElectricitySources](pane)
  }

  def getTotalEmissionsFromElectricityDisplay(): Unit ={
    loadPage[SeeElectricitySources](pane)
  }

  def getSolarPanelRecommendationDisplay(): Unit ={
    loadPage[SolarPanels](pane)
  }

  def changeElectricityConsumptionDisplay(): Unit ={
    loadPage[ChangeConsumption](pane)
  }
}
