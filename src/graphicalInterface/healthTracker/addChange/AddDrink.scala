package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.{AddDrink, AddFood}
import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState

class AddDrink {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
    addToDrinkList(FxApp.caloricMaps.drinksMap.keys.toList.sorted)
  }

  @FXML
  var drinkChoice: ChoiceBox[String] = new ChoiceBox[String]()

  @FXML
  var drinkQuantity: TextField = _


  def addDrink() ={
    val drink = AddDrink(drinkChoice.getValue,drinkQuantity.getText().toInt,Date.today)
    val healthTracker = home.getHealthTracker
    //val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(drink,healthTracker,FxApp.caloricMaps)
    home.updateHealthTracker(newHealthTracker)
    //FxApp.updateHealthTracker(newHealthTracker)
  }

  def addToDrinkList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      drinkChoice.getItems.add(value)
      addToDrinkList(next)

  }

}
