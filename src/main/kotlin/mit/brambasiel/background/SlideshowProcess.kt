package mit.brambasiel.background

import mit.brambasiel.background.utils.Notifier

class SlideshowProcess(val show: Slideshow) : Thread() {

    private var shouldStop: Boolean = false
    private var nextChange: Long = System.currentTimeMillis()

    init {
        change()
    }

    fun change() {
        nextChange += show.interval
        val f = show.getRandomImage()
        Background.setImage(f)
    }

    override fun run() {
        while (!shouldStop) {
            sleep(1)
            if (System.currentTimeMillis() > nextChange) {
                change()
            }
        }
    }

    companion object {
        var current: SlideshowProcess? = null

        fun start() {
            stop()
            if (Slideshow.current == null) {
                Notifier.notify("No slideshow configured")
                return
            }else{
                current = SlideshowProcess(Slideshow.current!!)
                Notifier.notify("Background process started")
                current!!.start()
            }
        }
        fun startShow(show: Slideshow){
            Slideshow.current = show
            start()
        }

        fun stop() {
            if (current != null) {
                current!!.shouldStop = true
                current!!.join()
            }
        }
    }
}