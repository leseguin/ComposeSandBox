package fr.leane.seguin.composesandbox.helpers

import android.util.Log
import java.io.File

object FileHelper {
    fun readFile(fileName: String): String {
        var fileText = "*** $fileName ***\n"
        try {
            File(fileName).forEachLine { line ->
                println(line)
                fileText += "$line\n"
            }
        } catch (e: Exception) {
            Log.e("readFile", e.message ?: "")
        }

        return fileText
    }
}

