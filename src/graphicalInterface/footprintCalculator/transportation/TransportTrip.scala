package graphicalInterface.footprintCalculator.transportation

import graphicalInterface.FxApp
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control._
import javafx.scene.layout.VBox
import main.Date
import main.footprint.FootPrintOps
import main.footprint.transport.TransportMean.{Bus, Plane, SubWay, Train}
import main.footprint.transport.TransportTrip.history
import main.footprint.transport.{Car, TransportMean}

class TransportTrip {
  private val meansOfTransport: ObservableList[TransportMean] = FXCollections.observableArrayList(Car, Plane, Bus, SubWay, Train)

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
  var missing_values: Label = _
  @FXML
  var invalid_char: Label = _
  @FXML
  var success_label: Label = _

  @FXML
  def initialize(): Unit = {
    means.setItems(meansOfTransport)
  }

  def AddTrip() = {
    if (means.getValue == null || kms.getText().isBlank || date.getValue == null) MissingValues()
    else {
      missing_values.setText("")
      try{
      means.getValue match {
        case Car => AddCarTrip()
        case _ => AddPublicT()
      }
    }catch {
        case _: NumberFormatException => InvalidChar()
      }
    }
  }

  def AddCarTrip(): Unit = {
    if (car.getValue == null) MissingValues()
    else {
      val find_car = FxApp.getFootPrint.cars.find(c => c.name == car.getValue)
      find_car match {
        case None =>
        case Some(value) => {
          val mean = Car(value.name, value.consumption, value.fuel)
          val newFootPrint = FootPrintOps.addTrip(FxApp.getFootPrint, mean, kms.getText.toInt, Date(date.getValue))
          FxApp.updateFootPrint(footPrintState = newFootPrint)
          Success()
        }
      }
    }
  }

  def AddPublicT(): Unit = {
    val newFootPrint = FootPrintOps.addTrip(FxApp.getFootPrint, means.getValue, kms.getText.toInt, Date(date.getValue))
    FxApp.updateFootPrint(footPrintState = newFootPrint)
    Success()
  }

  def TripsHistory(): Unit = {
    println(history(FxApp.getFootPrint.transportTrips))
  }

  def CheckCar(): Unit = {
    if (car_box.getChildren.isEmpty) {
      means.getValue match {
        case Car =>
          car_box.getChildren.add(enter_car)
          car_box.getChildren.add(car)
          addCars(FxApp.getFootPrint.cars)
        case _ =>
      }
    } else {
      if (means.getValue != Car) {
        car_box.getChildren.remove(enter_car)
        car_box.getChildren.remove(car)
      }
    }
  }

  def addCars(list: List[Car]): Unit = list match {
    case ::(head, next) =>
      car.getItems.add(head.name)
      addCars(next)
    case Nil =>
  }

  def InvalidChar() = {
    invalid_char.setText("Invalid Character. Please try again")
    success_label.setText("")
  }

  def MissingValues() = {
    missing_values.setText("You need to fill every parameter in order to add a transportation trip")
    success_label.setText("")
  }

  def Success() = {
    success_label.setText("Your trip has been added with success!")
    invalid_char.setText("")
    missing_values.setText("")
    means.getItems.clear()
    kms.clear()
    date.setValue(null)
  }

}
