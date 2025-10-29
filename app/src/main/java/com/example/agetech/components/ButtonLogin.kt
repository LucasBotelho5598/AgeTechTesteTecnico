import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonLogin(){
    Button(
        onClick = {

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
}