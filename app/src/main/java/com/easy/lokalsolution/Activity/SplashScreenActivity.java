package com.easy.lokalsolution.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.easy.lokalsolution.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         auth=FirebaseAuth.getInstance();





        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (auth.getCurrentUser() != null) {
                        Intent intent1 = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finishAffinity();

                    } else {
                        Intent intent11 = new Intent(SplashScreenActivity.this, SigninActivity.class);
                        intent11.putExtra("onlysignin","1234");
                        startActivity(intent11);
                        finishAffinity();
                    }

                }
            }
        };
        thread.start();


    }
}