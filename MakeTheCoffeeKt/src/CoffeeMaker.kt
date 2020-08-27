import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class CoffeeMaker() : Application(){

    companion object{
        lateinit var pStage: Stage
    }

    override fun start(primaryStage: Stage) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("MiniFrame.fxml"))
        val root = fxmlLoader.load<Any>() as AnchorPane
        val scene = Scene(root, 500.0, 500.0)

        primaryStage.minWidth = 320.0

        primaryStage.isAlwaysOnTop = true
        primaryStage.scene = scene
        primaryStage.show()

        pStage = primaryStage
    }
}