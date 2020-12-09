package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.{AddDrink, AddFood}
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, TextField}
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


  def addDrink() ={
    val drink = AddDrink(drinkChoice.getValue,drinkQuantity.getText().toInt,Date.today)
    val healthTracker = home.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(drink,healthTracker,caloricMaps)
    home.updateHealthTracker(newHealthTracker)
  }

  def addToDrinkList(list: List[String]):Unit  = list match {
    case Nil =>
    case value::next =>
      drinkChoice.getItems.add(value)
      addToDrinkList(next)

  }

}
