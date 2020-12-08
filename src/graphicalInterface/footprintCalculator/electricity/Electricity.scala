package graphicalInterface.footprintCalculator.electricity

import javafx.fxml.FXML
import javafx.scene.control.Label

class Electricity {
  @FXML
  var electricityLabel : Label = _

  def setElectricitySourcesDisplay() ={
    electricityLabel.setText("setElectricitySourcesDisplay")
  }

  def getTotalEmissionsFromElectricityDisplay() ={
    electricityLabel.setText("getTotalEmissionsFromElectricityDisplay")
  }

  def getSolarPanelRecommendationDisplay() ={
    electricityLabel.setText("getSolarPanelRecommendationDisplay")
  }

  def changeElectricityConsumptionDisplay() ={
    electricityLabel.setText("changeElectricityConsumptionDisplay")
  }
}
