package com.easy.lokalsolution.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easy.lokalsolution.databinding.ActivityUploadFromHareBinding;

public class UploadFromHareActivity extends AppCompatActivity {

    ActivityUploadFromHareBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadFromHareBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadFromHareActivity.this, AddNewsActivity.class);
                startActivity(intent);
            }
        });
        binding.addquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadFromHareActivity.this, AddQueryActivity.class);
                startActivity(intent);
            }
        });
        binding.createid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadFromHareActivity.this, AddIdActivity.class);
                startActivity(intent);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });


    }
}