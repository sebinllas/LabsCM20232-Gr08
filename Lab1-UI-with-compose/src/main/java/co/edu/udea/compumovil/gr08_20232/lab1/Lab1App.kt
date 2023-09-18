package co.edu.udea.compumovil.gr08_20232.lab1

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class Screens(@StringRes val resourceId: Int) {
    PersonalInfo(R.string.personal_info),
    ContactInfo(R.string.contact_info)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lab1AppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentScreen: String
) {
    TopAppBar(
        title = { Text(currentScreen) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Lab1App(
    modifier: Modifier = Modifier,
    personViewModel: PersonViewModel,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenName = backStackEntry?.destination?.route ?: Screens.PersonalInfo.name

    Scaffold(
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screens.PersonalInfo.name,
                modifier = modifier.padding(innerPadding)
            ) {

                composable(route = Screens.PersonalInfo.name) {
                    PersonalInfoForm(personViewModel = personViewModel)
                }
                composable(route = Screens.ContactInfo.name) {
                    ContactInfoForm(personViewModel = personViewModel)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (personViewModel.validPersonalInfo() and
                        navController.currentDestination?.route.equals(
                            Screens.PersonalInfo.name
                        )
                    ) {
                        navController.navigate(Screens.ContactInfo.name)
                    }
                    if (personViewModel.validContactInfo()) {
                        Log.i("user-info", personViewModel.user.value.toString())
                    }
                },
                content = { Icon(Icons.Rounded.ArrowForward, contentDescription = null) },
            )
        },
        topBar = {
            Lab1AppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = stringResource(id = Screens.valueOf(currentScreenName).resourceId)
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )

}
