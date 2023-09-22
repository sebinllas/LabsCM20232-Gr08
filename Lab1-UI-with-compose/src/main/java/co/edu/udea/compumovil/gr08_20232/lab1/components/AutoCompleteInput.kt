package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties

@Composable
fun AutoCompleteInput(
    value: String,
    onValueChange: (String) -> Unit,
    suggestions: List<String>,
    modifier: Modifier = Modifier,
    onSuggestionSelected: (String) -> Unit = onValueChange,
    label: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var currentSuggestions: List<String> by remember { mutableStateOf(emptyList()) }
    var showSuggestions: Boolean by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column {
        OutlinedTextField(
            value = value,
            label = label,
            isError = isError,
            leadingIcon = leadingIcon,
            modifier = modifier.fillMaxWidth(),
            keyboardOptions = keyboardOptions,
            onValueChange = {
                onValueChange(it)
                val newSuggestions = suggestions.filter { suggestion ->
                    suggestion.contains(it, ignoreCase = true)
                }
                if (newSuggestions.isNotEmpty()) {
                    currentSuggestions = newSuggestions
                    showSuggestions = true
                } else {
                    showSuggestions = false
                }
            },
        )
        DropdownMenu(
            expanded = showSuggestions,
            modifier = modifier
                .heightIn(40.dp, 300.dp)
                .fillMaxWidth(),
            onDismissRequest = {
                showSuggestions = false
            },
            properties = PopupProperties(focusable = false)
        ) {
            currentSuggestions.forEach { element ->
                DropdownMenuItem(
                    text = { Text(text = element) },
                    onClick = {
                        onSuggestionSelected(element)
                        focusManager.clearFocus()
                        showSuggestions = false
                    }
                )
            }
        }
    }
}