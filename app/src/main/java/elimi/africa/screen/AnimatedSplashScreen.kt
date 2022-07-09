package elimi.africa.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import elimi.africa.R
import elimi.africa.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun AnimatedSplashScreen(navController: NavHostController){
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        navController.navigate(Screen.Signin.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float){
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Image(
            modifier = Modifier
                .size(280.dp)
                .alpha(alpha = alpha)
                .fillMaxWidth(.9f)
                .fillMaxHeight(.9f),
            painter = painterResource(id = R.drawable.ilimi),
            contentDescription = "Splash Screen Elimi Logo")
    }
}