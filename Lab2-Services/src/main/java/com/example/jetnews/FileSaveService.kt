package com.example.jetnews

import android.app.IntentService
import android.content.Intent
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileSaveService : IntentService("FileSaveService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val fileName = intent.getStringExtra("file_name")
            val fileContent = intent.getStringExtra("file_content")

            if (fileName != null && fileContent != null) {
                saveFile(fileName, fileContent)
            }
        }
    }

    private fun saveFile(fileName: String, fileContent: String) {
        try {
            val file = File(getExternalFilesDir(null), fileName)
            val fos = FileOutputStream(file)
            fos.write(fileContent.toByteArray())
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}