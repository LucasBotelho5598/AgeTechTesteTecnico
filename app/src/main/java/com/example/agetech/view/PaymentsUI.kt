package com.example.agetech.view

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.agetech.R
import com.example.agetech.components.ErrorScreen
import com.example.agetech.components.LoadingScreen
import com.example.agetech.model.Login
import com.example.agetech.model.Payments


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun previewPayments(paymentsViewModel: PaymentsViewModel = hiltViewModel(), navController: NavController) {
    val uiState by paymentsViewModel.uiState.collectAsStateWithLifecycle()
    PaymentsUI(paymentsUiState = uiState, navController = navController)
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsUI(
    paymentsUiState: PaymentsUiState,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        "Pagamentos",
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Image(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (paymentsUiState) {
            is PaymentsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is PaymentsUiState.Success -> PaymentsBodyUI(
                modifier = Modifier
                    .padding(innerPadding).fillMaxSize(),
                login = paymentsUiState.login.first(),
                payments = paymentsUiState.payments
            )

            is PaymentsUiState.Error -> ErrorScreen()


        }
    }
}


@Composable
fun PaymentsBodyUI(modifier: Modifier, login: Login, payments: List<Payments>) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 20.dp)
            .offset(12.dp)
    ) {
        Column() {
            Spacer(Modifier.height(100.dp))
            Text(
                "Detalhes do pagamento",
                Modifier.paddingFromBaseline(top = 50.dp),
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(50.dp))
            Text(
                "Nome: ${login.customerName}",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(10.dp))
            Row {
                Text(
                    "Agência: ${login.branchNumber} | Conta: ${login.accountNumber}",
                    fontSize = 16.sp,
                    color = Color(0xFF4A739C),
                )
            }
            Spacer(Modifier.height(30.dp))
            Text(
                "Saldo: R$ 1500,00",
                fontSize = 24.sp,
                color = Color.Black,
            )
            Spacer(Modifier.height(30.dp))
            Text(
                "Contas pagas",
                Modifier.paddingFromBaseline(top = 50.dp),
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            LazyColumn() {
                items(payments, key = { payment -> payment.id }) { payment ->
                    PayCard(payment)

                }
            }


        }
    }
}

@Composable
fun PayCard(payments: Payments) {
    Row(horizontalArrangement = Arrangement.spacedBy(80.dp)) {
        Column() {
            Text(
                "Conta de luz",
                Modifier.paddingFromBaseline(top = 50.dp),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "${payments.electricityBill}",
                Modifier.paddingFromBaseline(top = 30.dp),
                fontSize = 14.sp,
                color = Color(0xFF4A739C),
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(Modifier.width(100.dp))
        Text(
            "${payments.paymentDate}",
            Modifier.paddingFromBaseline(top = 75.dp),
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )


    }


}
