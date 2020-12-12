package graphicalInterface.footprintCalculator.transportation

import graphicalInterface.FxApp
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import main.States.FootPrintState
import main.footprint.FootPrintOps.gramOrKg
import main.footprint.transport.TransportTrip

class TransportInformation {
  @FXML
  var elements: VBox = _
  @FXML
  var total_emissions: Label = _
  @FXML
  var no_emissions: Label = new Label("You don't have any trips yet. Head over to Add Transport Trip")
  @FXML
  var emissions_menu: VBox = _

  @FXML
  def initialize(): Unit = {
    if (FxApp.getFootPrint.transportTrips.isEmpty) noTrips()
    else {
      addInformation(FxApp.getFootPrint)
      val emissions = TransportTrip.getTotalEmissions(FxApp.getFootPrint.transportTrips).toInt
      total_emissions.setText(gramOrKg(emissions))
    }
  }

  def noTrips(): Unit = {
    emissions_menu.getChildren.clear()
    emissions_menu.getChildren.add(no_emissions)
  }

  def addInformation(footPrint: FootPrintState): Unit = {
    val trips = footPrint.transportTrips
    trips match {
      case ::(head, next) =>
        addElement(head, footPrint)
        val new_trips = footPrint.copy(transportTrips = next)
        addInformation(new_trips)
      case Nil =>
    }
  }

  def addElement(trip: TransportTrip, footPrint: FootPrintState): Unit = {
    val mean = trip.mean
    val kms = trip.km
    val emissions = TransportTrip.getEmissionsByType(trip).toInt
    val loader = new FXMLLoader(getClass.getResource("Template.fxml"))
    elements.getChildren.add(loader.load())
    loader.getController[Template].initialize(mean.toString, gramOrKg(emissions), kms.toInt.toString)
  }

}
