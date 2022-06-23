package elimi.africa.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import elimi.africa.ui.theme.ElimiTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elimi.africa.R
import elimi.africa.navigation.Screen

@Composable
fun SigniinScreen(navController: NavHostController) {

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
                    val ( image, heading, btns, divider, signin, signup) = createRefs()
                    val guideLine = createGuidelineFromTop(0.08f)

                    Image(
                        painter = painterResource(id = R.drawable.signin),
                        contentDescription = "Sign in Image",
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .fillMaxHeight(.3f)
                            .size(280.dp)
                            .constrainAs(image) {
                                top.linkTo(guideLine)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.Center
                    )

                    Text(
                        text = "Let's sign you in",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .constrainAs(heading){
                                top.linkTo(image.bottom, margin= 15.dp)
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
                            .constrainAs(btns) {
                                top.linkTo(heading.bottom, margin = 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ){

                        SigninBtns(
                            content = "Continue with Facebook",
                            icon = R.drawable.ic_icons8_facebook_circled__1_

                        ){
                            println("Facebook Login")
                        }
                        SigninBtns(content = "Continue with Gmail",
                            icon = R.drawable.ic_icons8_google,
                        ){
                            println("Gmail Login")
                        }
                        SigninBtns(content = "Continue with Apple",
                            icon =  R.drawable.ic_icons8_apple_logo,
                        ){
                            println("Apple Login")
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .constrainAs(divider) {
                                top.linkTo(btns.bottom, margin = 20.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }){


                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = null,
                            tint = Color.LightGray,

                        )
                        Text(text=" or ")
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = null,
                            tint = Color.LightGray,

                        )

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
                            .constrainAs(signin) {
                                top.linkTo(divider.bottom, margin = 15.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ){
                        Text(text="Sign in with Password")
                    }


                    Row(modifier = Modifier
                        .constrainAs(signup) {
                            top.linkTo(signin.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }){
                        Text(text= "Don't have account? ")
                        Text(text="Sign up", color = Color(0xFF8D161A), modifier = Modifier
                            .clickable{
                                navController.navigate(Screen.Signup.route)
                            })
                    }

                }
            }
        }

    }
}


@Composable
fun SigninBtns(content: String, icon: Int, onClick: () -> Unit){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth()
            .height(40.dp)
    ){
        Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = content)

    }
}