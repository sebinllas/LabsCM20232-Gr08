package co.edu.udea.compumovil.gr08_20232.lab1.components

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateInput(
    value: LocalDate?,
    onValueChange: (LocalDate) -> Unit,
    label: @Composable (() -> Unit),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
) {
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, y, m, d ->
            onValueChange(LocalDate.of(y, m, d))
        },
        value?.year ?: LocalDate.now().year,
        value?.monthValue ?: (LocalDate.now().monthValue - 1),
        value?.dayOfMonth ?: LocalDate.now().dayOfMonth,
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { if (it.isFocused) dialog.show() }
                .clickable { dialog.show() },
            value = if (value != null) "${value.dayOfMonth}/${value.monthValue + 1}/${value.year}" else "",
            onValueChange = { },
            readOnly = true,
            leadingIcon = leadingIcon,
            label = label,
            trailingIcon = trailingIcon,
            isError = isError,
        )
    }

}