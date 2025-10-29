package com.example.agetech.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agetech.datastore.UserDataStore
import com.example.agetech.model.Login
import com.example.agetech.model.Payments
import com.example.agetech.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface PaymentsUiState {
    data class Success(val login: List<Login>, val payments: List<Payments>) : PaymentsUiState
    object Error : PaymentsUiState
    object Loading : PaymentsUiState
}

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val bankRepository: BankRepository,
    private val userDataStore: UserDataStore
) : ViewModel() {

    private val _uiState: MutableStateFlow<PaymentsUiState> = MutableStateFlow(PaymentsUiState.Loading)
    val uiState: StateFlow<PaymentsUiState> = _uiState.asStateFlow()

    init {
        getLogin()
    }

    private fun getLogin() {
        viewModelScope.launch {
            _uiState.value = PaymentsUiState.Loading
            delay(1000)
            _uiState.value = try {
                val login = bankRepository.getLogin()
                val payments = bankRepository.getPayments()
                if (login.isNotEmpty()) {
                    userDataStore.saveUser(login.first())
                }
                PaymentsUiState.Success(login, payments)
            } catch (e: IOException) {
                PaymentsUiState.Error
            } catch (e: HttpException) {
                PaymentsUiState.Error
            }
        }
    }
}
