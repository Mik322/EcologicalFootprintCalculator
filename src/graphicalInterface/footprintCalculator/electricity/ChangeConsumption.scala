package graphicalInterface.footprintCalculator.electricity

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import javafx.scene.paint.Color

class ChangeConsumption {
  @FXML
  private var consumption: TextField = _
  @FXML
  private var infoLabel: Label = _

  def changeConsumption(): Unit = {
    try {
      val kWh = consumption.getText.toInt
      if (kWh < 0) invalidNumber()
      else {
        val newElectricity = FxApp.getFootPrint.electricity.copy(monthlyConsumption = kWh)
        FxApp.updateFootPrint(FxApp.getFootPrint.copy(electricity = newElectricity))
        infoLabel.setText("Changed")
        infoLabel.setTextFill(Color.BLACK)
      }
    } catch {
      case _: NumberFormatException => invalidNumber()
    }
  }

  def invalidNumber() = {
    infoLabel.setText("Not a valid number")
    infoLabel.setTextFill(Color.RED)
  }
}
