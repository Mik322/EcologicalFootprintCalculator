package graphicalInterface.footprintCalculator.electricity

import graphicalInterface.HomePage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class Electricity {
  @FXML
  var electricityLabel : Label = _
  @FXML
  var pane: Pane = _

  var homePage: HomePage = _

  def setHomePage(homePage: HomePage): Unit = this.homePage = homePage

  def setElectricitySourcesDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("SetElectricitySources.fxml"))
    pane.getChildren.clear()
    pane.getChildren.add(loader.load())
    loader.getController[SetElectricitySources].setHomePage(homePage)
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
