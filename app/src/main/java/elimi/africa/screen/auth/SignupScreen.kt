package elimi.africa.screen.auth


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import elimi.africa.R
import elimi.africa.navigation.Screen
import elimi.africa.ui.theme.ElimiTheme

@Composable
fun SignupScreen(navController: NavHostController) {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

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
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ){
                ConstraintLayout {
                    val ( heading, fields, remember, btns, continua, tiny, login) = createRefs()
                    val guideLine = createGuidelineFromTop(0.09f)

                    Text(
                        text = "Create an Account",
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .constrainAs(heading){
                                top.linkTo(guideLine, margin= 15.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .constrainAs(fields) {
                                top.linkTo(heading.bottom, margin = 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }){

                        TextField(
                            value = emailValue.value,
                            onValueChange = { emailValue.value = it },
                            label = { Text(text = "Email")},
                            placeholder = { Text(text= "Email")},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
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
                            modifier = Modifier.fillMaxWidth(0.8f),
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


                        TextField(
                            value = confirmPasswordValue.value,
                            onValueChange = { confirmPasswordValue.value = it },
                            label = { Text(text = "Confirm Password")},
                            placeholder = { Text(text= "Confirm Password")},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            leadingIcon = {
                                IconButton(onClick = {}){
                                    Icon(painter = painterResource(id = R.drawable.ic_baseline_lock_24),
                                        contentDescription = "Password Icon Lock",
                                        tint = Color.LightGray
                                    )
                                }
                            },
                            trailingIcon = {
                                IconButton(onClick = { confirmPasswordVisibility.value = !confirmPasswordVisibility.value })  {
                                    Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                                        contentDescription = "Password Icon",
                                        tint = if(confirmPasswordVisibility.value) Color.Black else Color.Gray
                                    )
                                }
                            },
                            visualTransformation = if(confirmPasswordVisibility.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                        )

                    }

                    Row(modifier = Modifier.constrainAs(remember){
                        top.linkTo(fields.bottom, margin = 12.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                        Checkbox(
                            checked = rememberState.value,
                            modifier = Modifier.padding(16.dp),
                            onCheckedChange ={ rememberState.value = it} )
                        Text(text = "Remember me", modifier = Modifier.padding(16.dp))
                    }

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF8D161A),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier  = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .constrainAs(btns) {
                                top.linkTo(remember.bottom, margin = 15.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ){
                        Text(text="Sign up")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .constrainAs(continua) {
                                top.linkTo(btns.bottom, margin = 20.dp)
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


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .constrainAs(tiny) {
                                top.linkTo(continua.bottom, margin = 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ){

                        Carder( icon = R.drawable.ic_icons8_facebook_circled__1_){
                            println("Facebook Login")
                        }
                        Carder( icon =  R.drawable.ic_icons8_google){
                            println("Gmail Login")
                        }
                        Carder(  icon = R.drawable.ic_icons8_apple_logo){
                            println("Apple Login")
                        }
                    }


                    Row(modifier = Modifier
                        .constrainAs(login) {
                            top.linkTo(tiny.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }){
                        Text(text= "Already have account ")
                        Text(text="Login", color = Color(0xFF8D161A), modifier = Modifier.clickable{  navController.navigate(
                            Screen.Login.route) })
                    }

                }
            }
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Carder(icon: Int, onClick: () -> Unit){
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth()
            .height(40.dp)

    ){
        Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    val navController = rememberNavController()
    ElimiTheme {
        SignupScreen(navController)
    }
}
