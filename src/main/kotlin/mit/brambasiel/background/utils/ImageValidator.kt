package mit.brambasiel.background.utils

import com.intellij.openapi.vfs.VirtualFile
import java.io.File

object ImageValidator {

    fun isValid(file: VirtualFile): Pair<Boolean, String?> {
        if (file.canonicalPath == null){
            return Pair(false,"Can't find file on disk")
        }
        val f = File(file.canonicalPath!!)
        return isValid(f)
    }

    fun isValid(file: File): Pair<Boolean, String?> {
        val formats = arrayOf("png", "jpg", "webp", "bmp")
        if (!file.exists()) {
            return Pair(false, "Image doesn't exist")
        }
        if (file.isDirectory) {
            return Pair(false, "Not a file")
        }
        if (file.nameWithoutExtension.contains(",")) {
            return Pair(false, "Image path can't contain commas (,)")
        }

        var valid = false
        for (format in formats) {
            if (file.extension == format) {
                valid = true
                break
            }
        }
        if (!valid){
           return Pair(false,"Unknown image format ${file.extension}")
        }

        return Pair(true, null)
    }

}