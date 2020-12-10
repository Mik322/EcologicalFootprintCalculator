package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox

class Garage {
  @FXML
  var garageDisplay: VBox = _

  @FXML
  var garageLabel : Label = _

  def addCarDisplay(): Unit={
    loadPage[garage.AddCar](garageDisplay)
  }

  def editGarage(): Unit ={
    loadPage[garage.EditGarage](garageDisplay)
  }

  def displayInformationByCar(): Unit ={
    loadPage[garage.InformationByCar](garageDisplay)
  }

  def information(): Unit ={
    loadPage[garage.Informations](garageDisplay)
  }

}
