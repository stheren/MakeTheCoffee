import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import org.json.JSONArray
import org.json.JSONObject
import java.awt.Robot
import java.awt.event.KeyEvent
import java.io.File

class CoffeeController {

    var StartPlay:Boolean = false
    val grinder: Robot = Robot()

    enum class type(Name:String){
        DELETE("DELETE"),
        RESTART("RESTART"),
        LEFT("LEFT"),
        RIGHT("RIGHT"),
        FORWARD("FORWARD"),
        BACKWARD("BACKWARD")
    }

    @FXML lateinit var btnPlay: Button
    @FXML lateinit var btnInit: Button
    @FXML lateinit var Output: TextArea

    fun initialize(){
        btnPlay.onAction = EventHandler {
            Thread.sleep(3000)

            JSONArray(File("C:\\CoffeeConfig\\Config.json").bufferedReader().use { it.readText() }).forEach {
                val Bean: JSONObject = it as JSONObject
                Output.text += Bean
                val Parfum = Bean.getString("Parfum")
                val Start = Bean.getLong("Start")
                val Stop = Bean.getLong("Stop")
                when (Parfum) {
                    type.DELETE.name -> {
                        setTimeout(PressDelete, Start)
                        setTimeout(ReleaseDelete, Stop)
                    }
                    type.RESTART.name -> {
                        setTimeout(PressRestart, Start)
                        setTimeout(ReleaseRestart, Stop)
                    }
                    type.LEFT.name -> {
                        setTimeout(PressLeft, Start)
                        setTimeout(ReleaseLeft, Stop)
                    }
                    type.RIGHT.name -> {
                        setTimeout(PressRight, Start)
                        setTimeout(ReleaseRight, Stop)
                    }
                    type.FORWARD.name -> {
                        setTimeout(PressFordward, Start)
                        setTimeout(ReleaseFordward, Stop)
                    }
                    type.BACKWARD.name -> {
                        setTimeout(PressBackward, Start)
                        setTimeout(ReleaseBackward, Stop)
                    }
                }
            }
        }
    }

    private val PressFordward = Runnable { grinder.keyPress(KeyEvent.VK_SHIFT) }
    private val ReleaseFordward = Runnable { grinder.keyRelease(KeyEvent.VK_SHIFT) }

    private val PressBackward = Runnable { grinder.keyPress(KeyEvent.VK_CONTROL) }
    private val ReleaseBackward = Runnable { grinder.keyRelease(KeyEvent.VK_CONTROL) }

    private val PressLeft = Runnable { grinder.keyPress(KeyEvent.VK_LEFT) }
    private val ReleaseLeft = Runnable { grinder.keyRelease(KeyEvent.VK_LEFT) }

    private val PressRight = Runnable { grinder.keyPress(KeyEvent.VK_RIGHT) }
    private val ReleaseRight = Runnable { grinder.keyRelease(KeyEvent.VK_RIGHT) }

    private val PressDelete = Runnable { grinder.keyPress(KeyEvent.VK_DELETE) }
    private val ReleaseDelete = Runnable { grinder.keyRelease(KeyEvent.VK_DELETE) }

    private val PressRestart = Runnable { grinder.keyPress(KeyEvent.VK_ENTER) }
    private val ReleaseRestart = Runnable { grinder.keyRelease(KeyEvent.VK_ENTER) }

    private fun setTimeout(runnable: Runnable, delay: Long) {
        Thread(Runnable {
            try {
                Thread.sleep(delay)
                runnable.run()
            } catch (e: Exception) {
                System.err.println(e)
            }
        }).start()
    }
}