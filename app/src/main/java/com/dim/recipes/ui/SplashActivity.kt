package com.dim.recipes.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.dim.recipes.MainActivity
import com.dim.recipes.R
import com.dim.recipes.api.FavouriteRecipeFirebaseDatabase
import com.dim.recipes.data.RecipeRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        hideSystemUI()

        RecipeRepository.seedDatabase(10)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            FavouriteRecipeFirebaseDatabase.userLoggedIn = true
            FavouriteRecipeFirebaseDatabase.create(FirebaseAuth.getInstance().currentUser!!)
        } else {
            FavouriteRecipeFirebaseDatabase.userLoggedIn = false
        }

        splash_motionlayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
            // Set the content to appear under the system bars so that the
            // content doesn't resize when the system bars hide and show.
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // Hide the nav bar and status bar
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}