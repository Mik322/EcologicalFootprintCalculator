package graphicalInterface.healthTracker.addChange


import consoleinterface.healthtracker.options.AddCaloricActivity.AddFood
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState

class AddFood {
  @FXML
  def initialize(): Unit = {
    addToFoodList(FxApp.caloricMaps.foodMap.keys.toList.sorted)
  }
  @FXML
  var foodChoice: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  var foodQuantity: TextField = _


  def addFood(): Unit ={
    val food = AddFood(foodChoice.getValue,foodQuantity.getText().toInt,Date.today())
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(food,healthTracker,FxApp.caloricMaps)
    FxApp.updateHealthTracker(newHealthTracker)
  }

  def addToFoodList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      foodChoice.getItems.add(value)
      addToFoodList(next)

  }

}
