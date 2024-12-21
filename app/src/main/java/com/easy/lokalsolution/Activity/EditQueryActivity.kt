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
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
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
        binding = ActivityEditQueryBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.QueryType1,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.querytype.setAdapter(adapter)
        binding!!.querytype.setOnItemSelectedListener(this@EditQueryActivity)
        id = getIntent().getStringExtra("id")
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Please wait, Editing query .....")
        dialog!!.setCancelable(false)
        val tsLong: Long = System.currentTimeMillis() / 1000
        val ts: String = tsLong.toString()
        FirebaseFirestore.getInstance().collection("Nanded").document("NandedCity")
            .collection("Query").document((id)!!).get()
            .addOnSuccessListener(object : OnSuccessListener<DocumentSnapshot> {
                public override fun onSuccess(documentSnapshot: DocumentSnapshot) {
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
                    if (!(image == "No")) {
                        binding!!.queryimage.setVisibility(View.VISIBLE)
                        Picasso.get().load(image).placeholder(R.drawable.placehald)
                            .into(binding!!.queryimage)
                    } else {
                        binding!!.queryimage.setVisibility(View.GONE)
                    }
                }
            })
        binding!!.querytype.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                val data: Any = adapterView.getItemAtPosition(i)
                if ((data.toString() == "किरायाने देणे (Rent)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("किरायाने देणे (Rent)")
                    binding!!.whatyouwanttext.setText("तुम्हाला काय किरायाने द्यायचे आहे ? *")
                    binding!!.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर")
                    binding!!.moneytext.setText("महिनेवारी भाडे किती असेल ?")
                    binding!!.moneyedittext.setHint("महिनेवारी भाडे टाका")
                } else if ((data.toString() == "किरायाने पाहिजे (Rent)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("किरायाने पाहिजे (Rent)")
                    binding!!.whatyouwanttext.setText("तुम्हाला काय किरायाने पाहिजे आहे ? *")
                    binding!!.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर")
                    binding!!.moneytext.setText("किरायाने घेण्याची किंमत किती असायला पाहिजे ?")
                    binding!!.moneyedittext.setHint("किरायाने घेण्याची किंमत टाका")
                } else if ((data.toString() == "विकत पाहिजे (Buy)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("विकत पाहिजे (Buy)")
                    binding!!.whatyouwanttext.setText("तुम्हाला काय विकत पाहिजे आहे ? *")
                    binding!!.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर")
                    binding!!.moneytext.setText("विकत घेण्याची किंमत किती असायला पाहिजे ?")
                    binding!!.moneyedittext.setHint("विकत घेण्याची किंमत टाका")
                } else if ((data.toString() == "विक्री आहे (Sell)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("विक्री आहे (Sell)")
                    binding!!.whatyouwanttext.setText("तुमच्याकडे काय विक्री आहे ? *")
                    binding!!.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर")
                    binding!!.moneytext.setText("विक्रीची किंमत किती आहे ?")
                    binding!!.moneyedittext.setHint("विक्रीची किंमत टाका")
                } else if ((data.toString() == "नोकरी आहे (Job)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("नोकरी आहे (Job)")
                    binding!!.whatyouwanttext.setText("तुमच्याकडे कोणती नोकरी उपलब्ध आहे ? *")
                    binding!!.subtype.setHint("तुमच्याकडे कोणती नोकरी उपलब्ध आहे")
                    binding!!.moneytext.setText("नोकरीसाठी महिनेवारी पगार किती देत आहात ?")
                    binding!!.moneyedittext.setHint("महिनेवारी पगार टाका")
                } else if ((data.toString() == "नोकरी पाहिजे (Job)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("नोकरी पाहिजे (Job)")
                    binding!!.whatyouwanttext.setText("तुम्हाला कोणती नोकरी पाहिजे ? *")
                    binding!!.subtype.setHint("तुम्हाला कोणती नोकरी पाहिजे")
                    binding!!.moneytext.setText("नोकरीसाठी महिनेवारी पगार किती असायला पाहिजे ?")
                    binding!!.moneyedittext.setHint("पगार टाका")
                } else if ((data.toString() == "कामासाठी व्यक्ती पाहिजे (Need)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("कामासाठी व्यक्ती पाहिजे (Need)")
                    binding!!.whatyouwanttext.setText("तुम्हाला कोणत्या कामासाठी व्यक्ती पाहिजे ? *")
                    binding!!.subtype.setHint("तुम्हाला कोणत्या कामासाठी व्यक्ती पाहिजे")
                    binding!!.moneytext.setText("कामासाठी महिनेवारी पगार किती देत आहात ?")
                    binding!!.moneyedittext.setHint("पगार टाका")
                } else if ((data.toString() == "वरील पैकी वेगळे (Other)")) {
                    binding!!.typetext.setVisibility(View.GONE)
                    binding!!.typetext.setText("वरील पैकी वेगळे (Other)")
                    binding!!.whatyouwanttext.setText("तुम्हाला दुसरे काय करायचे आहे ? *")
                    binding!!.subtype.setHint("तुम्हाला दुसरे काय करायचे आहे")
                    binding!!.moneytext.setText("पैशाविषयी माहिती लिहा")
                    binding!!.moneyedittext.setHint("पैशाविषयी माहिती लिहा")
                } else if ((data.toString() == "")) {
                    binding!!.typetext.setVisibility(View.VISIBLE)
                }
            }

            public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
        binding!!.uploadimage.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                ImagePicker.with(this@EditQueryActivity)
                    .crop() //Crop image(Optional), Check Customization for more option
                    .compress(1024) //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(
                        1080,
                        1080
                    ) //Final image resolution will be less than 1080 x 1080(Optional)
                    .start(11)
            }
        })
        binding!!.back.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                finish()
            }
        })
        binding!!.submitqerybtn.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                dialog!!.show()
                val disc: String = binding!!.disc.getText().toString()
                val contact: String = binding!!.querycontact.getText().toString()
                val whatsapp: String = binding!!.querywhatapp.getText().toString()
                val cotactime: String = binding!!.contactime.getText().toString()
                val subtype: String = binding!!.subtype.getText().toString()
                val money: String = binding!!.moneyedittext.getText().toString()
                val address: String = binding!!.addressedittext.getText().toString()
                val note: String = binding!!.note.getText().toString()
                val uname: String = binding!!.uname.getText().toString()
                val selectedtype: String = binding!!.typetext.getText().toString()
                if (!subtype.isEmpty() && !contact.isEmpty()) {
                    val userid: String? = FirebaseAuth.getInstance().getUid()
                    val ImageFolder: StorageReference =
                        FirebaseStorage.getInstance().getReference().child("Nanded")
                            .child((FirebaseAuth.getInstance().getUid())!!).child("Query").child(ts)
                    val queryCollectionRef: CollectionReference =
                        FirebaseFirestore.getInstance().collection("Nanded")
                            .document("NandedCity").collection("Query")
                    val ownCollectionRef: CollectionReference =
                        FirebaseFirestore.getInstance().collection("OwnData")
                            .document((userid)!!).collection("Query")
                    if (image11 != null) {
                        ImageFolder.putFile(image11!!)
                            .addOnSuccessListener(object :
                                OnSuccessListener<UploadTask.TaskSnapshot?> {
                                public override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot?) {
                                    ImageFolder.getDownloadUrl()
                                        .addOnSuccessListener(object : OnSuccessListener<Uri> {
                                            public override fun onSuccess(uri: Uri) {
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
                                                    Date().getTime()
                                                )
                                                queryCollectionRef.document((id)!!).set(cmidClass)
                                                    .addOnSuccessListener(object :
                                                        OnSuccessListener<Void?> {
                                                        public override fun onSuccess(unused: Void?) {
                                                            val ownQueryClass: OwnQueryClass =
                                                                OwnQueryClass(id, Date().getTime())
                                                            ownCollectionRef.document((id)!!)
                                                                .set(ownQueryClass)
                                                                .addOnSuccessListener(object :
                                                                    OnSuccessListener<Void?> {
                                                                    public override fun onSuccess(
                                                                        unused: Void?
                                                                    ) {
                                                                        dialog!!.dismiss()
                                                                        finish()
                                                                        Toast.makeText(
                                                                            this@EditQueryActivity,
                                                                            "Query Uploaded Successfully",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                    }
                                                                })
                                                        }
                                                    })
                                            }
                                        })
                                }
                            }).addOnFailureListener(object : OnFailureListener {
                                public override fun onFailure(e: Exception) {
                                    Toast.makeText(
                                        this@EditQueryActivity,
                                        "fail",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    dialog!!.dismiss()
                                }
                            })
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
                            .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                public override fun onSuccess(unused: Void?) {
                                    val ownQueryClass: OwnQueryClass =
                                        OwnQueryClass(id, Date().getTime())
                                    ownCollectionRef.document((id)!!).set(ownQueryClass)
                                        .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                            public override fun onSuccess(unused: Void?) {
                                                dialog!!.dismiss()
                                                finish()
                                                Toast.makeText(
                                                    this@EditQueryActivity,
                                                    "Query Uploaded Successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        })
                                }
                            })
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
                        binding!!.subtype.setError("तुम्हाला दुसरे काय करायचे आहे")
                        dialog!!.dismiss()
                        Toast.makeText(
                            this@EditQueryActivity,
                            "तुम्हाला दुसरे काय करायचे आहे",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (contact.isEmpty()) {
                        binding!!.querycontact.setError("संपर्क टाका")
                        dialog!!.dismiss()
                        Toast.makeText(this@EditQueryActivity, "संपर्क टाका", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11) {
            binding!!.queryimage.setVisibility(View.VISIBLE)
            binding!!.queryimage.setImageURI(data!!.getData())
            image11 = data.getData()
        }
    }

    public override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {}
    public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}