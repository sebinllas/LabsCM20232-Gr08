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
import co.edu.udea.compumovil.gr08_20232.lab1.User

@Composable
fun PersonCard(user: User) {
    Card {
        Column {
            Row {
                Icon(Icons.Rounded.AccountCircle, contentDescription = null)
                Text(text = "${user.name} ${user.lastNames}", fontSize = 20.sp)
            }
            Text(text = "Fecha de nacimiento: ${user.bornDate}")
            Text(text = "Nivel de educación: ${user.educationLevel}")
            Text(text = "Género: ${user.gender}")
            Text(text = "Correo electrónico: ${user.email}")
            Text(text = "Teléfono: ${user.phone}")
            Text(text = "Dirección: ${user.address}")
            Text(text = "Ciudad: ${user.city}")
            Text(text = "País: ${user.country}")
        }
    }
}