package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp
import graphicalInterface.footprintCalculator.transportation.Template
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import main.States.FootPrintState
import main.footprint.FootPrintOps.gramOrKg
import main.footprint.transport.Car

class InformationByCar {

  @FXML
  var elements: VBox = _
  @FXML
  var total_emissions: Label = _

  @FXML
  def initialize(): Unit = {
    addInformation(FxApp.getFootPrint)
    val emissions = Car.getTotalEmissions(FxApp.getFootPrint.transportTrips).toInt
    total_emissions.setText(gramOrKg(emissions))
  }

  def addInformation(footPrint: FootPrintState): Unit = {
    val cars = footPrint.cars
    cars match {
      case ::(head, next) =>
        addElement(head, footPrint)
        val new_cars = footPrint.copy(cars = next)
        addInformation(new_cars)
      case Nil =>
    }
  }

  def addElement(car: Car, footPrint: FootPrintState): Unit = {
    val emissions = Car.getEmissionByCar(footPrint.transportTrips, car.name).toInt
    val kms = Car.getKmByCar(footPrint.transportTrips, car.name)
    val loader = new FXMLLoader(getClass.getResource("../Template.fxml"))
    elements.getChildren.add(loader.load())
    loader.getController[Template].initialize(car.name, gramOrKg(emissions), kms.toInt.toString)
  }
}
