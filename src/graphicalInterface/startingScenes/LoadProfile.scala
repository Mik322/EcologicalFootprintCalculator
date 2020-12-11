package graphicalInterface.startingScenes

import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{Label, TextField}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.stage.Stage
import main.States
import main.fileOperations.FileOperations

class LoadProfile {
  @FXML
  private var errorLabel: Label = _
  @FXML
  private var userName: TextField = _

  def loadProfile(): Unit = {
    FileOperations.loadStates(userName.getText) match {
      case None => errorLabel.setText("There is no profile with that username")
      case Some(states) => loadHomepage(states)
    }
  }

  def loadHomepage(states: States): Unit = {
    val loader = new FXMLLoader(getClass.getResource("../HomePage.fxml"))
    val root: Parent = loader.load()
    val stage = userName.getScene.getWindow.asInstanceOf[Stage]
    FxApp.setStates(states)
    stage.getScene.setRoot(root)
    stage.setMaximized(true)
  }

  def goBack(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("ProfileMenu.fxml"))
    val root: Parent = loader.load()

    userName.getScene.setRoot(root)
  }

  def enterPress(key: KeyEvent): Unit = if (key.getCode == KeyCode.ENTER) loadProfile()
}
