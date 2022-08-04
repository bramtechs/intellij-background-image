package mit.brambasiel.background.dialogs

import javax.swing.text.NumberFormatter
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import mit.brambasiel.background.Slideshow
import mit.brambasiel.background.SlideshowProcess
import mit.brambasiel.background.utils.Notifier
import java.awt.FlowLayout
import java.io.File
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JFormattedTextField
import javax.swing.JPanel

class SlideshowDialog : DialogWrapper(true) {
    init {
        title = "Configure Slideshow"
        init()
    }

    private var file: File? = null
    private lateinit var interval: JFormattedTextField
    private lateinit var unitBox: ComboBox<String>

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(FlowLayout())
        panel.setSize(300, 200)

        // path box
        val chooseFolder = JButton("Select folder")
        file = Slideshow.current?.folder
        chooseFolder.addActionListener {
            file = File(DialogUtils.pickFolder().canonicalPath!!)
        }
        panel.add(chooseFolder)

        // duration box
        val format = NumberFormatter()
        format.minimum = 1
        format.maximum = 10000
        format.allowsInvalid = false
        format.commitsOnValidEdit = false

        interval = JFormattedTextField(format)
        panel.add(interval)

        // unit box
        unitBox = ComboBox<String>()
        val units = arrayOf("Seconds", "Minutes", "Hours")
        units.forEach { unitBox.addItem(it) }
        panel.add(unitBox)

        return panel
    }

    val result: Slideshow?
        get() {
            // create a new slideshow object
            if (file != null) {
                val interval = interval.text.toLong() * when (unitBox.selectedItem) {
                    "Seconds" -> 1 * 1000
                    "Minutes" -> 60 * 1000
                    "Hours" -> 60 * 60 * 1000
                    else -> throw IllegalArgumentException("Unknown unit")
                }
                return Slideshow(file!!, interval)
            }
            Notifier.notify("No folder was chosen")
            return null
        }

    companion object {
        fun open() {
            val dialog = SlideshowDialog()
            if (dialog.showAndGet()) {
                if (dialog.result != null) {
                    Slideshow.current = dialog.result!!
                }
            }
        }
    }
}
