package mit.brambasiel.background.dialogs

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBList
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class SlideshowDialog : DialogWrapper(null) {
    override fun createCenterPanel(): JComponent {
        val panel = JPanel()

        val amount = JTextField()
        panel.add(amount)

        val unitList = JBList<String>()
        val units = arrayOf("Seconds","Minutes","Hours")
        unitList.setListData(units)
        panel.add(unitList)

        return panel
    }

    companion object {
       fun open(){
           SlideshowDialog().showAndGet()
       }
    }
}
