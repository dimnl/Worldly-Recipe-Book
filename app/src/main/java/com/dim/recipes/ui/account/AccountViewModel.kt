package com.dim.recipes.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.dim.recipes.models.api.FirebaseUserLiveData

class AccountViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}