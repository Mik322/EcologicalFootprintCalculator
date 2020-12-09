package graphicalInterface.healthTracker

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.Pane

class AddChange {
  @FXML
  var addChangeDisplay: Pane = _

  def addFoodDisplay() ={

  }

  def addDrinkDisplay() ={

  }

  def addExerciseDisplay() ={

  }

  def addSleepDisplay() ={

  }

  def setGoalDisplay() ={

  }

  def addCupOfWaterDisplay() ={

  }

  def changeBodyParametersMenu() ={
    val loader = new FXMLLoader(getClass.getResource("ChangeBodyParameters.fxml"))
    addChangeDisplay.getChildren.clear()
    addChangeDisplay.getChildren.add(loader.load())
  }
}
