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

  def setElectricitySourcesDisplay(): Unit ={
    loadPage[SetElectricitySources]("SetElectricitySources.fxml").setHomePage(homePage)
  }

  def getTotalEmissionsFromElectricityDisplay(): Unit ={
    loadPage[SeeElectricitySources]("SeeElectricitySources.fxml").initialize(homePage)
  }

  def getSolarPanelRecommendationDisplay(): Unit ={
    loadPage[SolarPanels]("SolarPanels.fxml").setHomePage(homePage)
  }

  def changeElectricityConsumptionDisplay(): Unit ={
    loadPage[ChangeConsumption]("ChangeConsumption.fxml").setHomePage(homePage)
  }

  private def loadPage[A](path: String): A = {
    val loader = new FXMLLoader(getClass.getResource(path))
    pane.getChildren.clear()
    pane.getChildren.add(loader.load())
    loader.getController[A]
  }
}
