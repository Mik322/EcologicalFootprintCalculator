package graphicalInterface.footprintCalculator.transportation

import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, DatePicker, Label, TextField}
import javafx.scene.layout.VBox
import main.Date
import main.footprint.FootPrintOps
import main.footprint.transport.{Car, TransportMean}
import main.footprint.transport.TransportMean.{Bus, Plane, SubWay, Train}
import main.footprint.transport.TransportTrip.history

class TransportTrip {

  private var homePage: HomePage = _

  private var meansOfTransport: ObservableList[TransportMean] = FXCollections.observableArrayList(Car, Plane, Bus, SubWay, Train)

  @FXML
  var means: ChoiceBox[TransportMean] = new ChoiceBox[TransportMean]()
  @FXML
  var kms: TextField = _
  @FXML
  var date: DatePicker = _
  @FXML
  var add_trip: Button = _
  @FXML
  var see_trips: Button = _
  @FXML
  var car_box: VBox = _
  @FXML
  var enter_car: Label = new Label("Please choose the car of your trip")
  @FXML
  var car: ChoiceBox[String] = new ChoiceBox[String]()

  @FXML
  def initialize(homePage: HomePage) = {
    this.homePage = homePage
    means.setItems(meansOfTransport)
  }

  //Test Func
  def ClearHistory() = {
    val newFoot = homePage.getFootPrint.copy(transportTrips = Nil)
    homePage.updateFootPrint(footPrintState = newFoot)
  }

  def AddTrip() = {
    means.getValue match {
      case Car => AddCarTrip()
      case _ => AddPublicT()
    }
  }

  def AddCarTrip(): Unit = {
    val find_car = homePage.getFootPrint.cars.find(c => c.name == car.getValue)
    find_car match {
      case None =>
      case Some(value) => {
        val mean = Car(value.name, value.consumption, value.fuel)
        val newFootPrint = FootPrintOps.addTrip(homePage.getFootPrint, mean, kms.getText.toInt, Date(date.getValue))
        homePage.updateFootPrint(footPrintState = newFootPrint)
      }
    }
  }

  def AddPublicT(): Unit = {
    val newFootPrint = FootPrintOps.addTrip(homePage.getFootPrint, means.getValue, kms.getText.toInt, Date(date.getValue))
    homePage.updateFootPrint(footPrintState = newFootPrint)
  }

  def TripsHistory = {
    println(history(homePage.getFootPrint.transportTrips))
  }

  def CheckCar() = {
    if (car_box.getChildren.isEmpty) {
      means.getValue match {
        case Car => {
          car_box.getChildren.add(enter_car)
          car_box.getChildren.add(car)
          addCars(homePage.getFootPrint.cars)
        }
        case _ =>
      }
    } else {
      if (means.getValue != Car) {
        println("Not a car")
        car_box.getChildren.remove(enter_car)
        car_box.getChildren.remove(car)
      }
    }
  }

  def addCars(list: List[Car]): Unit = list match {
    case ::(head, next) => {
      car.getItems.add(head.name)
      addCars(next)
    }
    case Nil =>
  }

}
