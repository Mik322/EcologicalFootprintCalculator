package graphicalInterface.startingScenes.newprofile

import consoleinterface.StartOptions.{BodyParams, FootPrintData, NewProfile}
import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{ChoiceBox, RadioButton, TextField}
import main.{Date, States}
import main.healthTracker.Body.{Active, ExtremelyActive, Female, Lifestyle, Male, Moderated, Sedentary, VeryActive}

class BodyInput {

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

  @FXML
  def initialize(): Unit = {
    val list: ObservableList[Lifestyle] = FXCollections.observableArrayList(Sedentary, Moderated, Active, VeryActive, ExtremelyActive)
    lifestyleChoice.setItems(list)
  }

  private var footPrintData: FootPrintData = _
  private var username: String = _


  def setData(username: String, footPrintData: FootPrintData): Unit = {
    this.username = username
    this.footPrintData = footPrintData
  }

  def createProfile(): Unit = {
    val sex = if (male.isArmed) Male else Female
    val bodyParams = BodyParams(height.getText.toInt, weight.getText.toInt, age.getText.toInt, sex, lifestyleChoice.getValue, Date.today())
    val states = States.createStates(NewProfile(username, bodyParams, footPrintData))
    val loader = new FXMLLoader(getClass.getResource("../../HomePage.fxml"))
    val root: Parent = loader.load()
    loader.getController[HomePage].initialize(states)
    lifestyleChoice.getScene.setRoot(root)
  }
}
