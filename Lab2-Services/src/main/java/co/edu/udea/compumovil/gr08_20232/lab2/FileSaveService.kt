package co.edu.udea.compumovil.gr08_20232.lab2

import android.app.IntentService
import android.content.Intent
import android.widget.Toast
import com.example.jetnews.R
import java.io.File
import java.io.FileOutputStream

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
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                JetnewsApplication.applicationContext(),
                getString(R.string.file_save_error),
                Toast.LENGTH_SHORT
            ).show()
        } finally {
            Toast.makeText(
                JetnewsApplication.applicationContext(),
                getString(R.string.file_saved_success),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}