package com.easy.lokalsolution.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity() : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        auth = FirebaseAuth.getInstance()
        val thread: Thread = object : Thread() {
            public override fun run() {
                try {
                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    if (auth!!.currentUser != null) {
                        val intent1: Intent =
                            Intent(this@SplashScreenActivity, MainActivity::class.java)
                        startActivity(intent1)
                        finishAffinity()
                    } else {
                        val intent11: Intent =
                            Intent(this@SplashScreenActivity, SigninActivity::class.java)
                        intent11.putExtra("onlysignin", "1234")
                        startActivity(intent11)
                        finishAffinity()
                    }
                }
            }
        }
        thread.start()
    }
}