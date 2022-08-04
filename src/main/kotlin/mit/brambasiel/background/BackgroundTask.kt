package mit.brambasiel.background

import mit.brambasiel.background.utils.Notifier

class BackgroundTask(val show: Slideshow) : Thread() {

    private var shouldStop: Boolean = false
    private var nextChange: Long = System.currentTimeMillis()

    init {
        change()
    }

    private fun change() {
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
        private var current: BackgroundTask? = null

        fun start() {
            stop()
            val show = Slideshow.getCurrentOrNull()
            if (show == null) {
                Notifier.notify("No slideshow configured")
                return
            }
            current = BackgroundTask(show)
        }

        fun stop() {
            if (current != null) {
                current!!.shouldStop = true
                current!!.join()
            }
        }
    }
}