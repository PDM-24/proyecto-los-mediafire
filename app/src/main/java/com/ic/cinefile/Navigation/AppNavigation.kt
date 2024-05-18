import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ic.cinefile.Navigation.AppScreens
import com.ic.cinefile.viewModel.userCreateViewModel

@Composable
fun AppNavigation(navController: NavController) {

    NavHost(navController as NavHostController, startDestination = AppScreens.HomeApp.route) {
        composable(AppScreens.HomeApp.route) {
            AppScreens.HomeApp.content(navController)
        }
        composable(AppScreens.LoginCuenta.route) {
            AppScreens.LoginCuenta.content(navController)
        }
        composable(AppScreens.CrearCuenta.route) {
            AppScreens.CrearCuenta.content(navController)
        }
        composable(AppScreens.CrearPerfil.route) {
            AppScreens.CrearPerfil.content(navController)
        }
        composable(AppScreens.ElegirGeneros.route) {
            AppScreens.ElegirGeneros.content(navController)
        }
        composable(AppScreens.Genero.route) {
            AppScreens.Genero.content(navController)
        }
        composable(AppScreens.Avatar.route) {
            AppScreens.Avatar.content(navController)
       }
        composable(AppScreens.RestablecerContra.route) {
            AppScreens.RestablecerContra.content(navController)
        }
    }
}
