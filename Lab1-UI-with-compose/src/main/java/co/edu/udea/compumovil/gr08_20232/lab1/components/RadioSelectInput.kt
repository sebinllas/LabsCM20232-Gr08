package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <T> RadioSelectInput(
    values: List<T>,
    selectedValue: T?,
    onChange: (T) -> Unit,
    label: @Composable (() -> Unit),
    trailingIcon: @Composable (() -> Unit)? = null,
    itemFactory: @Composable (T, Int) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
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
            Spacer(modifier = Modifier.width(10.dp))
            if (trailingIcon != null) {
                trailingIcon()
            }
            Spacer(modifier = Modifier.width(20.dp))
            label()
        }
        FlowRow {
            values.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.Center,
                ) {
                    RadioButton(
                        selected = it == selectedValue,
                        onClick = {
                            onChange(it)
                        },


                    )
                    itemFactory(it, values.indexOf(it))
                }
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
    }

}