package graphicalInterface.startingScenes.newprofile

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Scene
import javafx.scene.control.{ScrollPane, TextField}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.stage.Stage

class NewProfile {
  @FXML
  var username: TextField = _

  def keyPressed(e: KeyEvent): Unit = if (e.getCode == KeyCode.ENTER) createProfile()

  def createProfile(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("Questionary.fxml"))
    val questioner: ScrollPane = loader.load()
    loader.getController[Questionary].setUsername(username.getText)
    val stage = username.getScene.getWindow.asInstanceOf[Stage]
    stage.setScene(new Scene(questioner))
    stage.setWidth(900)
    stage.setHeight(650)
  }
}
