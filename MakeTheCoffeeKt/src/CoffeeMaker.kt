import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.WindowConstants

class CoffeeMaker() : JFrame(){
    init{
        title = "Make The Coffee"
        size = Dimension(500, 1000)
        setLocationRelativeTo(null)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        var UpMenu = Filter(
            BorderLayout.NORTH,
            FilterHolder(2,1,
                FilterHolder(1,2,

                    )
            )
        )

        layout = FilterHolder()
    }
}