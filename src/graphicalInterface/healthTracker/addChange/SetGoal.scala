package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, Label}
import main.Date
import main.healthTracker.{Body, Goal}
class SetGoal {
  private val goalInput: ObservableList[String] = FXCollections.observableArrayList("Lose 1kg per week","Lose 0.5kg per week","Keep Weight","Gain 0.5kg per week","Gain 1kg per week")
  @FXML
  var goalChoice : ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  def initialize(): Unit = {
    goalChoice.setItems(goalInput)
  }
  
  @FXML
  var goalErrorLabel: Label = _
  @FXML
  var setLabel: Label = _

  def updateGoal() ={
    goalErrorLabel.setVisible(false)
    setLabel.setVisible(false)
    val goalC = goalChoice.getValue
    if (goalC == null){
      goalErrorLabel.setVisible(true)
    }
    else {
      val choice = goalC match {
        case "Lose 1kg per week" => Goal.LoseALotOfWeight
        case "Lose 0.5kg per week" => Goal.LoseWeight
        case "Keep Weight" => Goal.KeepWeight
        case "Gain 0.5kg per week" => Goal.GainWeight
        case "Gain 1kg per week" => Goal.GainALotOfWeight
      }
      val healthTracker = FxApp.getHealthTracker
      val newHealthTracker = healthTracker.copy(goal = (choice, Date.today))
      val updatedHealthTracker = Body.changeBody(BodyChange.ChangeWeight(newHealthTracker.body.weight, Date.today()), newHealthTracker)
      FxApp.updateHealthTracker(updatedHealthTracker)
      setLabel.setVisible(true)
    }
  }
}
