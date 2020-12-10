package graphicalInterface.footprintCalculator.waste

import graphicalInterface.{FxApp}
import javafx.fxml.FXML
import javafx.scene.control.{ChoiceBox, Label, TextField}
import javafx.scene.paint.Color
import main.footprint.FootPrintOps
import main.footprint.waste.{TypeOfWaste, Waste}
import main.footprint.waste.TypeOfWaste.{Food, Recycled}

class AddWaste {

  @FXML
  private var quantity: TextField = _
  @FXML
  private var wasteType: ChoiceBox[TypeOfWaste] = new ChoiceBox[TypeOfWaste]()
  @FXML
  private var infoLabel: Label = _
  @FXML
  private var emissions: Label = _
  @FXML
  private var totalOrganic: Label = _
  @FXML
  private var totalRecycled: Label = _

  @FXML
  def initialize(): Unit = {
    wasteType.getItems.addAll(Recycled, Food)
    setTotals()
  }

  def setTotals(): Unit = {
    val waste = FxApp.getFootPrint.waste
    emissions.setText(s"${Waste.getTotalEmissions(waste)}g CO2")
    totalOrganic.setText(s"${waste.foodWaste}kg")
    totalRecycled.setText(s"${waste.recycledWaste}kg")
  }

  def addWaste(): Unit = {
    if (wasteType.getValue == null || quantity.getText.isBlank) {
      infoLabel.setText("You need to fill every parameter in order to add a waste")
      infoLabel.setTextFill(Color.RED)
    } else {
      try {
        val kg = quantity.getText.toInt
        val waste = wasteType.getValue
        val newFootPrint = FootPrintOps.addWaste(FxApp.getFootPrint, kg, waste)
        FxApp.updateFootPrint(newFootPrint)
        infoLabel.setText("Added")
        infoLabel.setTextFill(Color.BLACK)
        setTotals()
      } catch {
        case _: NumberFormatException =>
          infoLabel.setText("Number not valid")
          infoLabel.setTextFill(Color.RED)
      }
    }
  }
}
