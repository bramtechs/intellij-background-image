package mit.brambasiel.background

import com.intellij.openapi.vfs.VirtualFile
import mit.brambasiel.background.utils.ImageValidator
import mit.brambasiel.background.utils.Notifier
import java.io.File
import kotlin.random.Random

class Slideshow(val folder: VirtualFile, val interval: Long) {
    val pool : ArrayDeque<File> = ArrayDeque()

    init {

        // index the folder of images
        val dir = File(folder.canonicalPath!!)
        for (file in dir.listFiles()!!){
           if (ImageValidator.isValid(file!!).first) {
               pool.add(file)
           }
        }
        Notifier.notify("Found ${pool.size} images!")
        pool.random(Random(System.currentTimeMillis()))
    }

    fun getRandomImage(): File{
        val f = pool.removeFirst()
        pool.addLast(f)
        return f
    }

    companion object {
        private var current : Slideshow? = null

        fun getCurrentOrNull(): Slideshow? {
           return current
        }
    }
}