package mit.brambasiel.background.dialogs

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.fileChooser.FileChooserFactory
import com.intellij.openapi.vfs.VirtualFile
import mit.brambasiel.background.utils.ImageValidator

object DialogUtils {
    fun pickSingleImage(): VirtualFile {
        val descr = FileChooserDescriptorFactory.createSingleFileDescriptor()
        descr.withFileFilter { t: VirtualFile -> ImageValidator.isValid(t).first }
        val picker = FileChooserFactory.getInstance().createFileChooser(descr, null, null)
        return picker.choose(null).first()
    }
}