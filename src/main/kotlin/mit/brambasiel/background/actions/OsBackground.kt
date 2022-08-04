package mit.brambasiel.background.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import mit.brambasiel.background.Background
import mit.brambasiel.background.utils.Notifier
import mit.brambasiel.background.utils.OSUtils

class OsBackground : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val file = OSUtils.findOSBackground()
        if (file != null) {
            Background.setImage(file)
        }else{
            Notifier.error("Could not find desktop wallpaper")
        }
    }
}