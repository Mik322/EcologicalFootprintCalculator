package graphicalInterface.startingScenes.createProfile

import consoleinterface.StartOptions.FootPrintData
import consoleinterface.footprint.FootPrintQuestions
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{CheckBox, ChoiceBox, Label, TextField}

import scala.annotation.tailrec

class Questionnaire {

  private val distance_publicT_list: ObservableList[String] = FXCollections.observableArrayList("More than 32000", "Between 25000 and 32000", "Between 15000 and 25000", "Between 1500 and 15000","Less than 1500", "0")
  private val holidayDest_list: ObservableList[String] = FXCollections.observableArrayList("Close to home(Country)", "Short distance away(Continent)", "Long flight away(Rest of the world)")
  private val averageGasBill_list: ObservableList[String] = FXCollections.observableArrayList("More than 280", "Between 170 and 280", "Between 50 and 170", "Less than 50")
  private val sourceOfEnergy_list: ObservableList[String] = FXCollections.observableArrayList("Renewable / Green Tariff", "Non-Renewable")
  private val typeOfEater_list: ObservableList[String] = FXCollections.observableArrayList("Vegan", "Vegetarian", "Regular meat eater", "Heavy meat eater")
  private val typeOfFood_list: ObservableList[String] = FXCollections.observableArrayList("Mostly fresh, locally grown", "Mix of fresh and convenience", "Mostly convenience")
  private val magazines_list: ObservableList[String] = FXCollections.observableArrayList("More than 20", "Between 10 and 20", "Between 1 and 10", "None")
  private val purchases_list: ObservableList[String] = FXCollections.observableArrayList("More than 7","Between 5 and 7", "Between 3 and 5", "Less than 3", "Hardly any, or second hand")
  private val typeOfProperty_list: ObservableList[String] = FXCollections.observableArrayList("Large sized house", "Medium size house", "Small size house", "Flat / apartment")
  private val household_list: ObservableList[String] = FXCollections.observableArrayList("No other person", "One other person", "Two other person", "Three other person", "Four other person", "Five other person", "More than five people")
  private val childrenHousehold_list: ObservableList[String] = FXCollections.observableArrayList("No children", "One child", "Two children", "Three children", "Four children", "More than four children")
  private val amountOfWaste_list: ObservableList[String] = FXCollections.observableArrayList("More than 120kg", "Between 90 and 120kg", "Between 60 and 90kg", "Between 30 and 60kg", "Between 15 and 30kg", "Less than 15kg")
  private val dishWasher_list: ObservableList[String] = FXCollections.observableArrayList("More than 9 times", "Between 4 and 9 times", "Between 1 and 4 times", "Not applicable")
  private val washingMachine_list: ObservableList[String] = FXCollections.observableArrayList("More than 9 times", "Between 4 and 9 times", "Between 1 and 4 times", "Not applicable")

  @FXML
  private var distance_car: TextField = _
  @FXML
  private var distance_publicT: ChoiceBox[String] = new ChoiceBox[String](distance_publicT_list)
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
  private var glass, plastic, paper, aluminium, steelCans, foodWaste: CheckBox = _
  @FXML
  private var recycledWasteList: List[CheckBox] = _
  @FXML
  private var infoLabel, electricityError, kmError: Label = _

  private var username: String = _

  def setUsername(username: String): Unit = this.username = username

  @FXML
  def initialize(): Unit = {
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

  def getChoiceBoxes: (List[ChoiceBox[String]], List[ObservableList[String]]) = {
    val choiceBoxes = List(distance_publicT, holidayDest, averageGasBill, sourceOfEnergy, typeOfEater, typeOfFood, magazines, purchases, typeOfProperty,
      household, childrenHousehold, amountOfWaste, dishWasher, washingMachine)
    val choiceValues = List(distance_publicT_list, holidayDest_list, averageGasBill_list, sourceOfEnergy_list, typeOfEater_list, typeOfFood_list, magazines_list, purchases_list,
      typeOfProperty_list, household_list, childrenHousehold_list, amountOfWaste_list, dishWasher_list, washingMachine_list)
    (choiceBoxes, choiceValues)
  }

  def calcPoints: Int = {
    @tailrec
    def calcChoiceBoxPoints(choiceBoxes: List[ChoiceBox[String]], values: List[ObservableList[String]], points: Int): Int = choiceBoxes match {
      case ::(head, next) =>
        val valuePoints = FootPrintQuestions.distanceOfPublicT(values.head, head.getValue)
        calcChoiceBoxPoints(next, values.tail, points + valuePoints)
      case Nil =>points
    }
    val (choiceBoxes, choiceValues) = getChoiceBoxes
    val choicePoints = calcChoiceBoxPoints(choiceBoxes, choiceValues, 0)

    val remainingPoints = FootPrintQuestions.distanceOfCar(distance_car.getText.toInt) +
      FootPrintQuestions.averageElectricBillPoints(averageElectricBill.getText.toDouble) +
      FootPrintQuestions.recycledWastePoints(recycledWasteList)

    choicePoints + remainingPoints
  }

  private def areChoiceBoxesValid: Boolean = getChoiceBoxes._1.count(c => c.getValue == null) == 0

  private def isValidInt(string: String): Boolean = "^[0-9]+$".r.matches(string)

  private def validateQuestioner: Boolean = {
    val (choiceBoxesValid, distanceValid, electricityValid) = (areChoiceBoxesValid, isValidInt(distance_car.getText), isValidInt(averageElectricBill.getText))
    if (!choiceBoxesValid) infoLabel.setVisible(true)
      else infoLabel.setVisible(false)

    if (!distanceValid) kmError.setVisible(true)
    else kmError.setVisible(false)

    if (!electricityValid) electricityError.setVisible(true)
    else electricityError.setVisible(false)

    choiceBoxesValid && distanceValid && electricityValid
  }

  def submit(): Unit = {
    if (!validateQuestioner) return

    val loader = new FXMLLoader(getClass.getResource("BodyInputInterface.fxml"))
    val root: Parent = loader.load()
    loader.getController[BodyInputInterface].setData(username, FootPrintData(calcPoints, averageElectricBill.getText().toInt))
    val scene = distance_car.getScene
    scene.setRoot(root)
  }
}
