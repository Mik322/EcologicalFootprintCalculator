package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.{AddDrink, AddFood}
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, Label, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState
import main.healthTracker.CaloricMaps

class AddDrink {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
    addToDrinkList(caloricMaps.drinksMap.keys.toList.sorted)
  }

  @FXML
  var drinkChoice: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  var drinkQuantity: TextField = _
  @FXML
  var drinkErrorLabel: Label = _
  @FXML
  var quantityErrorLabel: Label = _
  @FXML
  var addedLabel: Label = _

  def addDrink() = {
    drinkErrorLabel.setVisible(false)
    quantityErrorLabel.setVisible(false)
    addedLabel.setVisible(false)
    if (drinkChoice.getValue == null) {
      drinkErrorLabel.setVisible(true)
    }
    else {
      try {
        val drinkQuant = drinkQuantity.getText().toInt
        if (drinkQuant <= 0)
          quantityErrorLabel.setVisible(true)
        else {
          val drink = AddDrink(drinkChoice.getValue, drinkQuant, Date.today)
          val healthTracker = home.getHealthTracker
          val newHealthTracker = addCaloricActivityToState(drink, healthTracker, caloricMaps)
          home.updateHealthTracker(newHealthTracker)
          addedLabel.setVisible(true)
        }
      } catch {
        case _: NumberFormatException => quantityErrorLabel.setVisible(true)
      }
    }
  }

  def addToDrinkList(list: List[String]): Unit = list match {
    case Nil =>
    case value :: next =>
      drinkChoice.getItems.add(value)
      addToDrinkList(next)

  }

}
