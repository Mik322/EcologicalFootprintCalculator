package graphicalInterface.healthTracker.addChange.changeBodyParameters

import graphicalInterface.HomePage
import graphicalInterface.healthTracker.addChange.AddSleep
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.Pane
import main.healthTracker.CaloricMaps

class ChangeBodyParameters {

  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var changeBodyParametersDisplay: Pane = _

  def changeHeightDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("ChangeHeight.fxml"))
    changeBodyParametersDisplay.getChildren.clear()
    changeBodyParametersDisplay.getChildren.add(loader.load())
    loader.getController[ChangeHeight].initialize(home,caloricMaps)
  }

  def changeWeightDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("ChangeWeight.fxml"))
    changeBodyParametersDisplay.getChildren.clear()
    changeBodyParametersDisplay.getChildren.add(loader.load())
    loader.getController[ChangeWeight].initialize(home,caloricMaps)
  }

  def changeAgeDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("ChangeAge.fxml"))
    changeBodyParametersDisplay.getChildren.clear()
    changeBodyParametersDisplay.getChildren.add(loader.load())
    loader.getController[ChangeAge].initialize(home,caloricMaps)
  }

  def changeLifestyleDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("ChangeLifestyle.fxml"))
    changeBodyParametersDisplay.getChildren.clear()
    changeBodyParametersDisplay.getChildren.add(loader.load())
    loader.getController[ChangeLifestyle].initialize(home,caloricMaps)
  }
}
