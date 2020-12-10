package graphicalInterface.healthTracker.addChange

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import graphicalInterface.healthTracker.addChange.changeBodyParameters.ChangeBodyParameters
import javafx.fxml.FXML
import javafx.scene.layout.Pane

class AddChange {
  @FXML
  var addChangeDisplay: Pane = _

  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  def addFoodDisplay() ={
    loadPage[AddFood](addChangeDisplay).initialize(home)
  }

  def addDrinkDisplay() ={
    loadPage[AddDrink](addChangeDisplay).initialize(home)
  }

  def addExerciseDisplay() ={
    loadPage[AddExercise](addChangeDisplay).initialize(home)
  }

  def addSleepDisplay() ={
    loadPage[AddSleep](addChangeDisplay).initialize(home)
  }

  def setGoalDisplay() ={
    loadPage[SetGoal](addChangeDisplay).initialize(home)
  }

  def addCupOfWaterDisplay() ={
    loadPage[AddCupOfWater](addChangeDisplay).initialize(home)
  }

  def changeBodyParametersMenu() ={
    loadPage[ChangeBodyParameters](addChangeDisplay).initialize(home)
  }
}
