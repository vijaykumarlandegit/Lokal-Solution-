package com.easy.lokalsolution.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.Class.IdClass
import com.easy.lokalsolution.databinding.ActivityComIdDataShowBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class ComIdDataShowActivity() : AppCompatActivity() {
    var binding: ActivityComIdDataShowBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComIdDataShowBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        val id: String? = getIntent().getStringExtra("id")
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("Id").document((id)!!)
            .get().addOnSuccessListener(object : OnSuccessListener<DocumentSnapshot> {
                public override fun onSuccess(documentSnapshot: DocumentSnapshot) {
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

                    binding!!.idname.setText(type)
                    binding!!.experiseperiod.setText(exptime)
                    binding!!.oname.setText(umname)
                    binding!!.contact.setText(ucontact)
                    if (!(image == "No")) {
                        binding!!.extraimage.setVisibility(View.VISIBLE)
                        Picasso.get().load(image).into(binding!!.extraimage)
                    } else {
                        binding!!.extraimage.setVisibility(View.GONE)
                    }
                    if ((shopname == "!@#$%") && (shopaddress == "!@#$%")) {
                        binding!!.shopview.setVisibility(View.GONE)
                    } else {
                        binding!!.shopview.setVisibility(View.VISIBLE)
                        binding!!.shopname.setText("Shop Name : " + shopname)
                        binding!!.shopaddress.setText("Shop Address : " + shopaddress)
                    }
                    if ((starttime == "!@#$%")) {
                        binding!!.customtimeview.setVisibility(View.GONE)
                        binding!!.anytimetext.setVisibility(View.VISIBLE)
                    } else {
                        binding!!.customtimeview.setVisibility(View.VISIBLE)
                        binding!!.anytimetext.setVisibility(View.GONE)
                        binding!!.customtime.setText(starttime)
                    }
                    if ((coursename == "!@#$%") && (coursedetails == "!@#$%")) {
                        binding!!.courseview.setVisibility(View.GONE)
                    } else {
                        binding!!.courseview.setVisibility(View.VISIBLE)
                        binding!!.couresename.setText(coursename)
                        binding!!.coursedetaills.setText(coursedetails)
                    }
                    if (moredetails != null) {
                        if (moredetails.isEmpty()) {
                            binding!!.moreview.setVisibility(View.GONE)
                        } else {
                            binding!!.moreview.setVisibility(View.VISIBLE)
                            binding!!.moreaboutwork.setText(moredetails)
                        }
                    }
                    if (uwhatsapp != null) {
                        if (uwhatsapp.isEmpty()) {
                            binding!!.whatsappview.setVisibility(View.GONE)
                        } else {
                            binding!!.whatsappview.setVisibility(View.VISIBLE)
                            binding!!.whatsapp.setText(uwhatsapp)
                        }
                    }
                    if (uemail != null) {
                        if (uemail.isEmpty()) {
                            binding!!.emailview.setVisibility(View.GONE)
                        } else {
                            binding!!.emailview.setVisibility(View.VISIBLE)
                            binding!!.mail.setText(uemail)
                        }
                    }
                    if (uwhatsapp != null) {
                        if (!uwhatsapp.isEmpty()) {
                            binding!!.cmswhatsapp.setVisibility(View.VISIBLE)
                            binding!!.cmswhatsapp.setOnClickListener(object : View.OnClickListener {
                                public override fun onClick(view: View) {
                                    val intent: Intent = Intent(Intent.ACTION_VIEW)
                                    intent.setData(Uri.parse("https://wa.me/+91" + uwhatsapp + "?text= Hi is anyone there ?"))
                                    startActivity(intent)
                                }
                            })
                        } else {
                            binding!!.cmswhatsapp.setVisibility(View.GONE)
                        }
                    }
                    binding!!.cmscontact.setOnClickListener(object : View.OnClickListener {
                        public override fun onClick(view: View) {
                            val intent: Intent = Intent(Intent.ACTION_DIAL)
                            intent.setData(Uri.parse("tel:" + ucontact))
                            startActivity(intent)
                        }
                    })
                }
            })
    }
}