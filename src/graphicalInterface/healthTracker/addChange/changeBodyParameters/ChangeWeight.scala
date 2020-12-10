package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import main.Date
import main.healthTracker.{Body, CaloricMaps}

class ChangeWeight {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var weightInput : TextField = _
  @FXML
  var weightErrorLabel : Label = _
  @FXML
  var changedLabel : Label = _


  def changeWeight() ={
    weightErrorLabel.setVisible(false)
    changedLabel.setVisible(false)
    try {
      val weightValue = weightInput.getText.toInt
      if (weightValue <= 0)
        weightErrorLabel.setVisible(true)
      else {
        val healthTracker = home.getHealthTracker
        val newHealthTracker = Body.changeBody(BodyChange.ChangeWeight(weightValue, Date.today), healthTracker)
        home.updateHealthTracker(newHealthTracker)
        changedLabel.setVisible(true)
      }
    } catch {
      case _: NumberFormatException => weightErrorLabel.setVisible(true)
    }
  }

}
