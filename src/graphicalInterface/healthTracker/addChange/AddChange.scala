package graphicalInterface.healthTracker.addChange

import graphicalInterface.HomePage
import graphicalInterface.healthTracker.addChange.changeBodyParameters.ChangeBodyParameters
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.Pane
import main.healthTracker.CaloricMaps

class AddChange {
  @FXML
  var addChangeDisplay: Pane = _

  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  def addFoodDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("AddFood.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
    loader.getController[AddFood].initialize(home,caloricMaps)
  }

  def addDrinkDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("AddDrink.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
    loader.getController[AddDrink].initialize(home,caloricMaps)
  }

  def addExerciseDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("AddExercise.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
    loader.getController[AddExercise].initialize(home,caloricMaps)
  }

  def addSleepDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("AddSleep.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
    loader.getController[AddSleep].initialize(home,caloricMaps)
  }

  def setGoalDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("SetGoal.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
    loader.getController[SetGoal].initialize(home,caloricMaps)
  }

  def addCupOfWaterDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("AddCupOfWater.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
    loader.getController[AddCupOfWater].initialize(home,caloricMaps)
  }

  def changeBodyParametersMenu() ={
    val loader = new FXMLLoader(getClass.getResource("changeBodyParameters/ChangeBodyParameters.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
    loader.getController[ChangeBodyParameters].initialize(home,caloricMaps)
  }
}
