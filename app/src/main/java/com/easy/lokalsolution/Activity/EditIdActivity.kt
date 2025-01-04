package com.easy.lokalsolution.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.Class.IdClass
import com.easy.lokalsolution.Class.OwnIdClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.ActivityEditIdBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import java.util.Date
 class EditIdActivity() : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var binding: ActivityEditIdBinding? = null
    var image11: Uri? = null
    var image12: Uri? = null
    lateinit var gender: Array<String>
    var mechtype: String? = null
    var latitude = 0.0
    var longitude = 0.0
    var dialog: ProgressDialog? = null
    var dialog1: ProgressDialog? = null
    var id: String? = null
    var image: String? = null
    var item: Any? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditIdBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val adapter1 = ArrayAdapter.createFromResource(
            applicationContext,
            R.array.IdType1,
            android.R.layout.simple_spinner_item
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.subtype.adapter = adapter1
        binding!!.subtype.onItemSelectedListener = this@EditIdActivity
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Please wait, Creating your ID .....")
        dialog!!.setCancelable(false)
        id = intent.getStringExtra("id")
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("Id").document((id)!!)
            .get().addOnSuccessListener {
                val snapshot = it.toObject(IdClass::class.java)
                val type = snapshot!!.type
                val coursename = snapshot.cname
                val coursedetails = snapshot.cdetail
                val exptime = snapshot.exptime
                val shopname = snapshot.sname
                val shopaddress = snapshot.saddress
                val starttime = snapshot.customtime
                val moredetails = snapshot.more
                val umname = snapshot.uname
                val ucontact = snapshot.ucontact
                val uwhatsapp = snapshot.uwhatsapp
                val uemail = snapshot.uemail
                image = snapshot.image
                binding!!.typetext.text = type
                binding!!.exptime.setText(exptime)
                binding!!.moredetails.setText(moredetails)
                binding!!.username.setText(umname)
                binding!!.contactno.setText(ucontact)
                binding!!.whatsappno.setText(uwhatsapp)
                binding!!.cmmail.setText(uemail)
                if (image != "No") {
                    binding!!.imagee.visibility = View.VISIBLE
                    Picasso.get().load(image).placeholder(R.drawable.placehald)
                        .into(binding!!.imagee)
                } else {
                    binding!!.imagee.visibility = View.GONE
                }
                if ((coursename == "!@#$%") && (coursedetails == "!@#$%")) {
                    binding!!.yescourcevieww.visibility = View.GONE
                    binding!!.nocourse.isChecked = true
                } else {
                    binding!!.yescourcevieww.visibility = View.VISIBLE
                    binding!!.yescourse.isChecked = true
                    binding!!.coursename.setText(coursename)
                    binding!!.coursedetails.setText(coursedetails)
                }
                if ((shopname == "!@#$%") && (shopaddress == "!@#$%")) {
                    binding!!.yesshopvieww.visibility = View.GONE
                    binding!!.noshop.isChecked = true
                } else {
                    binding!!.yesshopvieww.visibility = View.VISIBLE
                    binding!!.yesshop.isChecked = true
                    binding!!.shopname.setText(shopname)
                    binding!!.shopaddress.setText(shopaddress)
                }
                if ((starttime == "!@#$%")) {
                    binding!!.customtimeview.visibility = View.GONE
                    binding!!.anytime.isChecked = true
                } else {
                    binding!!.customtimeview.visibility = View.VISIBLE
                    binding!!.owntime.isChecked = true
                    binding!!.customtime.setText(starttime)
                }
            }
        binding!!.subtype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                item = parent.getItemAtPosition(position)
                if ((item.toString() == "Mechanic")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Mechanic"
                } else if ((item.toString() == "Plumber")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Plumber"
                } else if ((item.toString() == "Electrician")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Electrician"
                } else if ((item.toString() == "Electronic Technician")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Electronic Technician"
                } else if ((item.toString() == "Painter")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Painter"
                } else if ((item.toString() == "Carpenter")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Carpenter"
                } else if ((item.toString() == "Event Management")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Event Management"
                } else if ((item.toString() == "Photographer")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Photographer"
                } else if ((item.toString() == "Home Shifting")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Home Shifting"
                } else if ((item.toString() == "Other")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Other"
                } else if ((item.toString() == "")) {
                    binding!!.typetext.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding!!.yescourse.setOnClickListener {
            binding!!.yescourcevieww.visibility = View.VISIBLE
        }
        binding!!.nocourse.setOnClickListener { binding!!.yescourcevieww.visibility = View.GONE }
        binding!!.anytime.setOnClickListener { binding!!.customtimeview.visibility = View.GONE }
        binding!!.owntime.setOnClickListener { binding!!.customtimeview.visibility = View.VISIBLE }
        binding!!.yesshop.setOnClickListener { binding!!.yesshopvieww.visibility = View.VISIBLE }
        binding!!.noshop.setOnClickListener { binding!!.yesshopvieww.visibility = View.GONE }
        val timestamp = System.currentTimeMillis() / 1000
        val currentime = timestamp.toString()
        binding!!.uploadimage.setOnClickListener {
            ImagePicker.with(this@EditIdActivity)
                .crop() //Crop image(Optional), Check Customization for more option
                .compress(1024) //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                ) //Final image resolution will be less than 1080 x 1080(Optional)
                .start(11)
        }
        binding!!.back.setOnClickListener { finish() }
        binding!!.submitcmid.setOnClickListener {
            dialog!!.show()
            val id1 = binding!!.yescourceradiogroupview.checkedRadioButtonId
            val radioButton1 = findViewById<RadioButton>(id1)
            val id2 = binding!!.yesshopradiogroupview.checkedRadioButtonId
            val radioButton2 = findViewById<RadioButton>(id2)
            val id3 = binding!!.servitimergroupview.checkedRadioButtonId
            val radioButton3 = findViewById<RadioButton>(id3)
            if ((radioButton1.text.toString() == "Yes")) {
                if (!binding!!.coursename.text.toString().isEmpty()) {
                    val cname = binding!!.coursename.text.toString()
                    val cdetail = binding!!.coursedetails.text.toString()
                    if ((radioButton2.text.toString() == "Yes")) {
                        if (!binding!!.shopname.text.toString()
                                .isEmpty() && !binding!!.shopaddress.text.toString().isEmpty()
                        ) {
                            val sname = binding!!.shopname.text.toString()
                            val saddress = binding!!.shopaddress.text.toString()
                            if ((radioButton3.text.toString() == "Customize time")) {
                                if (!binding!!.customtime.text.toString().isEmpty()) {
                                    val sstime = binding!!.customtime.text.toString()
                                    uploaddata(cname, cdetail, sname, saddress, sstime)
                                } else {
                                    if (binding!!.customtime.text.toString().isEmpty()) {
                                        binding!!.customtime.error = "Please select start time"
                                        dialog!!.dismiss()
                                        Toast.makeText(
                                            this@EditIdActivity,
                                            "Please select start time",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            } else {
                                val sstime = "!@#$%"
                                uploaddata(cname, cdetail, sname, saddress, sstime)
                            }
                        } else {
                            if (binding!!.shopname.text.toString().isEmpty()) {
                                binding!!.shopname.error = "Please enter shop name"
                                dialog!!.dismiss()
                                Toast.makeText(
                                    this@EditIdActivity,
                                    "Please enter shop name",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            if (binding!!.shopaddress.text.toString().isEmpty()) {
                                binding!!.shopaddress.error = "Please enter shop address"
                                dialog!!.dismiss()
                                Toast.makeText(
                                    this@EditIdActivity,
                                    "Please enter shop address",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        val sname = "!@#$%"
                        val saddress = "!@#$%"
                        if ((radioButton3.text.toString() == "Customize time")) {
                            if (!binding!!.customtime.text.toString().isEmpty()) {
                                val sstime = binding!!.customtime.text.toString()
                                uploaddata(cname, cdetail, sname, saddress, sstime)
                            } else {
                                if (binding!!.customtime.text.toString().isEmpty()) {
                                    binding!!.customtime.error = "Please select start time"
                                    dialog!!.dismiss()
                                    Toast.makeText(
                                        this@EditIdActivity,
                                        "Please select start time",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            val sstime = "!@#$%"
                            uploaddata(cname, cdetail, sname, saddress, sstime)
                        }
                    }
                } else {
                    binding!!.coursename.error = "Please enter course name"
                    dialog!!.dismiss()
                    Toast.makeText(
                        this@EditIdActivity,
                        "Please enter course name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                val cname = "!@#$%"
                val cdetail = "!@#$%"
                if ((radioButton2.text.toString() == "Yes")) {
                    if (!binding!!.shopname.text.toString()
                            .isEmpty() && !binding!!.shopaddress.text.toString().isEmpty()
                    ) {
                        val sname = binding!!.shopname.text.toString()
                        val saddress = binding!!.shopaddress.text.toString()
                        if ((radioButton3.text.toString() == "Customize time")) {
                            if (!binding!!.customtime.text.toString().isEmpty()) {
                                val sstime = binding!!.customtime.text.toString()
                                uploaddata(cname, cdetail, sname, saddress, sstime)
                            } else {
                                if (binding!!.customtime.text.toString().isEmpty()) {
                                    binding!!.customtime.error = "Please select start time"
                                    dialog!!.dismiss()
                                    Toast.makeText(
                                        this@EditIdActivity,
                                        "Please select start time",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            val sstime = "!@#$%"
                            uploaddata(cname, cdetail, sname, saddress, sstime)
                        }
                    } else {
                        if (binding!!.shopname.text.toString().isEmpty()) {
                            binding!!.shopname.error = "Please enter shop name"
                            dialog!!.dismiss()
                            Toast.makeText(
                                this@EditIdActivity,
                                "Please enter shop name",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        if (binding!!.shopaddress.text.toString().isEmpty()) {
                            binding!!.shopaddress.error = "Please enter shop address"
                            dialog!!.dismiss()
                            Toast.makeText(
                                this@EditIdActivity,
                                "Please enter shop address",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    val sname = "!@#$%"
                    val saddress = "!@#$%"
                    if ((radioButton3.text.toString() == "Customize time")) {
                        if (!binding!!.customtime.text.toString().isEmpty()) {
                            val sstime = binding!!.customtime.text.toString()
                            uploaddata(cname, cdetail, sname, saddress, sstime)
                        } else {
                            if (binding!!.customtime.text.toString().isEmpty()) {
                                binding!!.customtime.error = "Please select start time"
                                dialog!!.dismiss()
                                Toast.makeText(
                                    this@EditIdActivity,
                                    "Please select start time",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        val sstime = "!@#$%"
                        uploaddata(cname, cdetail, sname, saddress, sstime)
                    }
                }
            }
        }
    }

    private fun uploaddata(
        cnamez: String,
        cdetailz: String,
        snamez: String,
        saddressz: String,
        sstimez: String
    ) {
        val subtype = binding!!.typetext.text.toString()
        val exptime = binding!!.exptime.text.toString()
        val more = binding!!.moredetails.text.toString()
        val uname = binding!!.username.text.toString()
        val ucontact = binding!!.contactno.text.toString()
        val uwhatsapp = binding!!.whatsappno.text.toString()
        val uemail = binding!!.cmmail.text.toString()
        if (!more.isEmpty() && !exptime.isEmpty() && !uname.isEmpty() && !ucontact.isEmpty()) {
            val tsLong = System.currentTimeMillis() / 1000
            val ts = tsLong.toString()
            val ImageFolder = FirebaseStorage.getInstance().reference.child("Nanded")
                .child((FirebaseAuth.getInstance().uid)!!).child("Id").child(ts)
            val queryCollectionRef = FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id")
            val ownCollectionRef = FirebaseFirestore.getInstance().collection("OwnData")
                .document((FirebaseAuth.getInstance().uid)!!).collection("Id")
            if (image11 != null) {
                ImageFolder.putFile(image11!!)
                    .addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot?> {
                        override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot?) {
                            ImageFolder.downloadUrl.addOnSuccessListener(object :
                                OnSuccessListener<Uri> {
                                override fun onSuccess(uri: Uri) {
                                    val image11 = uri.toString()
                                    val date = Date()
                                    val cmidClass = IdClass(
                                        subtype,
                                        cnamez,
                                        cdetailz,
                                        exptime,
                                        snamez,
                                        saddressz,
                                        sstimez,
                                        more,
                                        uname,
                                        ucontact,
                                        uwhatsapp,
                                        uemail,
                                        image11,
                                        id,
                                        FirebaseAuth.getInstance().uid,
                                        date.time
                                    )
                                    queryCollectionRef.document((id)!!).set(cmidClass)
                                        .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                            override fun onSuccess(unused: Void?) {
                                                val ownQueryClass = OwnIdClass(id, Date().time)
                                                ownCollectionRef.document((id)!!).set(ownQueryClass)
                                                    .addOnSuccessListener(
                                                        OnSuccessListener {
                                                            finish()
                                                            dialog!!.dismiss()
                                                            Toast.makeText(
                                                                this@EditIdActivity,
                                                                "ID Uploaded Successfully",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        })
                                            }
                                        })
                                }
                            })
                        }
                    }).addOnFailureListener(object : OnFailureListener {
                        override fun onFailure(e: Exception) {
                            Toast.makeText(this@EditIdActivity, "fail", Toast.LENGTH_SHORT).show()
                            dialog!!.dismiss()
                        }
                    })
            } else if (image11 == null) {
                val date = Date()
                val cmidClass = IdClass(
                    subtype,
                    cnamez,
                    cdetailz,
                    exptime,
                    snamez,
                    saddressz,
                    sstimez,
                    more,
                    uname,
                    ucontact,
                    uwhatsapp,
                    uemail,
                    image,
                    id,
                    FirebaseAuth.getInstance().uid,
                    date.time
                )
                queryCollectionRef.document((id)!!).set(cmidClass)
                    .addOnSuccessListener(object : OnSuccessListener<Void?> {
                        override fun onSuccess(unused: Void?) {
                            val ownQueryClass = OwnIdClass(id, Date().time)
                            ownCollectionRef.document((id)!!).set(ownQueryClass)
                                .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                    override fun onSuccess(unused: Void?) {
                                        finish()
                                        dialog!!.dismiss()
                                        Toast.makeText(
                                            this@EditIdActivity,
                                            "ID Uploaded Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }
                    })
            } else {
                dialog!!.dismiss()
                Toast.makeText(this@EditIdActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (more.isEmpty()) {
                binding!!.moredetails.error = "Please enter more about work"
                dialog!!.dismiss()
                Toast.makeText(this, "Please enter more about work", Toast.LENGTH_SHORT).show()
            }
            if (exptime.isEmpty()) {
                binding!!.exptime.error = "Please enter experience period"
                dialog!!.dismiss()
                Toast.makeText(this, "Please enter experience period", Toast.LENGTH_SHORT).show()
            }
            if (uname.isEmpty()) {
                binding!!.username.error = "Please enter your name"
                dialog!!.dismiss()
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
            if (ucontact.isEmpty()) {
                binding!!.contactno.error = "Please enter contact number"
                dialog!!.dismiss()
                Toast.makeText(this, "Please enter contact number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11) {
            binding!!.imagee.visibility = View.VISIBLE
            binding!!.imagee.setImageURI(data!!.data)
            image11 = data.data
        }
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {}
    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}