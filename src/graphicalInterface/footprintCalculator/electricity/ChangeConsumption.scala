package graphicalInterface.footprintCalculator.electricity

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import javafx.scene.paint.Color

class ChangeConsumption {
  @FXML
  private var consumption: TextField = _
  @FXML
  private var infoLabel: Label = _

  private var homePage: HomePage = _

  def setHomePage(homePage: HomePage): Unit = this.homePage = homePage

  def changeConsumption(): Unit = {
    try {
      val kWh = consumption.getText.toInt
      val newElectricity = homePage.getFootPrint.electricity.copy(monthlyConsumption = kWh)
      homePage.updateFootPrint(homePage.getFootPrint.copy(electricity = newElectricity))
      infoLabel.setText("Changed")
      infoLabel.setTextFill(Color.BLACK)
    } catch {
      case _: NumberFormatException =>
        infoLabel.setText("Not a valid number")
        infoLabel.setTextFill(Color.RED)
    }
  }
}
