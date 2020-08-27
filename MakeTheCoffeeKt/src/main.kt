import com.sun.jdi.ThreadReference
import javafx.application.Application.launch
import java.time.Clock

fun main() {
    //launch(CoffeeMaker::class.java)
    testTime()
}

fun testTime(){
    while (true) {
        val time = System.currentTimeMillis()
        Thread.sleep(1000)
        println(System.currentTimeMillis() - time)
    }
}