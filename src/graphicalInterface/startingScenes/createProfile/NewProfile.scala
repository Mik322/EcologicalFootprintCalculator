package graphicalInterface.startingScenes.createProfile

import java.io.{File, FileInputStream, ObjectInputStream}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Label, ScrollPane, TextField}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.stage.Stage

class NewProfile {
  @FXML
  var username: TextField = _
  @FXML
  var usedProfile: Label = _

  def keyPressed(e: KeyEvent): Unit = if (e.getCode == KeyCode.ENTER) checkUsers()

  def checkUsers() = {
    if (!username.getText.isBlank) {
      try {
        val in = new ObjectInputStream(new FileInputStream(new File(s"${username.getText}.prof")))
        usedProfile.setText("There's already a profile with that username!")
      } catch {
        case _: Throwable => createProfile()
      }
    }else{
      usedProfile.setText("Please choose a username!")
    }
  }

  def createProfile(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("Questionnaire.fxml"))
    val questioner: ScrollPane = loader.load()
    loader.getController[Questionnaire].setUsername(username.getText)
    val stage = username.getScene.getWindow.asInstanceOf[Stage]
    stage.setScene(new Scene(questioner))
    stage.setMaximized(true)
  }

  def goBack(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("../ProfileMenu.fxml"))
    val root: Parent = loader.load()
    username.getScene.setRoot(root)
  }
}
