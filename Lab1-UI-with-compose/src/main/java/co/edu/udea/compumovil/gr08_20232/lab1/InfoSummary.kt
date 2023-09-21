package co.edu.udea.compumovil.gr08_20232.lab1

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import co.edu.udea.compumovil.gr08_20232.lab1.components.PersonCard

@Composable
fun InfoSummary(personViewModel: PersonViewModel) {

    Column {
        Text(text = "${personViewModel.user.value?.name} this is your information:")
        PersonCard(user = personViewModel.user.value!!)
    }
}