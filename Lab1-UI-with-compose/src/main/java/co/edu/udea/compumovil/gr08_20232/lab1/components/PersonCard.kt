package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20232.lab1.R
import co.edu.udea.compumovil.gr08_20232.lab1.User

@Composable
fun PersonCard(user: User) {
    Card {
        Column {
            Row {
                Icon(Icons.Rounded.AccountCircle, contentDescription = null)
                Text(text = "${user.name} ${user.lastNames}", fontSize = 20.sp)
            }
            Text(text = "${stringResource(R.string.birthdate_label)}: ${user.bornDate}")
            Text(
                text = "${stringResource(R.string.education_level)}: ${
                    user.educationLevel?.let {
                        stringResource(
                            it.resourceId
                        )
                    }
                }"
            )
            Text(text = "${stringResource(R.string.gender)}: ${user.gender?.let { stringResource(it.resourceId) }}")
            Text(text = "${stringResource(R.string.email_label)}: ${user.email}")
            Text(text = "${stringResource(R.string.phone_label)}: ${user.phone}")
            Text(text = "${stringResource(R.string.address_label)}: ${user.address}")
            Text(text = "${stringResource(R.string.city_label)}: ${user.city}")
            Text(text = "${stringResource(R.string.country_label)}: ${user.country}")
        }
    }
}