package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.TextField
import main.Date
import main.healthTracker.{Body, CaloricMaps}

class ChangeAge {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  @FXML
  var ageInput : TextField = _

  def changeAge(): Unit ={
    val healthTracker = home.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeAge(ageInput.getText.toInt),healthTracker)
    home.updateHealthTracker(newHealthTracker)
  }
}
