package com.easy.lokalsolution.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.databinding.ActivityUploadFromHareBinding

class UploadFromHareActivity() : AppCompatActivity() {
    var binding: ActivityUploadFromHareBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadFromHareBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.addnews.setOnClickListener {
            val intent: Intent =
                Intent(this@UploadFromHareActivity, AddNewsActivity::class.java)
            startActivity(intent)
        }
        binding!!.addquery.setOnClickListener {
            val intent: Intent =
                Intent(this@UploadFromHareActivity, AddQueryActivity::class.java)
            startActivity(intent)
        }
        binding!!.createid.setOnClickListener {
            val intent: Intent = Intent(this@UploadFromHareActivity, AddIdActivity::class.java)
            startActivity(intent)
        }
        binding!!.back.setOnClickListener { finish() }
    }
}