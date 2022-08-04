package mit.brambasiel.background.utils

import java.io.File

object OSUtils {
    enum class OS {
        WINDOWS, LINUX, MAC, UNKNOWN
    }

    private fun getOperatingSystem(): OS {
        val os = System.getProperty("os.name").toLowerCase()
        return when {
            os.contains("win") -> {
                OS.WINDOWS
            }
            os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
                OS.LINUX
            }
            os.contains("mac") -> {
                OS.MAC
            }
            else -> OS.UNKNOWN
        }
    }

    private fun searchPath(path: String): File? {
        val cacheFolder = File(path)
        if (cacheFolder.exists()){
            for (file in cacheFolder.listFiles()!!){
                return file
            }
        }else{
            Notifier.notify("Folder $path not found")
        }
        return null
    }

    fun findOSBackground(): File?{
        return when (getOperatingSystem()){
            OS.WINDOWS -> {
                // https://answers.microsoft.com/en-us/windows/forum/all/where-is-the-current-custom-wallpaper-stored-in/f08a364c-43d3-4303-9252-81d88b2b86a7
                searchPath("${System.getProperty("user.home")}\\AppData\\Roaming\\Microsoft\\Windows\\Themes\\CachedFiles")
            }
            else -> {
                Notifier.notify("Only Windows is supported for the moment.")
                null
            }
        }
    }
}