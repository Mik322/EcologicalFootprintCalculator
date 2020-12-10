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

  def initialize(firstParameter: String, secondParameter: String, thirdParameter: String): Unit = {
    first.setText(firstParameter)
    second.setText(secondParameter)
    third.setText(thirdParameter)
  }

}
