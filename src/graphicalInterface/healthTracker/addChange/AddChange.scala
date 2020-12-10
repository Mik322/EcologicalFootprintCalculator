package graphicalInterface.healthTracker.addChange

import graphicalInterface.FxApp.loadPage
import graphicalInterface.healthTracker.addChange.changeBodyParameters.ChangeBodyParameters
import javafx.fxml.FXML
import javafx.scene.layout.Pane

class AddChange {
  @FXML
  var addChangeDisplay: Pane = _

  def addFoodDisplay(): Unit ={
    loadPage[AddFood](addChangeDisplay)
  }

  def addDrinkDisplay(): Unit ={
    loadPage[AddDrink](addChangeDisplay)
  }

  def addExerciseDisplay(): Unit ={
    loadPage[AddExercise](addChangeDisplay)
  }

  def addSleepDisplay(): Unit ={
    loadPage[AddSleep](addChangeDisplay)
  }

  def setGoalDisplay(): Unit ={
    loadPage[SetGoal](addChangeDisplay)
  }

  def addCupOfWaterDisplay(): Unit ={
    loadPage[AddCupOfWater](addChangeDisplay)
  }

  def changeBodyParametersMenu(): Unit ={
    loadPage[ChangeBodyParameters](addChangeDisplay)
  }
}
