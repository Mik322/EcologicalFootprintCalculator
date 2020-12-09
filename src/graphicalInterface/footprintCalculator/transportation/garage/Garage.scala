package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import main.footprint.transport.Car

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

  def editGarage() ={
    val loader = new FXMLLoader(getClass.getResource("EditGarage.fxml"))
    garageDisplay.getChildren.clear()
    garageDisplay.getChildren.add(loader.load())
    loader.getController[garage.EditGarage].initialize(homePage)
  }

  def displayInformationByCar() ={
    val loader = new FXMLLoader(getClass.getResource("InformationByCar.fxml"))
    garageDisplay.getChildren.clear()
    garageDisplay.getChildren.add(loader.load())
    loader.getController[garage.InformationByCar].initialize(homePage)
  }

  def information() ={
    val loader = new FXMLLoader(getClass.getResource("Informations.fxml"))
    garageDisplay.getChildren.clear()
    garageDisplay.getChildren.add(loader.load())
    loader.getController[garage.Informations].initialize(homePage)
  }

}
