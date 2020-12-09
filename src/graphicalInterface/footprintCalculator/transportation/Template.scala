package graphicalInterface.footprintCalculator.transportation

import javafx.fxml.FXML
import javafx.scene.control.Label

class Template {

  @FXML
  var first: Label = _
  @FXML
  var second: Label = _
  @FXML
  var third: Label = _

  def initialize(first_parameter: String, second_parameter: String, thirs_parameter: String) = {
    first.setText(first_parameter)
    second.setText(second_parameter)
    third.setText(thirs_parameter)
  }

}
