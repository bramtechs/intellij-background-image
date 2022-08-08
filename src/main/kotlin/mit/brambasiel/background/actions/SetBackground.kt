package mit.brambasiel.background.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import mit.brambasiel.background.Background
import mit.brambasiel.background.SlideshowProcess
import mit.brambasiel.background.utils.Notifier
import mit.brambasiel.background.dialogs.DialogUtils
import java.io.File

class SetBackground : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val file = DialogUtils.pickSingleImage()
        val realFile = File(file.canonicalPath!!)
        Notifier.notify(
            "Set background image to ${
                file.canonicalPath
            }"
        )
        Background.setImage(realFile)
        SlideshowProcess.stop()
    }
}