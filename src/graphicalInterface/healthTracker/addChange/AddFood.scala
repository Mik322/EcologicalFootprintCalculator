package graphicalInterface.healthTracker.addChange


import consoleinterface.healthtracker.options.AddCaloricActivity.AddFood
import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, Label, TextField}
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
  @FXML
  var foodErrorLabel: Label = _
  @FXML
  var weightErrorLabel: Label = _
  @FXML
  var addedLabel: Label = _


  def addFood() = {
    foodErrorLabel.setVisible(false)
    weightErrorLabel.setVisible(false)
    addedLabel.setVisible(false)
    if (foodChoice.getValue == null) {
      foodErrorLabel.setVisible(true)
    }
    else {
      try {
        val foodQuant = foodQuantity.getText().toInt
        if (foodQuant <= 0)
          weightErrorLabel.setVisible(true)
        else {
          val food = AddFood(foodChoice.getValue, foodQuant, Date.today)
          val healthTracker = home.getHealthTracker
          val newHealthTracker = addCaloricActivityToState(food, healthTracker, caloricMaps)
          home.updateHealthTracker(newHealthTracker)
          addedLabel.setVisible(true)
        }
      } catch {
        case _: NumberFormatException => weightErrorLabel.setVisible(true)
      }
    }
  }

  def addToFoodList(list: List[String]): Unit = list match {
    case Nil =>
    case value :: next =>
      foodChoice.getItems.add(value)
      addToFoodList(next)

  }

}
