package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import main.Date
import main.healthTracker.{Body, CaloricMaps}

class ChangeHeight {

  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  @FXML
  var heightInput : TextField = _

  def changeHeight() ={
    val healthTracker = home.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeHeight(heightInput.getText.toInt),healthTracker)
    home.updateHealthTracker(newHealthTracker)
  }
}
