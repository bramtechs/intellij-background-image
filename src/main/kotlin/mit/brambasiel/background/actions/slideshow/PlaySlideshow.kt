package mit.brambasiel.background.actions.slideshow

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import mit.brambasiel.background.SlideshowProcess

class PlaySlideshow : AnAction(){
    override fun actionPerformed(e: AnActionEvent) {
        SlideshowProcess.start()
    }
}