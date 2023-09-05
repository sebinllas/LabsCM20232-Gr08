package co.edu.udea.compumovil.gr08_20232.lab1

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Female
import androidx.compose.material.icons.rounded.Male
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material.icons.rounded.School
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.gr08_20232.lab1.components.DateInput
import co.edu.udea.compumovil.gr08_20232.lab1.components.PersonCard
import co.edu.udea.compumovil.gr08_20232.lab1.components.RadioSelectInput
import co.edu.udea.compumovil.gr08_20232.lab1.components.SelectInput


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonalInfoView(personViewModel: PersonViewModel) {
    Scaffold(
        content = {
            Box(modifier = Modifier.padding(it)) {
                PersonalInfoForm(personViewModel = personViewModel)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { Log.i("PersonalInfoView", personViewModel.toString()) },
                content = { Icon(Icons.Rounded.ArrowForward, contentDescription = null) }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonalInfoForm(personViewModel: PersonViewModel) {

    val user by personViewModel.user.observeAsState(initial = User())

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = user.name,
            onValueChange = { personViewModel.setUser(user.copy(name = it)) },
            label = { Text("Nombre") },
            leadingIcon = { Icon(Icons.Rounded.Person, contentDescription = null) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = user.lastNames,
            onValueChange = { personViewModel.setUser(user.copy(lastNames = it)) },
            label = { Text("Apellidos") },
            leadingIcon = { Icon(Icons.Rounded.PersonAdd, contentDescription = null) }
        )
        DateInput(
            value = user.bornDate,
            onValueChange = { personViewModel.setUser(user.copy(bornDate = it)) },
            label = { Text("Fecha de nacimiento") },
            leadingIcon = { Icon(Icons.Rounded.DateRange, contentDescription = null) },
        )
        SelectInput<EducationLevel>(
            items = EducationLevel.values().map { it },
            selectedItem = user.educationLevel,
            label = { Text("Nivel de estudios") },
            onItemSelected = {
                personViewModel.setUser(
                    user.copy(
                        educationLevel = it
                    )
                )
            },
            dropdownItemFactory = { it, _ -> Text(it.name) },
            leadingIcon = {
                Icon(
                    Icons.Rounded.School,
                    contentDescription = null,
                )
            },
        )
        RadioSelectInput(
            values = Gender.values().map { it.name },
            selectedValue = user.gender,
            onChange = {
                personViewModel.setUser(
                    user.copy(gender = it)
                )
            },
            label = { Text("Sexo") },
            trailingIcon = {
                Icon(
                    when (user.gender) {
                        Gender.MALE -> Icons.Rounded.Male
                        Gender.FEMALE -> Icons.Rounded.Female
                        else -> Icons.Rounded.Android
                    },
                    contentDescription = null,
                )
            }
        )
        PersonCard(user)
    }
}
