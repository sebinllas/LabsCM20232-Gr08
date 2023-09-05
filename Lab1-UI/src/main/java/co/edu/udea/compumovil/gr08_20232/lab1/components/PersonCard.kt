package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun PersonCard(
    name: String? = "",
    lastNames: String? = "",
    bornDate: String? = "",
    educationLevel: String = "",
    gender: String? = "",
    email: String? = "",
    phone: String? = "",
    address: String? = "",
    city: String? = "",
    country: String? = "",
) {
    Card {
        Column {
            Row {
                Icon(Icons.Rounded.AccountCircle, contentDescription = null)
                Text(text = "$name $lastNames", fontSize = 20.sp)
            }
            Text(text = "Fecha de nacimiento: $bornDate")
            Text(text = "Nivel de educación: $educationLevel")
            Text(text = "Género: $gender")
            Text(text = "Correo electrónico: $email")
            Text(text = "Teléfono: $phone")
            Text(text = "Dirección: $address")
            Text(text = "Ciudad: $city")
            Text(text = "País: $country")
        }
    }
}