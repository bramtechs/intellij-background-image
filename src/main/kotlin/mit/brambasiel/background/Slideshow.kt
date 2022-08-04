package mit.brambasiel.background

import com.intellij.openapi.vfs.VirtualFile
import mit.brambasiel.background.utils.ImageValidator
import mit.brambasiel.background.utils.Notifier
import java.io.File
import kotlin.random.Random

class Slideshow(val folder: File, val interval: Long) {
    val pool : ArrayDeque<File> = ArrayDeque()

    init {

        // index the folder of images
        for (file in folder.listFiles()!!){
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
        var current : Slideshow? = null
    }
}