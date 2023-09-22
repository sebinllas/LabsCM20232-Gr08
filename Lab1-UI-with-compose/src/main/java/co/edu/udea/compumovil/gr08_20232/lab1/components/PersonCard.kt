package co.edu.udea.compumovil.gr08_20232.lab1.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr08_20232.lab1.EducationLevel
import co.edu.udea.compumovil.gr08_20232.lab1.Gender
import co.edu.udea.compumovil.gr08_20232.lab1.R
import co.edu.udea.compumovil.gr08_20232.lab1.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonCard(user: User) {
    val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row {
                Icon(
                    Icons.Rounded.AccountCircle,
                    contentDescription = null,
                    Modifier
                        .width(80.dp)
                        .height(80.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${user.name} ${user.lastNames}",
                    fontSize = 20.sp,
                    modifier = Modifier.align(CenterVertically)
                )
            }
            Divider(Modifier.padding(horizontal = 16.dp, vertical = 8.dp), color = MaterialTheme.colorScheme.primary)
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "${stringResource(R.string.birthdate_label)}: ${
                        user.bornDate?.format(
                            formatter
                        )
                    }"
                )
                if (user.educationLevel != null) Text(
                    text = "${stringResource(R.string.education_level)}: ${
                        stringResource(
                            user.educationLevel.resourceId
                        )
                    }"
                )
                if (user.gender != null) Text(
                    text = "${stringResource(R.string.gender)}: ${
                        stringResource(
                            user.gender.resourceId
                        )
                    }"
                )
                Text(text = "${stringResource(R.string.email_label)}: ${user.email}")
                Text(text = "${stringResource(R.string.phone_label)}: ${user.phone}")
                Text(text = "${stringResource(R.string.country_label)}: ${user.country}")
                if (user.city.isNotBlank()) Text(text = "${stringResource(R.string.city_label)}: ${user.city}")
                if (user.address.isNotBlank()) Text(text = "${stringResource(R.string.address_label)}: ${user.address}")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
val user = User(
    name = "John Doe",
    lastNames = "Smith",
    bornDate = LocalDate.of(1990, 1, 1),
    gender = Gender.MALE,
    educationLevel = EducationLevel.PROFESSIONAL,
    email = "john.doe@example.com",
    phone = "+1-555-555-5555",
    address = "123 Main Street",
    city = "Anytown",
    country = "USA"
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4_XL,
)
fun PersonCardPreview() {
    Column {

        PersonCard(user = user)
    }
}
