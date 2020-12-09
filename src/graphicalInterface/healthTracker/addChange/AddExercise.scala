package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddSport
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
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


  def addExercise() ={
    val exercise = AddSport(exerciseChoice.getValue,exerciseTime.getText().toInt,Date.today)
    val healthTracker = home.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(exercise,healthTracker,caloricMaps)
    home.updateHealthTracker(newHealthTracker)
  }

  def addToExerciseList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      exerciseChoice.getItems.add(value)
      addToExerciseList(next)

  }
}
