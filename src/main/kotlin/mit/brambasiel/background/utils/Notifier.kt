package mit.brambasiel.background.utils

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications


object Notifier {
    fun notify(message: String){
       val n = Notification("Background Image","Background image",message,NotificationType.INFORMATION)
        Notifications.Bus.notify(n)
    }
    fun error(message: String){
        val n = Notification("Background Image","Background image error",message,NotificationType.ERROR)
        Notifications.Bus.notify(n)
    }
}