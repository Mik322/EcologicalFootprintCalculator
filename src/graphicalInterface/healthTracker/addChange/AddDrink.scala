package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddDrink
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState

class AddDrink {
  @FXML
  var drinkChoice: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  var drinkQuantity: TextField = _
  @FXML
  def initialize(): Unit = {
    addToDrinkList(FxApp.caloricMaps.drinksMap.keys.toList.sorted)
  }


  def addDrink(): Unit ={
    val drink = AddDrink(drinkChoice.getValue,drinkQuantity.getText().toInt,Date.today())
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(drink,healthTracker,FxApp.caloricMaps)
    FxApp.updateHealthTracker(newHealthTracker)
  }

  def addToDrinkList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      drinkChoice.getItems.add(value)
      addToDrinkList(next)

  }

}
