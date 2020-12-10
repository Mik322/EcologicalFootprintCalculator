package graphicalInterface.healthTracker.addChange.changeBodyParameters

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.layout.Pane

class ChangeBodyParameters {

  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  @FXML
  var changeBodyParametersDisplay: Pane = _

  def changeHeightDisplay() ={
    loadPage[ChangeHeight](changeBodyParametersDisplay).initialize(home)
  }

  def changeWeightDisplay() ={
    loadPage[ChangeWeight](changeBodyParametersDisplay).initialize(home)
  }

  def changeAgeDisplay() ={
    loadPage[ChangeAge](changeBodyParametersDisplay).initialize(home)
  }

  def changeLifestyleDisplay() ={
    loadPage[ChangeLifestyle](changeBodyParametersDisplay).initialize(home)
  }
}
