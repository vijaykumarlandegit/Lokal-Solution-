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
import com.easy.lokalsolution.databinding.ActivityAddIdBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.Date

class AddIdActivity() : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var binding: ActivityAddIdBinding? = null
    var image11: Uri? = null
    var image12: Uri? = null
    lateinit var gender: Array<String>
    var mechtype: String? = null

    var dialog: ProgressDialog? = null




    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("spinnerPosition", binding!!.subtype.selectedItemPosition)
        outState.putString("moreDetails", binding!!.moredetails.text.toString())
        outState.putString("courseName", binding!!.coursename.text.toString())
        outState.putString("courseDetails", binding!!.coursedetails.text.toString())
        outState.putString("experience", binding!!.exptime.text.toString())
        outState.putString("shopName", binding!!.shopname.text.toString())
        outState.putString("shopAddress", binding!!.shopaddress.text.toString())
        outState.putString("customTime", binding!!.customtime.text.toString())
        outState.putInt("courseRadioGroupCheckedId", binding!!.yescourceradiogroupview.checkedRadioButtonId)
        outState.putInt("shopRadioGroupCheckedId", binding!!.yesshopradiogroupview.checkedRadioButtonId)
        outState.putInt("serviceTimeRadioGroupCheckedId", binding!!.servitimergroupview.checkedRadioButtonId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIdBinding.inflate(layoutInflater)
        setContentView(binding!!.root)





        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            listOf("Select Query", "Option 1", "Option 2", "Option 3"))
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.subtype.adapter = spinnerAdapter
        savedInstanceState?.let {
            binding!!.subtype.setSelection(it.getInt("spinnerPosition", 0))
            binding!!.moredetails.setText(it.getString("moreDetails"))
            binding!!.yescourceradiogroupview.check(it.getInt("courseRadioGroupCheckedId"))
            binding!!.coursename.setText(it.getString("courseName"))
            binding!!.coursedetails.setText(it.getString("courseDetails"))
            binding!!.exptime.setText(it.getString("experience"))
            binding!!.yesshopradiogroupview.check(it.getInt("shopRadioGroupCheckedId"))
            binding!!.shopname.setText(it.getString("shopName"))
            binding!!.shopaddress.setText(it.getString("shopAddress"))
            binding!!.servitimergroupview.check(it.getInt("serviceTimeRadioGroupCheckedId"))
            binding!!.customtime.setText(it.getString("customTime"))
        }

        val adapter1 = ArrayAdapter.createFromResource(
            this, // Use activity context
            R.array.IdType,
            android.R.layout.simple_spinner_item
        )

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.subtype.adapter = adapter1
        binding!!.subtype.onItemSelectedListener = this@AddIdActivity
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Please wait, Creating your ID .....")
        dialog!!.setCancelable(false)
        binding!!.yescourse.setOnClickListener {
            binding!!.yescourcevieww.visibility = View.VISIBLE
        }
        binding!!.nocourse.setOnClickListener { binding!!.yescourcevieww.visibility = View.GONE }
        binding!!.alltime.setOnClickListener { binding!!.customtimeview.visibility = View.GONE }
        binding!!.owntime.setOnClickListener { binding!!.customtimeview.visibility = View.VISIBLE }
        binding!!.yesshop.setOnClickListener { binding!!.yesshopvieww.visibility = View.VISIBLE }
        binding!!.noshop.setOnClickListener { binding!!.yesshopvieww.visibility = View.GONE }
        binding!!.back.setOnClickListener { finish() }
        val id12 = binding!!.yescourceradiogroupview.checkedRadioButtonId
        val radioButton01 = findViewById<RadioButton>(id12)
        if ((radioButton01.text == "Yes")) {
        }
        if ((radioButton01.text == "No")) {
        }
        val timestamp = System.currentTimeMillis() / 1000
        val currentime = timestamp.toString()
        binding!!.uploadimage.setOnClickListener {
            ImagePicker.with(this@AddIdActivity)
                .crop() //Crop image(Optional), Check Customization for more option
                .compress(1024) //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                ) //Final image resolution will be less than 1080 x 1080(Optional)
                .start(11)
        }
        binding!!.submitcmid.setOnClickListener {
            dialog!!.show()
            val id1 = binding!!.yescourceradiogroupview.checkedRadioButtonId
            val radioButton1 = findViewById<RadioButton>(id1)
            val id2 = binding!!.yesshopradiogroupview.checkedRadioButtonId
            val radioButton2 = findViewById<RadioButton>(id2)
            val id3 = binding!!.servitimergroupview.checkedRadioButtonId
            val radioButton3 = findViewById<RadioButton>(id3)
            if ((radioButton1.text.toString() == "Yes")) {
                if (binding!!.coursename.text.toString().isNotEmpty()) {
                    val cname = binding!!.coursename.text.toString()
                    val cdetail = binding!!.coursedetails.text.toString()
                    if ((radioButton2.text.toString() == "Yes")) {
                        if (binding!!.shopname.text.toString().isNotEmpty() && binding!!.shopaddress.text.toString().isNotEmpty()
                        ) {
                            val sname = binding!!.shopname.text.toString()
                            val saddress = binding!!.shopaddress.text.toString()
                            if ((radioButton3.text.toString() == "Customize time")) {
                                if (binding!!.customtime.text.toString().isNotEmpty()) {
                                    val sstime = binding!!.customtime.text.toString()
                                    uploaddata(cname, cdetail, sname, saddress, sstime)
                                } else {
                                    if (binding!!.customtime.text.toString().isEmpty()) {
                                        binding!!.customtime.error = "Please select start time"
                                        dialog!!.dismiss()
                                        Toast.makeText(
                                            this@AddIdActivity,
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
                                    this@AddIdActivity,
                                    "Please enter shop name",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            if (binding!!.shopaddress.text.toString().isEmpty()) {
                                binding!!.shopaddress.error = "Please enter shop address"
                                dialog!!.dismiss()
                                Toast.makeText(
                                    this@AddIdActivity,
                                    "Please enter shop address",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        val sname = "!@#$%"
                        val saddress = "!@#$%"
                        if ((radioButton3.text.toString() == "Customize time")) {
                            if (binding!!.customtime.text.toString().isNotEmpty()) {
                                val sstime = binding!!.customtime.text.toString()
                                uploaddata(cname, cdetail, sname, saddress, sstime)
                            } else {
                                if (binding!!.customtime.text.toString().isEmpty()) {
                                    binding!!.customtime.error = "Please select start time"
                                    dialog!!.dismiss()
                                    Toast.makeText(
                                        this@AddIdActivity,
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
                        this@AddIdActivity,
                        "Please enter course name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                val cname = "!@#$%"
                val cdetail = "!@#$%"
                if ((radioButton2.text.toString() == "Yes")) {
                    if (binding!!.shopname.text.toString().isNotEmpty() && binding!!.shopaddress.text.toString()
                            .isNotEmpty()
                    ) {
                        val sname = binding!!.shopname.text.toString()
                        val saddress = binding!!.shopaddress.text.toString()
                        if ((radioButton3.text.toString() == "Customize time")) {
                            if (binding!!.customtime.text.toString().isNotEmpty()) {
                                val sstime = binding!!.customtime.text.toString()
                                uploaddata(cname, cdetail, sname, saddress, sstime)
                            } else {
                                if (binding!!.customtime.text.toString().isEmpty()) {
                                    binding!!.customtime.error = "Please select start time"
                                    dialog!!.dismiss()
                                    Toast.makeText(
                                        this@AddIdActivity,
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
                                this@AddIdActivity,
                                "Please enter shop name",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        if (binding!!.shopaddress.text.toString().isEmpty()) {
                            binding!!.shopaddress.error = "Please enter shop address"
                            dialog!!.dismiss()
                            Toast.makeText(
                                this@AddIdActivity,
                                "Please enter shop address",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    val sname = "!@#$%"
                    val saddress = "!@#$%"
                    if ((radioButton3.text.toString() == "Customize time")) {
                        if (binding!!.customtime.text.toString().isNotEmpty()) {
                            val sstime = binding!!.customtime.text.toString()
                            uploaddata(cname, cdetail, sname, saddress, sstime)
                        } else {
                            if (binding!!.customtime.text.toString().isEmpty()) {
                                binding!!.customtime.error = "Please select start time"
                                dialog!!.dismiss()
                                Toast.makeText(
                                    this@AddIdActivity,
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
        val subtype = binding!!.subtype.selectedItem.toString()
        val exptime = binding!!.exptime.text.toString()
        val more = binding!!.moredetails.text.toString()
        val uname = binding!!.username.text.toString()
        val ucontact = binding!!.contactno.text.toString()
        val uwhatsapp = binding!!.whatsappno.text.toString()
        val uemail = binding!!.cmmail.text.toString()
        if (subtype.isNotEmpty() && exptime.isNotEmpty() && uname.isNotEmpty() && ucontact.isNotEmpty()) {
            val tsLong = System.currentTimeMillis() / 1000
            val ts = tsLong.toString()
            val ImageFolder = FirebaseStorage.getInstance().reference.child("Nanded")
                .child((FirebaseAuth.getInstance().uid)!!).child("Id").child(ts)
            val queryCollectionRef = FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id")
            val ownCollectionRef = FirebaseFirestore.getInstance().collection("OwnData")
                .document((FirebaseAuth.getInstance().uid)!!).collection("Id")
            val id = queryCollectionRef.document().id
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
                                    queryCollectionRef.document(id).set(cmidClass)
                                        .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                            override fun onSuccess(unused: Void?) {
                                                val ownQueryClass = OwnIdClass(id, Date().time)
                                                ownCollectionRef.document(id).set(ownQueryClass)
                                                    .addOnSuccessListener(
                                                        OnSuccessListener {
                                                            finish()
                                                            dialog!!.dismiss()
                                                            Toast.makeText(
                                                                this@AddIdActivity,
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
                            Toast.makeText(this@AddIdActivity, "fail", Toast.LENGTH_SHORT).show()
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
                    "No",
                    id,
                    FirebaseAuth.getInstance().uid,
                    date.time
                )
                queryCollectionRef.document(id).set(cmidClass)
                    .addOnSuccessListener(object : OnSuccessListener<Void?> {
                        override fun onSuccess(unused: Void?) {
                            val ownQueryClass = OwnIdClass(id, Date().time)
                            ownCollectionRef.document(id).set(ownQueryClass)
                                .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                    override fun onSuccess(unused: Void?) {
                                        finish()
                                        dialog!!.dismiss()
                                        Toast.makeText(
                                            this@AddIdActivity,
                                            "ID Uploaded Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }
                    })
            } else {
                dialog!!.dismiss()
                Toast.makeText(this@AddIdActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
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

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
        val querytype = adapterView.getItemAtPosition(position).toString()
        val mechtype = adapterView.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}