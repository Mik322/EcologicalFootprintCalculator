package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class Garage {
  @FXML
  var garageDisplay: Pane = _

  @FXML
  var garageLabel : Label = _

  private var homePage: HomePage = _

  def initialize(homePage: HomePage) = this.homePage = homePage

  def addCarDisplay()={
    val loader = new FXMLLoader(getClass.getResource("AddCar.fxml"))
    garageDisplay.getChildren.clear()
    garageDisplay.getChildren.add(loader.load())
    loader.getController[garage.AddCar].initialize(homePage)
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
