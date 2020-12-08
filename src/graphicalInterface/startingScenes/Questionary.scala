package graphicalInterface

import consoleinterface.footprint.FootPrintQuestions
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{CheckBox, ChoiceBox, ListView, TextField}

class Questionary {

  //private var distance_car_list: ObservableList[String] = FXCollections.observableArrayList("More than 2000", "Between 1250 and 2000", "Between 125 and 1250", "Less than 125", "0")
  private var distance_publicT_list: ObservableList[String] = FXCollections.observableArrayList("More than 32000", "Between 25000 and 32000", "Between 15000 and 25000", "Between 1500 and 15000","Less than 1500", "0")
  private var holidayDest_list: ObservableList[String] = FXCollections.observableArrayList("Close to home(Country)", "Short distance away(Continent)", "Long flight away(Rest of the world)")
  private var averageGasBill_list: ObservableList[String] = FXCollections.observableArrayList("More than 280", "Between 170 and 280", "Between 50 and 170", "Less than 50")
  private var sourceOfEnergy_list: ObservableList[String] = FXCollections.observableArrayList("Renewable / Green Tariff", "Non-Renewable")
  private var typeOfEater_list: ObservableList[String] = FXCollections.observableArrayList("Vegan", "Vegetarian", "Regular meat eater", "Heavy meat eater")
  private var typeOfFood_list: ObservableList[String] = FXCollections.observableArrayList("Mostly fresh, locally grown", "Mix of fresh and convenience", "Mostly convenience")
  private var magazines_list: ObservableList[String] = FXCollections.observableArrayList("More than 20", "Between 10 and 20", "Between 1 and 10", "None")
  private var purchases_list: ObservableList[String] = FXCollections.observableArrayList("More than 7","Between 5 and 7", "Between 3 and 5", "Less than 3", "Hardly any, or second hand")
  private var typeOfProperty_list: ObservableList[String] = FXCollections.observableArrayList("Large sized house", "Medium size house", "Small size house", "Flat / apartment")
  private var household_list: ObservableList[String] = FXCollections.observableArrayList("No other person", "One other person", "Two other person", "Three other person", "Four other person", "Five other person", "More than five people")
  private var childrenHousehold_list: ObservableList[String] = FXCollections.observableArrayList("No children", "One child", "Two children", "Three children", "Four children", "More than four children")
  private var amountOfWaste_list: ObservableList[String] = FXCollections.observableArrayList("More than 120kg", "Between 90 and 120kg", "Between 60 and 90kg", "Between 30 and 60kg", "Between 15 and 30kg", "Less than 15kg")
  private var dishWasher_list: ObservableList[String] = FXCollections.observableArrayList("More than 9 times", "Between 4 and 9 times", "Between 1 and 4 times", "Not applicable")
  private var washingMachine_list: ObservableList[String] = FXCollections.observableArrayList("More than 9 times", "Between 4 and 9 times", "Between 1 and 4 times", "Not applicable")

  @FXML
  private var distance_car: TextField = _
  @FXML
  private var distance_publicT: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var holidayDest: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var averageGasBill: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var averageElectricBill: TextField = _
  @FXML
  private var sourceOfEnergy: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var typeOfEater: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var typeOfFood: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var magazines: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var purchases: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var typeOfProperty: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var household: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var childrenHousehold: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var amountOfWaste: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var dishWasher: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var washingMachine: ChoiceBox[String] = new ChoiceBox[String]()
  @FXML
  private var glass: CheckBox = _
  @FXML
  private var plastic: CheckBox = _
  @FXML
  private var paper: CheckBox = _
  @FXML
  private var aluminium: CheckBox = _
  @FXML
  private var steelCans: CheckBox = _
  @FXML
  private var foodWaste: CheckBox = _
  @FXML
  private var recycledWasteList: List[CheckBox] = _


  @FXML
  def initialize = {
    distance_publicT.setItems(distance_publicT_list)
    holidayDest.setItems(holidayDest_list)
    averageGasBill.setItems(averageGasBill_list)
    sourceOfEnergy.setItems(sourceOfEnergy_list)
    typeOfEater.setItems(typeOfEater_list)
    typeOfFood.setItems(typeOfFood_list)
    magazines.setItems(magazines_list)
    purchases.setItems(purchases_list)
    typeOfProperty.setItems(typeOfProperty_list)
    household.setItems(household_list)
    childrenHousehold.setItems(childrenHousehold_list)
    amountOfWaste.setItems(amountOfWaste_list)
    dishWasher.setItems(dishWasher_list)
    washingMachine.setItems(washingMachine_list)
    recycledWasteList = List(glass, plastic, paper, aluminium, steelCans, foodWaste)
  }

  @FXML
  def submit = {
    val points = FootPrintQuestions.distanceOfCar(distance_car.getText.toInt) +
      FootPrintQuestions.distanceOfPublicT(distance_publicT_list,distance_publicT.getValue) +
      FootPrintQuestions.holidayDestPoints(holidayDest_list, holidayDest.getValue) +
      FootPrintQuestions.averageGasBillPoints(averageGasBill_list, averageGasBill.getValue) +
      FootPrintQuestions.averageElectricBillPoints(averageElectricBill.getText.toDouble) +
      FootPrintQuestions.sourceOfEnergyPoints(sourceOfEnergy_list, sourceOfEnergy.getValue) +
      FootPrintQuestions.typeOfEaterPoints(typeOfEater_list, typeOfEater.getValue) +
      FootPrintQuestions.typeOfFoodPoints(typeOfFood_list, typeOfFood.getValue) +
      FootPrintQuestions.magazinesPoints(magazines_list, magazines.getValue) +
      FootPrintQuestions.purchasesPoints(purchases_list, purchases.getValue) +
      FootPrintQuestions.typeOfPropertyPoints(typeOfProperty_list, typeOfProperty.getValue) +
      FootPrintQuestions.householdPoints(household_list, household.getValue) +
      FootPrintQuestions.childrenHouseholdPoints(childrenHousehold_list, childrenHousehold.getValue) +
      FootPrintQuestions.amountOfWastePoints(amountOfWaste_list, amountOfWaste.getValue) +
      FootPrintQuestions.dishwasherPoints(dishWasher_list, dishWasher.getValue) +
      FootPrintQuestions.washingMachinePoints(washingMachine_list, washingMachine.getValue) +
      FootPrintQuestions.recycledWastePoints(recycledWasteList)
  }
}
