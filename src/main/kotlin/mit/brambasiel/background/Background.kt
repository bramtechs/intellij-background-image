package mit.brambasiel.background

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.impl.IdeBackgroundUtil
import mit.brambasiel.background.utils.ImageValidator
import mit.brambasiel.background.utils.Notifier
import mit.brambasiel.background.utils.OSUtils
import java.io.File
import java.nio.file.Path

object Background {
    /**
     * Clears any IDE background image.
     */
    fun clear(){
        val props = PropertiesComponent.getInstance()
        props.setValue(IdeBackgroundUtil.EDITOR_PROP,null)
        props.setValue(IdeBackgroundUtil.FRAME_PROP,null)
    }

    fun setImage(file: File){
        val props = PropertiesComponent.getInstance()
        props.setValue(IdeBackgroundUtil.EDITOR_PROP,file.canonicalPath)
        props.setValue(IdeBackgroundUtil.FRAME_PROP,file.canonicalPath)
    }


}