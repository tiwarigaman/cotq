package com.example.myapplication54656494458548

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity


class welcome_activity : AppCompatActivity() {
    var topAnim : Animation? = null
    var bottomAnim : Animation? = null
    private var imageBrain : ImageView? = null
    private var AppName : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        imageBrain= findViewById(R.id.btn_image)
        AppName = findViewById(R.id.btn_cotq)

        imageBrain?.animation = topAnim
        AppName?.animation = bottomAnim


        val updateHandler = Handler()

        val runnable = Runnable {
            updateDisplay() // some action(s)
        }
        updateHandler.postDelayed(runnable, 2000)
    }

    private fun updateDisplay() {
        val intent = Intent(this,LoginActivity::class.java)
//        val (a, b) = Pair(AppName, "logo_text")
//        val options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this,android.util.Pair(a,b))
//        startActivity(intent,options.toBundle())
        startActivity(intent)
        finish()
    }
}




