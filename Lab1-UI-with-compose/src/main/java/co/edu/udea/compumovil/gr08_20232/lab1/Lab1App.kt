package co.edu.udea.compumovil.gr08_20232.lab1

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class Screens {
    PersonalInfo,
    ContactInfo
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
        title = { Text(text = currentScreen) },
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Lab1App(
    personViewModel: PersonViewModel,
    navController: NavHostController = rememberNavController(),
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        content = {_ ->
            NavHost(navController = navController, startDestination = Screens.PersonalInfo.name) {

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
                onClick = { navController.navigate(Screens.ContactInfo.name) },
                content = { Icon(Icons.Rounded.ArrowForward, contentDescription = null) }
            )
        },
        topBar = {
            Lab1AppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = backStackEntry?.destination?.route ?: Screens.PersonalInfo.name,

        )
        },
        floatingActionButtonPosition = FabPosition.End
    )


}