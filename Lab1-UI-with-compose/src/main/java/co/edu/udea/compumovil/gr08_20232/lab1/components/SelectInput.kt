package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SelectInput(
    modifier: Modifier = Modifier,
    dropDownModifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    dropdownItemFactory: @Composable (T, Int) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
) {
    var expanded: Boolean by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.wrapContentSize(Alignment.TopStart)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .onFocusChanged { if (it.isFocused) expanded = !expanded },
            value = if(selectedItem == null) "" else selectedItem.toString(),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                )
            },
            leadingIcon = leadingIcon,
            label = label,
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = dropDownModifier
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(
                    text = { dropdownItemFactory(element, index) },
                    onClick = {
                        onItemSelected(items[index])
                        expanded = false
                    }
                )
            }
        }
    }
}