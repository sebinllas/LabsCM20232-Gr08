package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr08_20232.lab1.Gender

@Composable
fun RadioSelectInput(
    values: List<String>,
    selectedValue: Gender?,
    onChange: (Gender) -> Unit,
    label: @Composable (() -> Unit),
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                OutlinedTextFieldDefaults.UnfocusedBorderThickness,
                Color.Gray,
                OutlinedTextFieldDefaults.shape
            )
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            if (trailingIcon != null) {
                trailingIcon()
            }
            Spacer(modifier = Modifier.width(20.dp))
            label()
        }
        Row {
            values.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.Center,
                ) {
                    RadioButton(
                        selected = Gender.valueOf(it) == selectedValue,
                        onClick = {
                            onChange(Gender.valueOf(it))
                        }
                    )
                    Text(it)
                }
            }
        }
    }

}