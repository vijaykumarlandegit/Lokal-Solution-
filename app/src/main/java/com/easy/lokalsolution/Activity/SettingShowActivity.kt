package com.easy.lokalsolution.Activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.databinding.ActivitySettingShowBinding

class SettingShowActivity() : AppCompatActivity() {
    var binding: ActivitySettingShowBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingShowBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        val type: String? = getIntent().getStringExtra("type")
        if ((type == "contactus")) {
            binding!!.contacusview.setVisibility(View.VISIBLE)
        }
        if ((type == "aboutus")) {
            binding!!.aboutusview.setVisibility(View.VISIBLE)
        }
        if ((type == "pp")) {
            binding!!.privacypolicyview.setVisibility(View.VISIBLE)
        }
        if ((type == "tc")) {
            binding!!.termconditionview.setVisibility(View.VISIBLE)
        }
        binding!!.contactuscontact.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:7028297606"))
                startActivity(intent)
            }
        })
        binding!!.contactuswhatsapp.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val wn: String = "https://wa.me/+917028297606?text= Hi is anyone available?"
                val intent: Intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(wn))
                startActivity(intent)
            }
        })
        binding!!.contactusemail.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                try {
                    val intent: Intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "help@resieasy.com"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "I have problem in ResiEasy Application")
                    intent.putExtra(Intent.EXTRA_TEXT, "Write your problem")
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        this@SettingShowActivity,
                        "Something is wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        binding!!.back.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                finish()
            }
        })
    }
}