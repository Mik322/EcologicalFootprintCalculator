package graphicalInterface.healthTracker

import graphicalInterface.HomePage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.Pane
import main.healthTracker.CaloricMaps

class HealthTrackerInterface {

  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  @FXML
  private var healthTrackerDisplay: Pane = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  def addChangeMenu() ={
    val loader = new FXMLLoader(getClass.getResource("AddChange.fxml"))
    healthTrackerDisplay.getChildren.clear()
    healthTrackerDisplay.getChildren.add(loader.load())
  }

  def healthTrackerInformationMenu() ={
    val loader = new FXMLLoader(getClass.getResource("HealthTrackerInformation.fxml"))
    healthTrackerDisplay.getChildren.clear()
    healthTrackerDisplay.getChildren.add(loader.load())
  }
}
