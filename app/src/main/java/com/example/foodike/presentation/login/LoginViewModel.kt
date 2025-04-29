package com.example.foodike.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodike.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _email = mutableStateOf(
        FoodikeTextFieldState(
            hint = "Enter email or phone number"
        )
    )
    val email: State<FoodikeTextFieldState> = _email

    private val _password = mutableStateOf(
        FoodikeTextFieldState(
            hint = "Password"
        )
    )
    val password: State<FoodikeTextFieldState> = _password

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _email.value = email.value.copy(text = event.value)
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = password.value.copy(text = event.value)
            }
            is LoginEvent.PerformLogin -> {
                loginWithFirebase(email.value.text.trim(), password.value.text.trim(), event.onClick)
            }
        }
    }

    private fun loginWithFirebase(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewModelScope.launch {
                            repository.toggleLoginState()
                            onSuccess()
                        }
                    } else {
                        viewModelScope.launch {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = task.exception?.localizedMessage ?: "Login failed"
                                )
                            )
                        }
                    }
                }
        }
    }
}
