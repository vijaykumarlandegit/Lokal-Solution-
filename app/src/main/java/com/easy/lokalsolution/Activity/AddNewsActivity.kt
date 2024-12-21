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
 import com.easy.lokalsolution.databinding.ActivityAddNewsBinding
 import com.github.dhaval2404.imagepicker.ImagePicker
 import com.google.android.gms.tasks.OnFailureListener
 import com.google.android.gms.tasks.OnSuccessListener
 import com.google.firebase.auth.FirebaseAuth
 import com.google.firebase.firestore.CollectionReference
 import com.google.firebase.firestore.FirebaseFirestore
 import com.google.firebase.storage.FirebaseStorage
 import com.google.firebase.storage.StorageReference
 import com.google.firebase.storage.UploadTask
 import java.util.Date

class AddNewsActivity() : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var binding: ActivityAddNewsBinding? = null
    lateinit var rentaltype: Array<String>
    lateinit var areatype: Array<String>
    var PICK_IMG: Int = 123
    var `in`: Int = 0
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var image11: Uri? = null
    var hashMap: HashMap<String, String>? = null
    var dialog: ProgressDialog? = null
    var dialog1: ProgressDialog? = null
    private val upload_count: Int = 0
    var newlist: ArrayList<Uri> = ArrayList()
    var newuri: Uri? = null
    var mainum: Int = 0
    var newStrings: ArrayList<*> = ArrayList<Any>()
    var subtype: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Please wait, Uploading post .....")
        dialog!!.setCancelable(false)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.NewsType,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.newtype.setAdapter(adapter)
        binding!!.newtype.setOnItemSelectedListener(this@AddNewsActivity)
        binding!!.uploadimage.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                ImagePicker.with(this@AddNewsActivity)
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
        binding!!.uploadnewbtn.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                dialog!!.show()
                val title: String = binding!!.newtitle.getText().toString()
                val disc: String = binding!!.newdis.getText().toString()
                if (!title.isEmpty() && !disc.isEmpty()) {
                    dialog!!.show()
                    val userid: String? = FirebaseAuth.getInstance().getUid()
                    val tsLong: Long = System.currentTimeMillis() / 1000
                    val ts: String = tsLong.toString()
                    val ImageFolder: StorageReference =
                        FirebaseStorage.getInstance().getReference().child("Nanded")
                            .child((FirebaseAuth.getInstance().getUid())!!).child("News").child(ts)
                    val newsCollectionRef: CollectionReference =
                        FirebaseFirestore.getInstance().collection("Nanded")
                            .document("NandedCity").collection("News")
                    val ownCollectionRef: CollectionReference =
                        FirebaseFirestore.getInstance().collection("OwnData")
                            .document((userid)!!).collection("News")
                    val id: String = newsCollectionRef.document().getId()
                    if (image11 != null) {
                        ImageFolder.putFile(image11!!)
                            .addOnSuccessListener(object :
                                OnSuccessListener<UploadTask.TaskSnapshot?> {
                                public override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot?) {
                                    ImageFolder.getDownloadUrl()
                                        .addOnSuccessListener(object : OnSuccessListener<Uri> {
                                            public override fun onSuccess(uri: Uri) {
                                                val image11: String = uri.toString()
                                                val type: String =
                                                    binding!!.newtype.getSelectedItem().toString()
                                                val date: Date = Date()
                                                val cmidClass: NewsClass = NewsClass(
                                                    type,
                                                    title,
                                                    disc,
                                                    userid,
                                                    id,
                                                    image11,
                                                    date.getTime()
                                                )
                                                val cmidClass1: OwnNewsClass =
                                                    OwnNewsClass(id, date.getTime())
                                                newsCollectionRef.document(id).set(cmidClass)
                                                    .addOnSuccessListener(object :
                                                        OnSuccessListener<Void?> {
                                                        public override fun onSuccess(unused: Void?) {
                                                            ownCollectionRef.document(id)
                                                                .set(cmidClass1)
                                                                .addOnSuccessListener(object :
                                                                    OnSuccessListener<Void?> {
                                                                    public override fun onSuccess(
                                                                        unused: Void?
                                                                    ) {
                                                                        finish()
                                                                        dialog!!.dismiss()
                                                                        Toast.makeText(
                                                                            this@AddNewsActivity,
                                                                            "News Uploaded Successfully",
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
                                    Toast.makeText(this@AddNewsActivity, "fail", Toast.LENGTH_SHORT)
                                        .show()
                                    dialog!!.dismiss()
                                }
                            })
                    } else if (image11 == null) {
                        val type: String = binding!!.newtype.getSelectedItem().toString()
                        val date: Date = Date()
                        val cmidClass: NewsClass =
                            NewsClass(type, title, disc, userid, id, "No", date.getTime())
                        val cmidClass1: OwnNewsClass = OwnNewsClass(id, date.getTime())
                        newsCollectionRef.document(id).set(cmidClass)
                            .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                public override fun onSuccess(unused: Void?) {
                                    ownCollectionRef.document(id).set(cmidClass1)
                                        .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                            public override fun onSuccess(unused: Void?) {
                                                dialog!!.dismiss()
                                                finish()
                                                Toast.makeText(
                                                    this@AddNewsActivity,
                                                    "Data Uploaded Successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        })
                                }
                            })
                    } else {
                        dialog!!.dismiss()
                        Toast.makeText(this@AddNewsActivity, "Something Wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    if (title.isEmpty()) {
                        dialog!!.dismiss()
                        binding!!.newtitle.setError("Please enter news title")
                        Toast.makeText(
                            this@AddNewsActivity,
                            "Please enter news title",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (disc.isEmpty()) {
                        dialog!!.dismiss()
                        binding!!.newdis.setError("Please enter new description")
                        Toast.makeText(
                            this@AddNewsActivity,
                            "Please enter new description",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

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

    companion object {
        private val PICK_IMAGE: Int = 1
    }
}