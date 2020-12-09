package graphicalInterface.healthTracker.addChange


import consoleinterface.healthtracker.options.AddCaloricActivity.AddFood
import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState
import main.healthTracker.CaloricMaps

class AddFood {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
    addToFoodList(caloricMaps.foodMap.keys.toList.sorted)
  }

  @FXML
  var foodChoice: ChoiceBox[String] = new ChoiceBox[String]()

  @FXML
  var foodQuantity: TextField = _


  def addFood() ={
    val food = AddFood(foodChoice.getValue,foodQuantity.getText().toInt,Date.today)
    val healthTracker = home.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(food,healthTracker,caloricMaps)
    home.updateHealthTracker(newHealthTracker)
  }

  def addToFoodList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      foodChoice.getItems.add(value)
      addToFoodList(next)

  }

}
