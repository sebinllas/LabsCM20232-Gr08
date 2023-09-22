package co.edu.udea.compumovil.gr08_20232.lab1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import co.edu.udea.compumovil.gr08_20232.lab1.components.PersonCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoSummary(personViewModel: PersonViewModel) {

    Column {
        PersonCard(user = personViewModel.user.value!!)
    }
}