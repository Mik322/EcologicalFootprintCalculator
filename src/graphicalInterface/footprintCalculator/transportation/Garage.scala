package graphicalInterface.footprintCalculator.transportation

import javafx.fxml.FXML
import javafx.scene.control.Label

class Garage {
  @FXML
  var garageLabel : Label = _

  def addCarDisplay() ={
    garageLabel.setText("addCarDisplay")
  }

  def editCarDisplay() ={
    garageLabel.setText("editCarDisplay")
  }

  def deleteCarDisplay() ={
    garageLabel.setText("deleteCarDisplay")
  }

  def seeTotalEmissionsByCarDisplay() ={
    garageLabel.setText("seeTotalEmissionsByCarDisplay")
  }

  def seeTotalKmMadeByCarDisplay() ={
    garageLabel.setText("seeTotalKmMadeByCarDisplay")
  }

  def seeTotalEmissionsFromCarsDisplay() ={
    garageLabel.setText("seeTotalEmissionsFromCarsDisplay")
  }

  def seeCarsDisplay() ={
    garageLabel.setText("seeCarsDisplay")
  }

  def getMonthlyFuelConsumptionDisplay() ={
    garageLabel.setText("getMonthlyFuelConsumptionDisplay")
  }

  def seeMonthlyCarEmissionDisplay() ={
    garageLabel.setText("seeMonthlyCarEmissionDisplay")
  }
}
