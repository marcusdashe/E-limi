package elimi.africa.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import elimi.africa.screen.AnimatedSplashScreen
import elimi.africa.screen.HomeScreen
import elimi.africa.screen.ProfileScreen
import elimi.africa.screen.WelcomeScreen
import elimi.africa.screen.auth.LoginScreen
import elimi.africa.screen.auth.SigninScreen
import elimi.africa.screen.auth.SignupScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Splash.route){
            AnimatedSplashScreen(navController = navController)
        }

        composable(route = Screen.Signin.route){
            SigninScreen(navController = navController)
        }

        composable(route = Screen.Signup.route){
            SignupScreen(navController = navController)
        }

        composable(route = Screen.Login.route){
            LoginScreen(navController = navController )
        }

        composable(route = Screen.Profile.route){
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.Home.route){
            HomeScreen()
        }
    }

}