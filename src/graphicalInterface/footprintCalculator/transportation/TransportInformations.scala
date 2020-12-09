package graphicalInterface.footprintCalculator.transportation

import graphicalInterface.HomePage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import main.States.FootPrintState
import main.footprint.transport.{Car, TransportTrip}

class TransportInformations {
  @FXML
  var elements: VBox = _
  @FXML
  var total_emissions: Label = _
  @FXML
  var no_emissions: Label = new Label("You don't have any trips yet. Head over to Add Transport Trip")
  @FXML
  var emissions_menu: VBox = _

  private var homePage: HomePage = _

  def initialize(homePage: HomePage) = {
    this.homePage = homePage
    if (homePage.getFootPrint.transportTrips.isEmpty) NoTrips()
    else {
      addInformation(homePage.getFootPrint)
      total_emissions.setText(TransportTrip.getTotalEmissions(homePage.getFootPrint.transportTrips).toString + " g CO2")
    }
  }

  def NoTrips() = {
    println("no trips")
    emissions_menu.getChildren.clear()
    emissions_menu.getChildren.add(no_emissions)
  }

  def addInformation(footPrint: FootPrintState): Unit = {
    val trips = footPrint.transportTrips
    trips match {
      case ::(head, next) => {
        addElement(head, footPrint)
        val new_trips = footPrint.copy(transportTrips = next)
        addInformation(new_trips)
      }
      case Nil =>
    }
  }

  def addElement(trip: TransportTrip, footPrint: FootPrintState) = {
    val mean = trip.mean
    val kms = trip.km
    val emissions = TransportTrip.getEmissionsByType(trip)
    val loader = new FXMLLoader(getClass.getResource("Template.fxml"))
    elements.getChildren.add(loader.load())
    loader.getController[Template].initialize(mean.toString, emissions.toString, kms.toInt.toString)
  }

}
