package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{RadioButton, ToggleGroup}
import main.healthTracker.Body
import main.healthTracker.Body._

class ChangeLifestyle {
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
  def initialize(): Unit = {
    sedentary.setToggleGroup(lifestyleGroup)
    moderated.setToggleGroup(lifestyleGroup)
    active.setToggleGroup(lifestyleGroup)
    veryActive.setToggleGroup(lifestyleGroup)
    extremelyActive.setToggleGroup(lifestyleGroup)

    FxApp.getHealthTracker.body.lifestyle match {
      case Sedentary => sedentary.setSelected(true)
      case Moderated => moderated.setSelected(true)
      case Active => active.setSelected(true)
      case VeryActive => veryActive.setSelected(true)
      case ExtremelyActive => extremelyActive.setSelected(true)
    }
  }

  def changeLifestyle(): Unit = {
    val nLifestyle = lifestyleGroup.getSelectedToggle.asInstanceOf[RadioButton].getId match{
      case "sedentary" => Sedentary
      case "moderated" => Moderated
      case "active" => Active
      case "veryActive" => VeryActive
      case "extremelyActive" => ExtremelyActive
    }
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeLifestyle(nLifestyle),healthTracker)
    FxApp.updateHealthTracker(newHealthTracker)
  }
}
