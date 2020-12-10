package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{Label, TextField}
import javafx.scene.layout.Pane
import main.Date
import main.healthTracker.{Body, CaloricMaps}

class ChangeHeight {

  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var heightInput: TextField = _
  @FXML
  var heightErrorLabel: Label = _
  @FXML
  var changedLabel: Label = _

  def changeHeight() = {
    heightErrorLabel.setVisible(false)
    changedLabel.setVisible(false)
    try {
      val heightValue = heightInput.getText.toInt
      if (heightValue <= 0)
        heightErrorLabel.setVisible(true)
      else {
        val healthTracker = home.getHealthTracker
        val newHealthTracker = Body.changeBody(BodyChange.ChangeHeight(heightInput.getText.toInt), healthTracker)
        home.updateHealthTracker(newHealthTracker)
        changedLabel.setVisible(true)
      }
    } catch {
      case _: NumberFormatException => heightErrorLabel.setVisible(true)
    }
  }
}
