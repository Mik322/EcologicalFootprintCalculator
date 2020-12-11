package graphicalInterface.startingScenes.newProfile

import consoleinterface.StartOptions.{BodyParams, FootPrintData, NewProfile}
import graphicalInterface.{FxApp, HomePage}
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{ChoiceBox, Label, RadioButton, TextField, ToggleGroup}
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
  var heightErrorLabel, weightErrorLabel, ageErrorLabel: Label = _

  var sexGroup = new ToggleGroup

  @FXML
  def initialize(): Unit = {
    val list: ObservableList[Lifestyle] = FXCollections.observableArrayList(Sedentary, Moderated, Active, VeryActive, ExtremelyActive)
    lifestyleChoice.setItems(list)
    male.setToggleGroup(sexGroup)
    female.setToggleGroup(sexGroup)
    male.setSelected(true)
    lifestyleChoice.setValue(Sedentary)
  }

  private var footPrintData: FootPrintData = _
  private var username: String = _


  def setData(username: String, footPrintData: FootPrintData): Unit = {
    this.username = username
    this.footPrintData = footPrintData
  }

  def createProfile(): Unit = {
    heightErrorLabel.setVisible(false)
    weightErrorLabel.setVisible(false)
    ageErrorLabel.setVisible(false)
    try {
      val heightValue = height.getText.toInt
      val weightValue = weight.getText.toDouble
      val ageValue = age.getText.toInt
      if (heightValue <= 0 || weightValue <= 0 || ageValue < 14 || ageValue > 100) {
        if (heightValue <= 0)
          heightErrorLabel.setVisible(true)
        if (weightValue <= 0)
          weightErrorLabel.setVisible(true)
        if (ageValue < 14 || ageValue > 100)
          ageErrorLabel.setVisible(true)
      } else {
        val sex = if (male.isSelected) Male else Female
        val bodyParams = BodyParams(heightValue, weightValue, ageValue, sex, lifestyleChoice.getValue, Date.today())
        val states = States.createStates(NewProfile(username, bodyParams, footPrintData))
        val loader = new FXMLLoader(getClass.getResource("../../HomePage.fxml"))
        val root: Parent = loader.load()
        FxApp.setStates(states)
        lifestyleChoice.getScene.setRoot(root)
      }
    } catch {
      case _ : NumberFormatException =>
        if(!isInt(height.getText))
          heightErrorLabel.setVisible(true)
        if(!isDouble(weight.getText))
          weightErrorLabel.setVisible(true)
        if(!isInt(age.getText))
          ageErrorLabel.setVisible(true)
    }
  }
  private def isInt(string: String): Boolean = "^[0-9]+$".r.matches(string)

  private def isDouble(string: String): Boolean = "^[0-9]+\\.?[0-9]*$".r.matches(string)
}
