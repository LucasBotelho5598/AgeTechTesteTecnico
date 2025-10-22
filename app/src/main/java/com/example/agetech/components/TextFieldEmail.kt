package com.example.agetech.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldEmail(email: String, updateStateEmail: (String) -> Unit, validatorHasErrors: Boolean){
    TextField(
        value = email,
        onValueChange = updateStateEmail,
        modifier = Modifier
            .width(450.dp)
            .height(70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFEBEDF2),
            focusedContainerColor = Color(0xFFEBEDF2)
        ),


        placeholder = {
            Text("Email")
        },
        isError = validatorHasErrors,
        supportingText = {
            if (validatorHasErrors) {
                Text("Email Invalid")
            }
        }

    )

}