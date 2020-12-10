package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddSport
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, Label, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState
import main.healthTracker.CaloricMaps

class AddExercise {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
    addToExerciseList(caloricMaps.exercisesMap.keys.toList.sorted)
  }

  @FXML
  var exerciseChoice: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  var exerciseTime: TextField = _
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
          val healthTracker = home.getHealthTracker
          val newHealthTracker = addCaloricActivityToState(exercise, healthTracker, caloricMaps)
          home.updateHealthTracker(newHealthTracker)
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
