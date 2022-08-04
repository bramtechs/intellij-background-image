package mit.brambasiel.background.actions.slideshow

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import mit.brambasiel.background.dialogs.SlideshowDialog

class ConfigureSlideshow : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        SlideshowDialog.open()
    }
}