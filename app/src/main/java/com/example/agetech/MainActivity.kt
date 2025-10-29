package com.example.agetech

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agetech.components.LoadingScreen
import com.example.agetech.model.Login
import com.example.agetech.navigation.AgeTechNavigation
import com.example.agetech.ui.theme.AgeTechTheme
import com.example.agetech.view.LoginUI
import com.example.agetech.view.PaymentsBodyUI
import com.example.agetech.view.PaymentsUI
import com.example.agetech.view.previewPayments
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
     override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AgeTechNavigation()
            //LoadingScreen()
            //LoginUI()
            //previewPayments()




            //PaymentsBodyUI(modifier = Modifier.fillMaxSize(), login = Login("","","",1,""))

                //Text("asdf")

        }
    }
}

