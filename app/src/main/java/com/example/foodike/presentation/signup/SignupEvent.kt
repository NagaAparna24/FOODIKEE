
package com.example.foodike.presentation.signup

sealed class SignupEvent {
    data class EnteredEmail(val value: String): SignupEvent()
    data class EnteredPassword(val value: String): SignupEvent()
    data class PerformSignup(val onSuccess: () -> Unit): SignupEvent()
}
