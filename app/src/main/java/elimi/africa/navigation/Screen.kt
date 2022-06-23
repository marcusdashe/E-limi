package elimi.africa.navigation

sealed class Screen(val route: String){
    object Welcome: Screen(route = "welcome_screen")
    object Splash: Screen(route= "splash_screen")
    object Login: Screen(route= "login_screen")
    object Signup: Screen(route= "signup_screen")
    object Home: Screen(route= "home_screen")
}
