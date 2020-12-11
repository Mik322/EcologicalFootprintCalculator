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

  def addTrip() = {
    missing_values.setVisible(false)
    invalid_char.setVisible(false)
    success_label.setVisible(false)
    if (means.getValue == null || kms.getText().isBlank || date.getValue == null){
      missing_values.setText("You need to fill every parameter in order to add a transportation trip!")
      missing_values.setVisible(true)
    }
    else {
      if (Date(date.getValue) > Date.today()) {
        missing_values.setText("You can't choose a future date!")
        missing_values.setVisible(true)
      } else {
        try {
          val km = kms.getText.toDouble
          if (km <= 0) invalid_char.setVisible(true)
          else {
            means.getValue match {
              case Car => addCarTrip()
              case _ => addPublicT()
            }
          }
        } catch {
          case _: NumberFormatException => invalid_char.setVisible(true)
        }
      }
    }
  }


  def addCarTrip(): Unit = {
    if (car.getValue == null) {
      missing_values.setText("You need to fill every parameter in order to add a transportation trip!")
      missing_values.setVisible(true)
    }
    else {
      val find_car = FxApp.getFootPrint.cars.find(c => c.name == car.getValue)
      find_car match {
        case None =>
        case Some(value) => {
          val mean = Car(value.name, value.consumption, value.fuel)
          val newFootPrint = FootPrintOps.addTrip(FxApp.getFootPrint, mean, kms.getText.toInt, Date(date.getValue))
          FxApp.updateFootPrint(footPrintState = newFootPrint)
          success()
        }
      }
    }
  }

  def addPublicT(): Unit = {
    val newFootPrint = FootPrintOps.addTrip(FxApp.getFootPrint, means.getValue, kms.getText.toInt, Date(date.getValue))
    FxApp.updateFootPrint(footPrintState = newFootPrint)
    success()
  }

  def tripsHistory(): Unit = {
    println(history(FxApp.getFootPrint.transportTrips))
  }

  def checkCar(): Unit = {
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

  def success() = {
    success_label.setVisible(true)
    means.setValue(null)
    kms.clear()
    date.setValue(null)
  }

}
