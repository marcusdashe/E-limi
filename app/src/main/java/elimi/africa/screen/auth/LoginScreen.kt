package elimi.africa.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import elimi.africa.R
import elimi.africa.navigation.Screen
import elimi.africa.ui.theme.ElimiTheme


@Composable
fun LoginScreen(navController : NavHostController){

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    val rememberState = remember { mutableStateOf(false ) }

    ElimiTheme {
        Surface(
            color = Color.White
        ){
            Card(
                elevation = 0.dp,
                border = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 20.dp)
            ){
                ConstraintLayout {
                    val ( heading, fields, remember, btns, continua, tiny, login, forgot) = createRefs()
                    val guideLine = createGuidelineFromTop(0.1f)

                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(start = 10.dp),

                        ){
                        Image(
                            painterResource(id = R.drawable.back_icon),
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth,
                            alignment = Alignment.TopEnd,
                            modifier = Modifier
                                .height(25.dp)
                                .width(25.dp)
                                .clickable{navController.popBackStack()}
                        )
                    }

                    Column(horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .constrainAs(heading) {
                                top.linkTo(guideLine, margin = 28.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ){
                        Text(
                            text = "Welcome back!",
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .constrainAs(fields) {
                                top.linkTo(heading.bottom, margin = 20.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }){

                        TextField(
                            value = emailValue.value,
                            onValueChange = { emailValue.value = it },
                            label = { Text(text = "Email")},
                            placeholder = { Text(text= "Email")},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth().scale(scaleY = 0.9F, scaleX = 1F),
                            shape = RoundedCornerShape(15.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.LightGray,
                                disabledTextColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                backgroundColor = Color(0x128D161A)),
                            leadingIcon = {
                                IconButton(onClick = {}){
                                    Icon(painter = painterResource(id = R.drawable.ic_baseline_email_24),
                                        contentDescription = "Email Icon",
                                        tint = Color.LightGray
                                    )
                                }
                            }
                        )


                        TextField(
                            value = passwordValue.value,
                            onValueChange = { passwordValue.value = it },
                            label = { Text(text = "Password")},
                            placeholder = { Text(text= "Password")},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth().scale(scaleY = 0.9F, scaleX = 1F),
                            shape = RoundedCornerShape(15.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.LightGray,
                                disabledTextColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                backgroundColor = Color(0x128D161A)),
                            leadingIcon = {
                                IconButton(onClick = {}){
                                    Icon(painter = painterResource(id = R.drawable.ic_baseline_lock_24),
                                        contentDescription = "Password Icon Lock",
                                        tint = Color.LightGray
                                    )
                                }
                            },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value })  {
                                    Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                                        contentDescription = "Password Icon Eye",
                                        tint = if(passwordVisibility.value) Color.Black else Color.Gray
                                    )
                                }
                            },
                            visualTransformation = if(passwordVisibility.value) VisualTransformation.None
                            else PasswordVisualTransformation()
                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.constrainAs(remember){
                            top.linkTo(fields.bottom, margin = 5.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                        Checkbox(
                            colors = CheckboxDefaults.colors(Color(0xFF8D161A)),
                            checked = rememberState.value,
                            modifier = Modifier.padding(5.dp),
                            onCheckedChange ={ rememberState.value = it} )
                        Text(text = "Remember me", modifier = Modifier.padding(0.dp))
                    }

                    Button(
                        onClick = { authenticate(navController) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF8D161A),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier  = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .constrainAs(btns) {
                                top.linkTo(remember.bottom, margin = 5.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ){
                        Text(text="Login")
                    }

                    Text(
                        text="Forgot Password", color = Color(0xFF8D161A),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(forgot) {
                                top.linkTo(btns.bottom, margin = 5.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .clickable{
                        navController.navigate(Screen.Login.route) }
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .constrainAs(continua) {
                                top.linkTo(forgot.bottom, margin = 15.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }){


                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = null,
                            tint = Color.LightGray,

                            )
                        Text(text=" Continue with ")
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = null,
                            tint = Color.LightGray,
                        )

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(0.9f).constrainAs(tiny) {
                            top.linkTo(continua.bottom, margin = 12.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ){

                            RowLayer( icon = R.drawable.ic_icons8_facebook_circled__1_){
                                println("Facebook Login")
                            }
                            RowLayer( icon =  R.drawable.ic_icons8_google){
                                println("Gmail Login")
                            }
                            RowLayer(  icon = R.drawable.ic_icons8_apple_logo){
                                println("Apple Login")
                            }
                        }
                    }



                    Row(modifier = Modifier
                        .constrainAs(login) {
                            top.linkTo(tiny.bottom, margin = 10.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }){
                        Text(text= "Don't have an account? ")
                        Text(text="Sign up", color = Color(0xFF8D161A), modifier = Modifier.clickable{
                            navController.navigate(Screen.Signup.route) })
                    }

                }
            }
        }

    }

}

fun authenticate(navController: NavHostController) {
    navController.navigate(Screen.Profile.route)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowLayer(icon: Int, onClick: () -> Unit){
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(10.dp)
            .width(55.dp)
            .height(35.dp)
            .fillMaxWidth()

    ){
        Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(50.dp).fillMaxWidth(.8f))

    }
}


