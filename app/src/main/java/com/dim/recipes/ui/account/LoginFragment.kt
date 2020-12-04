package com.dim.recipes.ui.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dim.recipes.R
import com.dim.recipes.api.FavouriteRecipeFirebaseDatabase
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    companion object {
        const val TAG = "LoginFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchSignInFlow()
    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build() //, AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).build(), SIGN_IN_RESULT_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                if (!FavouriteRecipeFirebaseDatabase.userLoggedIn) {
                    FirebaseAuth.getInstance().currentUser?.let {
                        FavouriteRecipeFirebaseDatabase.create(it)
                    }
                    FavouriteRecipeFirebaseDatabase.userLoggedIn = true
                }
                findNavController().navigate(LoginFragmentDirections.actionLoginToNavigationAccount())
            } else {
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
                findNavController().navigate(LoginFragmentDirections.actionLoginToNavigationAccount())
            }
        }
    }
}