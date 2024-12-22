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
import com.easy.lokalsolution.Class.NewsClass
import com.easy.lokalsolution.Class.OwnNewsClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.ActivityEditNewsBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.util.Date

class EditNewsActivity() : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var binding: ActivityEditNewsBinding? = null
    lateinit var rentaltype: Array<String>
    lateinit var areatype: Array<String>
    var image11: Uri? = null
    var PICK_IMG: Int = 123
    var `in`: Int = 0
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var hashMap: HashMap<String, String>? = null
    var dialog: ProgressDialog? = null
    var dialog1: ProgressDialog? = null
    private val upload_count: Int = 0
    var newlist: ArrayList<Uri> = ArrayList()
    var newuri: Uri? = null
    var mainum: Int = 0
    var newStrings: ArrayList<*> = ArrayList<Any>()
    var id: String? = null
    var image: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNewsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Please wait, Editing post .....")
        dialog!!.setCancelable(false)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.NewsType1,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.newtype.adapter = adapter
        binding!!.newtype.onItemSelectedListener = this@EditNewsActivity
        id = intent.getStringExtra("id")
        FirebaseFirestore.getInstance().collection("Nanded").document("NandedCity")
            .collection("News").document((id)!!).get()
            .addOnSuccessListener { documentSnapshot ->
                val data: NewsClass? = documentSnapshot.toObject(NewsClass::class.java)
                val type: String? = data?.type
                val title: String? = data?.title
                val disc: String? = data?.disc
                val image: String? = data?.image

                binding!!.typetext.text = type
                binding!!.newtitle.setText(title)
                binding!!.newdis.setText(disc)
                if (image != "No") {
                    binding!!.imagee.visibility = View.VISIBLE
                    Picasso.get().load(image).placeholder(R.drawable.images)
                        .into(binding!!.imagee)
                } else {
                    binding!!.imagee.visibility = View.GONE
                }
            }
        binding!!.newtype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val item: Any = parent.getItemAtPosition(position)
                if ((item.toString() == "News")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "News"
                } else if ((item.toString() == "Advertisement")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Advertisement"
                } else if ((item.toString() == "Story")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Story"
                } else if ((item.toString() == "Poetry")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Poetry"
                } else if ((item.toString() == "Announcement")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Announcement"
                } else if ((item.toString() == "Puzzle")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Puzzle"
                } else if ((item.toString() == "GK Question")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "GK Question"
                } else if ((item.toString() == "Other")) {
                    binding!!.typetext.visibility = View.GONE
                    binding!!.typetext.text = "Other"
                } else if ((item.toString() == "")) {
                    binding!!.typetext.visibility = View.VISIBLE
                }
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding!!.back.setOnClickListener { finish() }
        binding!!.uploadimage.setOnClickListener {
            ImagePicker.with(this@EditNewsActivity)
                .crop() //Crop image(Optional), Check Customization for more option
                .compress(1024) //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                ) //Final image resolution will be less than 1080 x 1080(Optional)
                .start(11)
        }
        binding!!.uploadnewbtn.setOnClickListener {
            dialog!!.show()
            val title: String = binding!!.newtitle.text.toString()
            val disc: String = binding!!.newdis.text.toString()
            if (title.isNotEmpty() && disc.isNotEmpty()) {
                dialog!!.show()
                val userid: String? = FirebaseAuth.getInstance().uid
                val tsLong: Long = System.currentTimeMillis() / 1000
                val ts: String = tsLong.toString()
                val ImageFolder: StorageReference =
                    FirebaseStorage.getInstance().reference.child("Nanded")
                        .child((FirebaseAuth.getInstance().uid)!!).child("News").child(ts)
                val newsCollectionRef: CollectionReference =
                    FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                val ownCollectionRef: CollectionReference =
                    FirebaseFirestore.getInstance().collection("OwnData")
                        .document((userid)!!).collection("News")
                if (image11 != null) {
                    ImageFolder.putFile(image11!!)
                        .addOnSuccessListener {
                            ImageFolder.downloadUrl
                                .addOnSuccessListener { uri ->
                                    val image11: String = uri.toString()
                                    val type: String = binding!!.typetext.text.toString()
                                    val date: Date = Date()
                                    val cmidClass: NewsClass = NewsClass(
                                        type,
                                        title,
                                        disc,
                                        userid,
                                        id,
                                        image11,
                                        date.time
                                    )
                                    val cmidClass1: OwnNewsClass =
                                        OwnNewsClass(id, date.time)
                                    newsCollectionRef.document((id)!!).set(cmidClass)
                                        .addOnSuccessListener {
                                            ownCollectionRef.document((id)!!)
                                                .set(cmidClass1)
                                                .addOnSuccessListener {
                                                    finish()
                                                    dialog!!.dismiss()
                                                    Toast.makeText(
                                                        this@EditNewsActivity,
                                                        "News Uploaded Successfully",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                        }
                                }
                        }.addOnFailureListener {
                            Toast.makeText(
                                this@EditNewsActivity,
                                "fail",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialog!!.dismiss()
                        }
                } else if (image11 == null) {
                    val type: String = binding!!.typetext.text.toString()
                    val date: Date = Date()
                    val cmidClass: NewsClass =
                        NewsClass(type, title, disc, userid, id, image, date.time)
                    val cmidClass1: OwnNewsClass = OwnNewsClass(id, date.time)
                    newsCollectionRef.document((id)!!).set(cmidClass)
                        .addOnSuccessListener {
                            ownCollectionRef.document((id)!!).set(cmidClass1)
                                .addOnSuccessListener {
                                    dialog!!.dismiss()
                                    finish()
                                    Toast.makeText(
                                        this@EditNewsActivity,
                                        "Data Uploaded Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                } else {
                    dialog!!.dismiss()
                    Toast.makeText(this@EditNewsActivity, "Something Wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                if (title.isEmpty()) {
                    dialog!!.dismiss()
                    binding!!.newtitle.error = "Please enter news title"
                    Toast.makeText(
                        this@EditNewsActivity,
                        "Please enter news title",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (disc.isEmpty()) {
                    dialog!!.dismiss()
                    binding!!.newdis.error = "Please enter new description"
                    Toast.makeText(
                        this@EditNewsActivity,
                        "Please enter new description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11) {
            binding!!.imagee.setVisibility(View.VISIBLE)
            binding!!.imagee.setImageURI(data!!.getData())
            image11 = data.getData()
        }
    }

    public override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {}
    public override fun onNothingSelected(adapterView: AdapterView<*>?) {}


}