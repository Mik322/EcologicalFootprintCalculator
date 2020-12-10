package graphicalInterface.healthTracker.addChange


import consoleinterface.healthtracker.options.AddCaloricActivity.AddFood
import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState

class AddFood {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
    addToFoodList(FxApp.caloricMaps.foodMap.keys.toList.sorted)
  }

  @FXML
  var foodChoice: ChoiceBox[String] = new ChoiceBox[String]()

  @FXML
  var foodQuantity: TextField = _


  def addFood(): Unit ={
    val food = AddFood(foodChoice.getValue,foodQuantity.getText().toInt,Date.today)
    val healthTracker = home.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(food,healthTracker,FxApp.caloricMaps)
    home.updateHealthTracker(newHealthTracker)
  }

  def addToFoodList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      foodChoice.getItems.add(value)
      addToFoodList(next)

  }

}
