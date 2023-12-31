package co.edu.udea.compumovil.gr08_20232.lab1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import co.edu.udea.compumovil.gr08_20232.lab1.components.AutoCompleteInput


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContactInfoForm(personViewModel: PersonViewModel) {

    val user by personViewModel.user.observeAsState(initial = User())
    val showErrors by personViewModel.contactInfoNextClicked.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = user.phone,
            onValueChange = { personViewModel.setUser(user.copy(phone = it)) },
            label = { Text(stringResource(id = R.string.phone_label)) },
            leadingIcon = { Icon(Icons.Rounded.Phone, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            isError = showErrors && !user.validPhone(),

            )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = user.email,
            onValueChange = { personViewModel.setUser(user.copy(email = it)) },
            label = { Text(stringResource(id = R.string.email_label)) },
            leadingIcon = { Icon(Icons.Rounded.Mail, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = showErrors && !user.validEmail(),
        )
        AutoCompleteInput(
            value = user.country,
            label = { Text(stringResource(id = R.string.country_label)) },
            onValueChange = {
                personViewModel.setUser(
                    user.copy(
                        country = it
                    )
                )
            },
            suggestions = stringArrayResource(id = R.array.countries).toList(),
            leadingIcon = {
                Icon(
                    Icons.Rounded.Language,
                    contentDescription = null,
                )
            },
            isError = showErrors && !user.validCountry(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
        )
        AutoCompleteInput(
            modifier = Modifier.fillMaxWidth(),
            value = user.city,
            onValueChange = { personViewModel.setUser(user.copy(city = it)) },
            label = { Text(stringResource(id = R.string.city_label)) },
            leadingIcon = { Icon(Icons.Rounded.LocationOn, contentDescription = null) },
            suggestions = if (personViewModel.getCitySuggestions() !== null) {
                stringArrayResource(
                    id = personViewModel.getCitySuggestions()!!
                ).toList()
            } else emptyList(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = user.address,
            onValueChange = { personViewModel.setUser(user.copy(address = it)) },
            label = { Text(stringResource(id = R.string.address_label)) },
            leadingIcon = { Icon(Icons.Rounded.Map, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
    }
}