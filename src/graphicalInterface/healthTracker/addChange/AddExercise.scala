package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddSport
import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, Label, TextField}
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
  @FXML
  var exerciseErrorLabel: Label = _
  @FXML
  var timeErrorLabel: Label = _
  @FXML
  var addedLabel: Label = _


  def addExercise() = {
    exerciseErrorLabel.setVisible(false)
    timeErrorLabel.setVisible(false)
    addedLabel.setVisible(false)
    if (exerciseChoice.getValue == null) {
      exerciseErrorLabel.setVisible(true)
    }
    else {
      try {
        val exerciseT = exerciseTime.getText().toInt
        if (exerciseT <= 0)
          timeErrorLabel.setVisible(true)
        else {
          val exercise = AddSport(exerciseChoice.getValue, exerciseT, Date.today)
          val healthTracker = FxApp.getHealthTracker
          val newHealthTracker = addCaloricActivityToState(exercise, healthTracker, FxApp.caloricMaps)
          FxApp.updateHealthTracker(newHealthTracker)
          addedLabel.setVisible(true)
        }
      } catch {
        case _: NumberFormatException => timeErrorLabel.setVisible(true)
      }
    }
  }

  def addToExerciseList(list: List[String]): Unit = list match {
    case Nil =>
    case value :: next =>
      exerciseChoice.getItems.add(value)
      addToExerciseList(next)

  }
}
