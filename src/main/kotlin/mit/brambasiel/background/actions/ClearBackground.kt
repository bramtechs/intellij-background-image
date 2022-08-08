package mit.brambasiel.background.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import mit.brambasiel.background.Background
import mit.brambasiel.background.SlideshowProcess
import mit.brambasiel.background.utils.Notifier

class ClearBackground : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        Background.clear()
        Notifier.notify("Cleared background")
        SlideshowProcess.stop()
    }
}