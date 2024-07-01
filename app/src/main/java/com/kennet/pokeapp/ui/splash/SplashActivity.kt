package com.kennet.pokeapp.ui.splash

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
import com.kennet.pokeapp.databinding.ActivitySplashBinding
import com.kennet.pokeapp.ui.login.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.animationInit.addAnimatorUpdateListener {
            // End Animation to Auth
            it.doOnEnd {
                startActivity(AuthActivity.create(this))
            }

        }
     }


}


