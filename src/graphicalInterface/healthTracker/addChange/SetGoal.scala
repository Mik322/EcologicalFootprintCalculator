package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.ChoiceBox
import main.Date
import main.footprint.transport.Fuel
import main.footprint.transport.Fuel.{Diesel, Electric, Hydrogen, Petrol}
import main.healthTracker.{Body, CaloricMaps, Goal}
class SetGoal {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage,caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
    goalChoice.setItems(goalInput)
  }
  private var goalInput: ObservableList[String] = FXCollections.observableArrayList("Lose 1kg per week","Lose 0.5kg per week","Keep Weight","Gain 0.5kg per week","Gain 1kg per week")

  @FXML
  var goalChoice : ChoiceBox[String] = new ChoiceBox[String]()

  def updateGoal() ={
    val choice = goalChoice.getValue match {
      case "Lose 1kg per week" => Goal.LoseALotOfWeight
      case "Lose 0.5kg per week" => Goal.LoseWeight
      case "Keep Weight" => Goal.KeepWeight
      case "Gain 0.5kg per week" => Goal.GainWeight
      case "Gain 1kg per week" => Goal.GainALotOfWeight
    }
    val healthTracker = home.getHealthTracker
    val newHealthTracker = healthTracker.copy(goal = (choice,Date.today))
    val updatedHealthTracker = Body.changeBody(BodyChange.ChangeWeight(newHealthTracker.body.weight,Date.today()),newHealthTracker)
    home.updateHealthTracker(updatedHealthTracker)
  }
}
