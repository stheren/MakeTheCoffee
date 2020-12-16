import com.sun.jdi.ThreadReference
import javafx.application.Application.launch
import java.awt.Robot
import java.awt.event.KeyEvent
import java.time.Clock

val grinder: Robot = Robot()

fun main() {
    //launch(CoffeeMaker::class.java)
    testTime()
}

fun testTime(){
    var moyen:Float = 0f
    var numberOfBoucle:Int = 0
    while (true) {

        val time = System.currentTimeMillis()
        grinder.keyPress(KeyEvent.VK_RIGHT)
        moyen += System.currentTimeMillis() - time


        grinder.keyRelease(KeyEvent.VK_RIGHT)
        numberOfBoucle++
        println(moyen/numberOfBoucle)
    }
}