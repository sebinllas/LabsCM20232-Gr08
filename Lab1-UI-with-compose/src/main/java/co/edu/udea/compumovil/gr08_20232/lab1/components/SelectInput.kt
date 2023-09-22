package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SelectInput(
    modifier: Modifier = Modifier,
    dropDownModifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    itemFactory: @Composable (T, Int) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    textValueFactory: @Composable (T) -> String = { it.toString() },
    isError: Boolean = false,
) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier.wrapContentSize(Alignment.TopStart)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .onFocusChanged { if (it.isFocused) expanded = !expanded },
            value = if (selectedItem == null) "" else textValueFactory(selectedItem),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                )
            },
            leadingIcon = leadingIcon,
            label = label,
            isError = isError,
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            },
            modifier = dropDownModifier
                .heightIn(40.dp, 300.dp)
                .fillMaxWidth()
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(
                    text = { itemFactory(element, index) },
                    onClick = {
                        onItemSelected(items[index])
                        focusManager.clearFocus()
                        expanded = false
                    }
                )
            }
        }
    }
}