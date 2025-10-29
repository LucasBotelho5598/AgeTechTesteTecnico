package com.example.agetech.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.agetech.model.Login
import com.example.agetech.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface BankUiState {
    data class Success(val login: List<Login> = emptyList()): BankUiState
    //object Error: BankUiState
    //object Loading: BankUiState

}

data class UiState(
    val inputEmail: String = "",
    val inputPassword: String = ""
)

@HiltViewModel
class LoginViewModel @Inject internal constructor(
    private val bankRepository: BankRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var regexPassword = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}\$")



    val emailHasErrors by derivedStateOf {
        if (email.isNotEmpty()) {
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            false
        }
    }

    /*
    val passwordHasErrors by derivedStateOf {
        if (password.isNotEmpty()) {
            password.length <= 6 || !regexPassword.matches(password)
        } else {
            false
        }
    }*/

    fun updateEmail(inputEmail:String){
        _uiState.update{
            it.copy(inputEmail =  email)
        }
        email = inputEmail
    }

    fun updatePassword(inputPassword:String){
        _uiState.update {
            it.copy(inputPassword = password)
        }
        if (inputPassword.length <= 6 || regexPassword.matches(inputPassword))  {
                password = inputPassword
            }


    }

}