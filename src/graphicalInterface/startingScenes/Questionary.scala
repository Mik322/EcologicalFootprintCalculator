package graphicalInterface.startingScenes

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.ChoiceBox

class Questionary {

  private var distance_car_list: ObservableList[String] = FXCollections.observableArrayList("More than 2000", "Between 1250 and 2000", "Between 125 and 1250", "Less than 125", "0")
  private var distance_publicT_list: ObservableList[String] = FXCollections.observableArrayList("More than 32000", "Between 25000 and 32000", "Between 15000 and 25000", "Between 1500 and 15000","Less than 1500", "0")
  private var holidayDest_list: ObservableList[String] = FXCollections.observableArrayList("Close to home(Country)", "Short distance away(Continent)", "Long flight away(Rest of the world)")
  private var averageGasBill_list: ObservableList[String] = FXCollections.observableArrayList("More than 280", "Between 170 and 280", "Between 50 and 170", "Less than 50")

  @FXML
  private var distance_car: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var distance_publicT: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var holidayDest: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var averageGasBill: ChoiceBox[String] = new ChoiceBox[String]()
  private var averageElectricBill: ChoiceBox[String] = new ChoiceBox[String]()
  private var sourceOfEnergy: ChoiceBox[String] = new ChoiceBox[String]()
  private var typeOfEater: ChoiceBox[String] = new ChoiceBox[String]()
  private var typeOfFood: ChoiceBox[String] = new ChoiceBox[String]()
  private var magazines: ChoiceBox[String] = new ChoiceBox[String]()
  private var purchases: ChoiceBox[String] = new ChoiceBox[String]()
  private var typeOfProperty: ChoiceBox[String] = new ChoiceBox[String]()

  @FXML
  def initialize = {
    distance_car.setItems(distance_car_list)
    distance_publicT.setItems(distance_publicT_list)
    holidayDest.setItems(holidayDest_list)
  }

  @FXML
  def submit = {
    println(distance_car.getValue)
  }
}
