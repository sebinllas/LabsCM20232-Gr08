package co.edu.udea.compumovil.gr08_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.gr08_20232.lab1.ui.theme.LabsCM20232Gr08Theme

class MainActivity : ComponentActivity() {
    private val personViewModel = PersonViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20232Gr08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalInfoView(personViewModel = personViewModel)
                }
            }
        }
    }


}

