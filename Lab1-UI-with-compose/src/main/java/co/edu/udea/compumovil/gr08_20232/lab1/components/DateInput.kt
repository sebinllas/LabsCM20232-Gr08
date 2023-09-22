package co.edu.udea.compumovil.gr08_20232.lab1.components

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import co.edu.udea.compumovil.gr08_20232.lab1.R
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(34)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    value: LocalDate?,
    onValueChange: (LocalDate) -> Unit,
    label: @Composable (() -> Unit),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
) {
    val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
    val focusManager = LocalFocusManager.current
    val openDialog = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.isFocused) {
                    openDialog.value = true
                }
            },
        value = if (value != null) value.format(formatter) else "",
        onValueChange = { },
        readOnly = true,
        leadingIcon = leadingIcon,
        label = label,
        trailingIcon = trailingIcon,
        isError = isError,
    )
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = {
                focusManager.clearFocus()
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    enabled = datePickerState.selectedDateMillis != null,
                    onClick = {
                        val instant = datePickerState.selectedDateMillis?.let {
                            Instant.ofEpochMilli(
                                it
                            )
                        }
                        val localDate = instant?.atZone(ZoneId.of("UTC"))?.toLocalDate()
                        onValueChange(localDate!!)
                        focusManager.clearFocus()
                        openDialog.value = false
                    },
                ) {
                    Text(stringResource(id = R.string.confirm_text))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        focusManager.clearFocus()
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.dismiss_text))
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                dateFormatter = DatePickerFormatter("MMMM dd, yyyy"),
            )
        }
    }
}