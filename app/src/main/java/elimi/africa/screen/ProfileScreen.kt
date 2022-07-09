package elimi.africa.screen

import android.net.Uri

import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.rememberImagePainter

import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.toSize
import elimi.africa.R
import elimi.africa.navigation.Screen
import elimi.africa.screen.auth.RowLayer
import elimi.africa.screen.auth.SignupScreen
import elimi.africa.ui.theme.ElimiTheme
import elimi.africa.util.rememberDatePickerDialog
import elimi.africa.util.rememberFragmentManager
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ProfileScreen(navController : NavHostController){

            Scaffold( content = { Profile(navController) } )
}


@Composable
fun Profile(navController : NavHostController){

    val nameValue = remember { mutableStateOf("") }

    val userNameValue = remember { mutableStateOf("") }

    val dobValue = remember { mutableStateOf("") }

    val occupationValue = remember { mutableStateOf("") }


    val phoneValue = remember { mutableStateOf("") }

    val notification = rememberSaveable { mutableStateOf("") }


    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Male","Female")
    var selectedText by remember { mutableStateOf("") }
    var dropDownWidth by remember { mutableStateOf(0) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var date by remember {  mutableStateOf(TextFieldValue(DATE_FORMAT.format(Date()))) }

    val fragmentManager = rememberFragmentManager()

    val datePicker = rememberDatePickerDialog(
        select = DATE_FORMAT.parse(date.text),
        title = R.string.select_date,
    ) { date = TextFieldValue(DATE_FORMAT.format(it)) }

    if(notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        ConstraintLayout {
            val (avatar, fields, gender, btns) = createRefs()
            val guideLine = createGuidelineFromTop(0.1f)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 10.dp),

                ) {
                Image(
                    painterResource(id = R.drawable.back_icon),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.TopEnd,
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .clickable { navController.popBackStack() }
                )

                Text(text = "Fill your profile")

                Text(text = "Save", modifier = Modifier.clickable {
                    notification.value = "Saved!"
                })
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .constrainAs(avatar) {
                        top.linkTo(guideLine, margin = 15.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {

                  ProfileImage()
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .constrainAs(fields) {
                        top.linkTo(avatar.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {

                TextField(
                    value = nameValue.value,
                    onValueChange = { nameValue.value = it },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleY = 0.9F, scaleX = 1F),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.LightGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        backgroundColor = Color(0x128D161A)
                    )
                )

                TextField(
                    value = userNameValue.value,
                    onValueChange = { userNameValue.value = it },
                    label = { Text(text = "Username") },
                    placeholder = { Text(text = "Username") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleY = 0.9F, scaleX = 1F),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.LightGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        backgroundColor = Color(0x128D161A)
                    )
                )

                TextField(
                    value = date,
                    onValueChange = { date = it },
                    trailingIcon = {
                        IconButton({ datePicker.show(fragmentManager, "Date") }) {
                            Icon(Icons.Default.DateRange, contentDescription = null)
                        }
                    },
//                    value = dobValue.value,
//                    onValueChange = { dobValue.value = it },
                    label = { Text(text = "Date of Birth") },
                    placeholder = { Text(text = "Date of Birth (DD/MM/YYYY)") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleY = 0.9F, scaleX = 1F),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.LightGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        backgroundColor = Color(0x128D161A)
                    )
                )

                TextField(
                    value = occupationValue.value,
                    onValueChange = { occupationValue.value = it },
                    label = { Text(text = "Occupation") },
                    placeholder = { Text(text = "Occupation") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleY = 0.9F, scaleX = 1F),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.LightGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        backgroundColor = Color(0x128D161A)
                    )
                )
                Column(modifier = Modifier.fillMaxWidth()){
                    TextField(
                        value = selectedText,
                        readOnly = true,
                        onValueChange = { selectedText = it },

                        placeholder = { Text(text = "Gender") },
                        singleLine = true,
                        trailingIcon = {
                            Icon(icon,"contentDescription", Modifier.clickable { expanded = !expanded })
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(scaleY = 0.9F, scaleX = 1F)
                            .onSizeChanged {
                                dropDownWidth = it.width
                            },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.LightGray,
                            disabledTextColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            backgroundColor = Color(0x128D161A)
                        )
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },

                        modifier = Modifier
                            .width(
                                with(LocalDensity.current)
                                {dropDownWidth.toDp()})
                    ){

                        suggestions.forEach { label ->
                            DropdownMenuItem(onClick = {
                                selectedText = label
                            }) {
                                Text(text = label, color = Color.Black)
                            }
                        }

                    }
                }

                TextField(
                    value = phoneValue.value,
                    onValueChange = { phoneValue.value = it },
                    label = { Text(text = "Phone number") },
                    placeholder = { Text(text = "Phone number") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleY = 0.9F, scaleX = 1F),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.LightGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        backgroundColor = Color(0x128D161A)
                    )
                )
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8D161A),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .constrainAs(btns) {
                        top.linkTo(fields.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(text = "Continue")
            }
        }
    }
}



//@Composable
//fun DownMenuTextField(
//    @StringRes titleId: Int,
//    selectedText: String,
//    suggestions: List<String>,
//    onValueChange: (String) -> Unit
//){
//    var expanded by remember { mutableStateOf(false)}
//    var textFieldSize by remember { mutableStateOf(Size.Zero)}
//    val icon = if(expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown
//
//    Column{
//        TextField(
//            value = selectedText,
//            readOnly = true,
//            onValueChange = { onValueChange(it)},
//            modifier = Modifier
//                .fillMaxWidth()
//                .onGloballyPositioned { coordinates ->
//                    textFieldSize = coordinates.size.toSize()
//                },
//            label = {
//                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium){
//                    Text(
//                        text = stringResource(titleId),
//                        style = MaterialTheme.typography.body2
//                    )
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .onFocusChanged {
//                    expanded = it.isFocused
//                }
//        )
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = {
//                expanded = false
//                focusManager.clearFocus()
//                },
//            modifier = Modifier.fillMaxWidth()
//            ) {
//                suggestions.forEach {
//                    label ->
//                    DropdownMenuItem(onClick = {
//                        onValueChange(label)
//                        expanded = false
//                        focusManager.clearFocus()
//                    }) {
//                        Text(text=label)
//                    }
//                }
//
//        }
//
//    }
//}

@Composable
fun ProfileImage(){
    val imageUri = rememberSaveable { mutableStateOf("")}
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty())
            R.drawable.user_avatar
        else
            imageUri.value
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        uri?.let {
            imageUri.value = it.toString()
        }
    }

    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(Modifier.background(Color.Transparent)){
            Card(shape = CircleShape,
                modifier = Modifier
                    .padding(5.dp)
                    .size(110.dp)
            ){
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {

                        },
                    contentScale = ContentScale.Crop
                )

            }

            IconButton(
                onClick = {launcher.launch("image/*")},
                modifier = Modifier
                    .background(color = Color(0xFF8D161A))
                    .align(Alignment.BottomEnd)
                    .size(24.dp)
            ){
                Icon( painter = painterResource(id = R.drawable.editor),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(15.dp)

                )


            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreen(){
    val navController = rememberNavController()
    ElimiTheme {
        SignupScreen(navController)
    }
}