package com.easy.lokalsolution.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.Class.IdClass
import com.easy.lokalsolution.databinding.ActivityComIdDataShowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class ComIdDataShowActivity() : AppCompatActivity() {
    var binding: ActivityComIdDataShowBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComIdDataShowBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val id: String? = intent.getStringExtra("id")
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("Id").document((id)!!)
            .get().addOnSuccessListener { documentSnapshot ->
                val snapshot: IdClass? = documentSnapshot.toObject(IdClass::class.java)
                val type: String? = snapshot?.type
                val coursename: String? = snapshot?.cname
                val coursedetails: String? = snapshot?.cdetail
                val exptime: String? = snapshot?.exptime
                val shopname: String? = snapshot?.sname
                val shopaddress: String? = snapshot?.saddress
                val starttime: String? = snapshot?.customtime
                val moredetails: String? = snapshot?.more
                val umname: String? = snapshot?.uname
                val ucontact: String? = snapshot?.ucontact
                val uwhatsapp: String? = snapshot?.uwhatsapp
                val uemail: String? = snapshot?.uemail
                val image: String? = snapshot?.image

                binding!!.idname.text = type
                binding!!.experiseperiod.text = exptime
                binding!!.oname.text = umname
                binding!!.contact.setText(ucontact)
                if (image != "No") {
                    binding!!.extraimage.visibility = View.VISIBLE
                    Picasso.get().load(image).into(binding!!.extraimage)
                } else {
                    binding!!.extraimage.visibility = View.GONE
                }
                if ((shopname == "!@#$%") && (shopaddress == "!@#$%")) {
                    binding!!.shopview.visibility = View.GONE
                } else {
                    binding!!.shopview.visibility = View.VISIBLE
                    binding!!.shopname.text = "Shop Name : " + shopname
                    binding!!.shopaddress.text = "Shop Address : " + shopaddress
                }
                if ((starttime == "!@#$%")) {
                    binding!!.customtimeview.visibility = View.GONE
                    binding!!.anytimetext.visibility = View.VISIBLE
                } else {
                    binding!!.customtimeview.visibility = View.VISIBLE
                    binding!!.anytimetext.visibility = View.GONE
                    binding!!.customtime.text = starttime
                }
                if ((coursename == "!@#$%") && (coursedetails == "!@#$%")) {
                    binding!!.courseview.visibility = View.GONE
                } else {
                    binding!!.courseview.visibility = View.VISIBLE
                    binding!!.couresename.text = coursename
                    binding!!.coursedetaills.text = coursedetails
                }
                if (moredetails != null) {
                    if (moredetails.isEmpty()) {
                        binding!!.moreview.visibility = View.GONE
                    } else {
                        binding!!.moreview.visibility = View.VISIBLE
                        binding!!.moreaboutwork.text = moredetails
                    }
                }
                if (uwhatsapp != null) {
                    if (uwhatsapp.isEmpty()) {
                        binding!!.whatsappview.visibility = View.GONE
                    } else {
                        binding!!.whatsappview.visibility = View.VISIBLE
                        binding!!.whatsapp.text = uwhatsapp
                    }
                }
                if (uemail != null) {
                    if (uemail.isEmpty()) {
                        binding!!.emailview.visibility = View.GONE
                    } else {
                        binding!!.emailview.visibility = View.VISIBLE
                        binding!!.mail.text = uemail
                    }
                }
                if (uwhatsapp != null) {
                    if (uwhatsapp.isNotEmpty()) {
                        binding!!.cmswhatsapp.visibility = View.VISIBLE
                        binding!!.cmswhatsapp.setOnClickListener {
                            val intent: Intent = Intent(Intent.ACTION_VIEW)
                            intent.setData(Uri.parse("https://wa.me/+91$uwhatsapp?text= Hi is anyone there ?"))
                            startActivity(intent)
                        }
                    } else {
                        binding!!.cmswhatsapp.visibility = View.GONE
                    }
                }
                binding!!.cmscontact.setOnClickListener {
                    val intent: Intent = Intent(Intent.ACTION_DIAL)
                    intent.setData(Uri.parse("tel:" + ucontact))
                    startActivity(intent)
                }
            }
    }
}