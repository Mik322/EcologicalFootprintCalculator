package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import main.Date
import main.healthTracker.{Body, CaloricMaps}

class ChangeAge {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var ageInput: TextField = _
  @FXML
  var ageErrorLabel: Label = _
  @FXML
  var changedLabel: Label = _

  def changeAge() = {
    ageErrorLabel.setVisible(false)
    changedLabel.setVisible(false)
    try {
      val ageValue = ageInput.getText.toInt
      if (ageValue < 14 || ageValue > 100)
        ageErrorLabel.setVisible(true)
      else {
        val healthTracker = home.getHealthTracker
        val newHealthTracker = Body.changeBody(BodyChange.ChangeAge(ageValue), healthTracker)
        home.updateHealthTracker(newHealthTracker)
        changedLabel.setVisible(true)
      }
    }
    catch {
      case _: NumberFormatException => ageErrorLabel.setVisible(true)
    }
  }

}

