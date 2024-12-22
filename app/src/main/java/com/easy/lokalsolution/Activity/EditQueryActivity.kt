package com.easy.lokalsolution.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.Class.OwnQueryClass
import com.easy.lokalsolution.Class.QueryClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.ActivityEditQueryBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.util.Date

class EditQueryActivity() : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var binding: ActivityEditQueryBinding? = null
    var dialog: ProgressDialog? = null
    var image11: Uri? = null
    lateinit var querytype: Array<String>
    var id: String? = null
    var image: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditQueryBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.QueryType1,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.querytype.adapter = adapter
        binding!!.querytype.onItemSelectedListener = this@EditQueryActivity
        id = intent.getStringExtra("id")
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Please wait, Editing query .....")
        dialog!!.setCancelable(false)
        val tsLong: Long = System.currentTimeMillis() / 1000
        val ts: String = tsLong.toString()
        FirebaseFirestore.getInstance().collection("Nanded").document("NandedCity")
            .collection("Query").document((id)!!).get()
            .addOnSuccessListener { documentSnapshot ->
                val data: QueryClass? = documentSnapshot.toObject(QueryClass::class.java)
                val type: String? = data?.type
                val subtype: String? = data?.subtype
                val money: String? = data?.money
                val address: String? = data?.address
                val uname: String? = data?.uname
                val disc: String? = data?.disc
                val note: String? = data?.note
                val contact: String? = data?.contact
                val whatsapp: String? = data?.whatsapp
                val contactime: String? = data?.contactime
                val image: String? = data?.image

                binding!!.typetext.setText(type)
                binding!!.subtype.setText(subtype)
                binding!!.moneyedittext.setText(money)
                binding!!.addressedittext.setText(address)
                binding!!.note.setText(note)
                binding!!.uname.setText(uname)
                binding!!.disc.setText(disc)
                binding!!.querycontact.setText(contact)
                binding!!.querywhatapp.setText(whatsapp)
                binding!!.contactime.setText(contactime)
                if (image != "No") {
                    binding!!.queryimage.visibility = View.VISIBLE
                    Picasso.get().load(image).placeholder(R.drawable.placehald)
                        .into(binding!!.queryimage)
                } else {
                    binding!!.queryimage.visibility = View.GONE
                }
            }
        binding!!.querytype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                val data: Any = adapterView.getItemAtPosition(i)
                if ((data.toString() == "किरायाने देणे (Rent)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "किरायाने देणे (Rent)"
                    binding!!.whatyouwanttext.text = "तुम्हाला काय किरायाने द्यायचे आहे ? *"
                    binding!!.subtype.hint = "फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर"
                    binding!!.moneytext.text = "महिनेवारी भाडे किती असेल ?"
                    binding!!.moneyedittext.hint = "महिनेवारी भाडे टाका"
                } else if ((data.toString() == "किरायाने पाहिजे (Rent)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "किरायाने पाहिजे (Rent)"
                    binding!!.whatyouwanttext.setText("तुम्हाला काय किरायाने पाहिजे आहे ? *")
                    binding!!.subtype.hint = "फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर"
                    binding!!.moneytext.text = "किरायाने घेण्याची किंमत किती असायला पाहिजे ?"
                    binding!!.moneyedittext.hint = "किरायाने घेण्याची किंमत टाका"
                } else if ((data.toString() == "विकत पाहिजे (Buy)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "विकत पाहिजे (Buy)"
                    binding!!.whatyouwanttext.setText("तुम्हाला काय विकत पाहिजे आहे ? *")
                    binding!!.subtype.hint = "फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर"
                    binding!!.moneytext.text = "विकत घेण्याची किंमत किती असायला पाहिजे ?"
                    binding!!.moneyedittext.hint = "विकत घेण्याची किंमत टाका"
                } else if ((data.toString() == "विक्री आहे (Sell)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "विक्री आहे (Sell)"
                    binding!!.whatyouwanttext.setText("तुमच्याकडे काय विक्री आहे ? *")
                    binding!!.subtype.hint = "फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर"
                    binding!!.moneytext.text = "विक्रीची किंमत किती आहे ?"
                    binding!!.moneyedittext.setHint("विक्रीची किंमत टाका")
                } else if ((data.toString() == "नोकरी आहे (Job)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "नोकरी आहे (Job)"
                    binding!!.whatyouwanttext.setText("तुमच्याकडे कोणती नोकरी उपलब्ध आहे ? *")
                    binding!!.subtype.hint = "तुमच्याकडे कोणती नोकरी उपलब्ध आहे"
                    binding!!.moneytext.text = "नोकरीसाठी महिनेवारी पगार किती देत आहात ?"
                    binding!!.moneyedittext.setHint("महिनेवारी पगार टाका")
                } else if ((data.toString() == "नोकरी पाहिजे (Job)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "नोकरी पाहिजे (Job)"
                    binding!!.whatyouwanttext.text = "तुम्हाला कोणती नोकरी पाहिजे ? *"
                    binding!!.subtype.hint = "तुम्हाला कोणती नोकरी पाहिजे"
                    binding!!.moneytext.text = "नोकरीसाठी महिनेवारी पगार किती असायला पाहिजे ?"
                    binding!!.moneyedittext.hint = "पगार टाका"
                } else if ((data.toString() == "कामासाठी व्यक्ती पाहिजे (Need)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "कामासाठी व्यक्ती पाहिजे (Need)"
                    binding!!.whatyouwanttext.setText("तुम्हाला कोणत्या कामासाठी व्यक्ती पाहिजे ? *")
                    binding!!.subtype.hint = "तुम्हाला कोणत्या कामासाठी व्यक्ती पाहिजे"
                    binding!!.moneytext.text = "कामासाठी महिनेवारी पगार किती देत आहात ?"
                    binding!!.moneyedittext.hint = "पगार टाका"
                } else if ((data.toString() == "वरील पैकी वेगळे (Other)")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "वरील पैकी वेगळे (Other)"
                    binding!!.whatyouwanttext.text = "तुम्हाला दुसरे काय करायचे आहे ? *"
                    binding!!.subtype.hint = "तुम्हाला दुसरे काय करायचे आहे"
                    binding!!.moneytext.text = "पैशाविषयी माहिती लिहा"
                    binding!!.moneyedittext.hint = "पैशाविषयी माहिती लिहा"
                } else if ((data.toString() == "")) {
                    binding!!.typetext.visibility = View.VISIBLE
                }
            }

            public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        binding!!.uploadimage.setOnClickListener {
            ImagePicker.with(this@EditQueryActivity)
                .crop() //Crop image(Optional), Check Customization for more option
                .compress(1024) //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                ) //Final image resolution will be less than 1080 x 1080(Optional)
                .start(11)
        }
        binding!!.back.setOnClickListener { finish() }
        binding!!.submitqerybtn.setOnClickListener {
            dialog!!.show()
            val disc: String = binding!!.disc.text.toString()
            val contact: String = binding!!.querycontact.text.toString()
            val whatsapp: String = binding!!.querywhatapp.text.toString()
            val cotactime: String = binding!!.contactime.text.toString()
            val subtype: String = binding!!.subtype.text.toString()
            val money: String = binding!!.moneyedittext.text.toString()
            val address: String = binding!!.addressedittext.text.toString()
            val note: String = binding!!.note.text.toString()
            val uname: String = binding!!.uname.text.toString()
            val selectedtype: String = binding!!.typetext.text.toString()
            if (subtype.isNotEmpty() && contact.isNotEmpty()) {
                val userid: String? = FirebaseAuth.getInstance().uid
                val ImageFolder: StorageReference =
                    FirebaseStorage.getInstance().reference.child("Nanded")
                        .child((FirebaseAuth.getInstance().uid)!!).child("Query").child(ts)
                val queryCollectionRef: CollectionReference =
                    FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                val ownCollectionRef: CollectionReference =
                    FirebaseFirestore.getInstance().collection("OwnData")
                        .document((userid)!!).collection("Query")
                if (image11 != null) {
                    ImageFolder.putFile(image11!!)
                        .addOnSuccessListener {
                            ImageFolder.downloadUrl
                                .addOnSuccessListener { uri ->
                                    val uri1: String = uri.toString()
                                    val cmidClass: QueryClass = QueryClass(
                                        selectedtype,
                                        subtype,
                                        money,
                                        address,
                                        disc,
                                        note,
                                        uname,
                                        contact,
                                        whatsapp,
                                        cotactime,
                                        uri1,
                                        userid,
                                        Date().time
                                    )
                                    queryCollectionRef.document((id)!!).set(cmidClass)
                                        .addOnSuccessListener {
                                            val ownQueryClass: OwnQueryClass =
                                                OwnQueryClass(id, Date().time)
                                            ownCollectionRef.document((id)!!)
                                                .set(ownQueryClass)
                                                .addOnSuccessListener {
                                                    dialog!!.dismiss()
                                                    finish()
                                                    Toast.makeText(
                                                        this@EditQueryActivity,
                                                        "Query Uploaded Successfully",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                        }
                                }
                        }.addOnFailureListener {
                            Toast.makeText(
                                this@EditQueryActivity,
                                "fail",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialog!!.dismiss()
                        }
                } else if (image11 == null) {
                    val cmidClass: QueryClass = QueryClass(
                        selectedtype,
                        subtype,
                        money,
                        address,
                        disc,
                        note,
                        uname,
                        contact,
                        whatsapp,
                        cotactime,
                        image,
                        userid,
                        Date().getTime()
                    )
                    queryCollectionRef.document((id)!!).set(cmidClass)
                        .addOnSuccessListener {
                            val ownQueryClass: OwnQueryClass =
                                OwnQueryClass(id, Date().time)
                            ownCollectionRef.document((id)!!).set(ownQueryClass)
                                .addOnSuccessListener {
                                    dialog!!.dismiss()
                                    finish()
                                    Toast.makeText(
                                        this@EditQueryActivity,
                                        "Query Uploaded Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                } else {
                    dialog!!.dismiss()
                    Toast.makeText(
                        this@EditQueryActivity,
                        "Something Wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                if (subtype.isEmpty()) {
                    binding!!.subtype.error = "तुम्हाला दुसरे काय करायचे आहे"
                    dialog!!.dismiss()
                    Toast.makeText(
                        this@EditQueryActivity,
                        "तुम्हाला दुसरे काय करायचे आहे",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (contact.isEmpty()) {
                    binding!!.querycontact.error = "संपर्क टाका"
                    dialog!!.dismiss()
                    Toast.makeText(this@EditQueryActivity, "संपर्क टाका", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11) {
            binding!!.queryimage.visibility = View.VISIBLE
            binding!!.queryimage.setImageURI(data!!.data)
            image11 = data.data
        }
    }

    public override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {}
    public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}