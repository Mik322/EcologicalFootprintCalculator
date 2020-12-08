package graphicalInterface.footprintCalculator.waste

import javafx.fxml.FXML
import javafx.scene.control.Label

class Waste {
  @FXML
  var wasteLabel : Label = _

  def addRecycledWasteDisplay() ={
    wasteLabel.setText("addRecycledWasteDisplay")
  }

  def addFoodWasteDisplay() ={
    wasteLabel.setText("addFoodWasteDisplay")

  }

  def seeTotalEmissionsFromWasteDisplay() ={
    wasteLabel.setText("seeTotalEmissionsFromWasteDisplay")
  }
}
