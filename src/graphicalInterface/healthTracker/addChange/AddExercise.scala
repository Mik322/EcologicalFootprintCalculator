package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddSport
import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState

class AddExercise {
  @FXML
  var exerciseChoice: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  var exerciseTime: TextField = _
  @FXML
  def initialize(): Unit = {
    addToExerciseList(FxApp.caloricMaps.exercisesMap.keys.toList.sorted)
  }


  def addExercise(): Unit ={
    val exercise = AddSport(exerciseChoice.getValue,exerciseTime.getText().toInt,Date.today)
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(exercise,healthTracker,FxApp.caloricMaps)
    FxApp.updateHealthTracker(newHealthTracker)
  }

  def addToExerciseList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      exerciseChoice.getItems.add(value)
      addToExerciseList(next)

  }
}
