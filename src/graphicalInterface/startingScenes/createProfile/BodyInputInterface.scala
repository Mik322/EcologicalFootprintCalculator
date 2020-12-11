package graphicalInterface.startingScenes.createProfile

import consoleinterface.StartOptions.{BodyParams, FootPrintData, NewProfile}
import graphicalInterface.FxApp
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{ChoiceBox, RadioButton, TextField, ToggleGroup}
import main.healthTracker.Body._
import main.{Date, States}

class BodyInputInterface {

  @FXML
  var height: TextField = _
  @FXML
  var weight: TextField = _
  @FXML
  var age: TextField = _
  @FXML
  var male: RadioButton = _
  @FXML
  var female: RadioButton = _
  @FXML
  var lifestyleChoice: ChoiceBox[Lifestyle] = new ChoiceBox[Lifestyle]()

  var sexGroup = new ToggleGroup

  @FXML
  def initialize(): Unit = {
    val list: ObservableList[Lifestyle] = FXCollections.observableArrayList(Sedentary, Moderated, Active, VeryActive, ExtremelyActive)
    lifestyleChoice.setItems(list)
    male.setToggleGroup(sexGroup)
    female.setToggleGroup(sexGroup)
    male.setSelected(true)
  }

  private var footPrintData: FootPrintData = _
  private var username: String = _


  def setData(username: String, footPrintData: FootPrintData): Unit = {
    this.username = username
    this.footPrintData = footPrintData
  }

  def createProfile(): Unit = {
    val sex = if (male.isSelected) Male else Female
    val bodyParams = BodyParams(height.getText.toInt, weight.getText.toInt, age.getText.toInt, sex, lifestyleChoice.getValue, Date.today())
    val states = States.createStates(NewProfile(username, bodyParams, footPrintData))
    val loader = new FXMLLoader(getClass.getResource("../../HomePage.fxml"))
    val root: Parent = loader.load()
    FxApp.setStates(states)
    lifestyleChoice.getScene.setRoot(root)
  }
}
