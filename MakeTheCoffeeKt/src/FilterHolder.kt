import java.awt.BorderLayout
import java.awt.Component
import java.awt.GridLayout
import javax.swing.JPanel

class Filter(var Orientation: String, var Component: Component)

class FilterHolder():JPanel(){
    constructor(rows:Int, cols:Int , vararg Childs:Component) : this(){
        layout = GridLayout(rows, cols)
        for (Child in Childs){
            add(Child)
        }
    }

    constructor(vararg Childs:Filter): this(){
        layout = BorderLayout()
        for (Child in Childs){
            add(Child.Component, Child.Orientation)
        }
    }
}