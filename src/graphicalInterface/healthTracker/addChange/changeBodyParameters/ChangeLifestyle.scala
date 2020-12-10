package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{RadioButton, ToggleGroup}
import main.Date
import main.healthTracker.Body.{Active, ExtremelyActive, Moderated, Sedentary, VeryActive}
import main.healthTracker.{Body, CaloricMaps}

class ChangeLifestyle {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
    home.getHealthTracker.body.lifestyle match {
      case Sedentary => sedentary.setSelected(true)
      case Moderated => moderated.setSelected(true)
      case Active => active.setSelected(true)
      case VeryActive => veryActive.setSelected(true)
      case ExtremelyActive => extremelyActive.setSelected(true)
    }
  }

  @FXML
  var sedentary: RadioButton = _
  @FXML
  var moderated: RadioButton = _
  @FXML
  var active: RadioButton = _
  @FXML
  var veryActive: RadioButton = _
  @FXML
  var extremelyActive: RadioButton = _

  var lifestyleGroup = new ToggleGroup

  @FXML
  def initialize() ={
    sedentary.setToggleGroup(lifestyleGroup)
    moderated.setToggleGroup(lifestyleGroup)
    active.setToggleGroup(lifestyleGroup)
    veryActive.setToggleGroup(lifestyleGroup)
    extremelyActive.setToggleGroup(lifestyleGroup)
  }

  def changeLifestyle() = {
    val nlifestyle = lifestyleGroup.getSelectedToggle.asInstanceOf[RadioButton].getId match{
      case "sedentary" => Sedentary
      case "moderated" => Moderated
      case "active" => Active
      case "veryActive" => VeryActive
      case "extremelyActive" => ExtremelyActive
    }
    val healthTracker = home.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeLifestyle(nlifestyle),healthTracker)
    home.updateHealthTracker(newHealthTracker)
  }
}
