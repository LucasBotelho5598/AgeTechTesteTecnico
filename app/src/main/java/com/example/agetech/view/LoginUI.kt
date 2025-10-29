package com.example.agetech.view

import ButtonLogin
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import com.example.agetech.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.agetech.viewmodel.LoginViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUI(loginViewModel: LoginViewModel = hiltViewModel(), onLogin: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("Login", fontWeight = FontWeight.Bold, maxLines = 1) },)
        }
    ) { innerPadding ->
        BodyUI(
            modifier = Modifier.padding(innerPadding),
            email = loginViewModel.email,
            updateStateEmail = { input -> loginViewModel.updateEmail(input) },
            validatorHasErrors = loginViewModel.emailHasErrors,
            password = loginViewModel.password,
            updateStatePassword = { inputPassword -> loginViewModel.updatePassword(inputPassword) },
            //passwordHasErrors = loginViewModel.passwordHasErrors,
            onLogin = onLogin
        )
    }
}



@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun BodyUI(
    modifier: Modifier,
    email: String,
    password: String,
    updateStateEmail: (String) -> Unit,
    updateStatePassword: (String) -> Unit,
    validatorHasErrors: Boolean,
    onLogin: () -> Unit,
    //passwordHasErrors: Boolean

) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column() {
            Spacer(Modifier.height(100.dp))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(450.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(Modifier.height(20.dp))
            TextField(
                value = email,
                onValueChange = updateStateEmail,
                modifier = Modifier
                    .width(450.dp)
                    .height(70.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFEBEDF2),
                    focusedContainerColor = Color(0xFFEBEDF2),
                    disabledContainerColor = Color(0xFFEBEDF2),
                    focusedIndicatorColor = Color(0xFFEBEDF2),
                    unfocusedIndicatorColor =Color(0xFFEBEDF2),
                    disabledIndicatorColor = Color(0xFFEBEDF2),
                ),


                placeholder = {
                    Text("Email", color = Color(0xFF5C738A))
                },
                isError = validatorHasErrors,
                supportingText = {
                    if (validatorHasErrors) {
                        Text("Email Invalid")
                    }
                }

            )
            Spacer(Modifier.height(20.dp))
            //password
            var showPassword by remember { mutableStateOf(false) }
            TextField(
                value = password,
                onValueChange =updateStatePassword,
                /*visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    IconButton(onClick = { showPassword = !showPassword })
                    {

                    }
                },*/
                        modifier = Modifier
                    .width(450.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFEBEDF2),
                    focusedContainerColor = Color(0xFFEBEDF2),
                    disabledContainerColor = Color(0xFFEBEDF2),
                    focusedIndicatorColor = Color(0xFFEBEDF2),
                    unfocusedIndicatorColor =Color(0xFFEBEDF2),
                    disabledIndicatorColor = Color(0xFFEBEDF2),


                ),
                placeholder = {
                    Text("Password",color = Color(0xFF5C738A))
                },
                /*isError = passwordHasErrors,
                supportingText = {
                    if (passwordHasErrors) {
                        Text("Senha Invalid")
                    }
                }*/

            )
            Spacer(Modifier.height(20.dp))

            val mutableInteractionSource = remember {
                MutableInteractionSource()
            }
            val pressed = mutableInteractionSource.collectIsPressedAsState()
            if(email == "age@gmail.com" && password =="123456") {
                Button(
                    onClick = {
                        onLogin()
                    },
                    Modifier
                        .width(450.dp)
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonColors(
                        contentColor = Color(0xFF358073),
                        containerColor = Color(0xFF358073),
                        disabledContainerColor = Color(0xFF358073),
                        disabledContentColor = Color(0xFF358073),
                    ),
                ) {
                    Text(
                        "Login",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )

                }
            }else{
                Button(
                    onClick = {

                    },
                    Modifier
                        .width(450.dp)
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonColors(
                        contentColor = Color(0xFF000000),
                        containerColor = Color(0xFF000000),
                        disabledContainerColor = Color(0xFF000000),
                        disabledContentColor = Color(0xFF000000),
                    ),
                ) {
                    Text(
                        "Login",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )

                }
            }

        }
    }
}
