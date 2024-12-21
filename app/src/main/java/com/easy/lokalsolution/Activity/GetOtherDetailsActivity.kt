package com.easy.lokalsolution.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.Class.UserClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.ActivityGetOtherDetailsBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso

class GetOtherDetailsActivity() : AppCompatActivity() {
    var binding: ActivityGetOtherDetailsBinding? = null
    var image11: ImageView? = null
    var selectedImage: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetOtherDetailsBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        val dialog: ProgressDialog = ProgressDialog(this)
        dialog.setMessage("Creating Profile, please wait . . . .")
        dialog.setCancelable(false)
        FirebaseFirestore.getInstance().collection("AllUserG")
            .document((FirebaseAuth.getInstance().getUid())!!)
            .get().addOnCompleteListener(object : OnCompleteListener<DocumentSnapshot> {
                public override fun onComplete(task: Task<DocumentSnapshot>) {
                    val snapshot: DocumentSnapshot = task.getResult()
                    val data: UserClass? = snapshot.toObject(UserClass::class.java)
                    val image: String? = data?.image
                    Picasso.get().load(image).placeholder(R.drawable.userkk).into(binding!!.userpic)
                }
            })
        binding!!.skipbutton.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(this@GetOtherDetailsActivity, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        })
        binding!!.userpic.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                val intent: Intent = Intent()
                intent.setAction(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent, 45)
            }
        })
        binding!!.createprofile.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                dialog.show()
                val user: FirebaseUser? = FirebaseAuth.getInstance().getCurrentUser()
                val uid: String = user!!.getUid()
                val storageReference: StorageReference =
                    FirebaseStorage.getInstance().getReference().child("AllUser").child(uid)
                val username: String = binding!!.username.getText().toString()
                val usernumber: String = binding!!.usernumber.getText().toString()
                if (selectedImage != null) {
                    storageReference.putFile(selectedImage!!)
                        .addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot?> {
                            public override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot?) {
                                storageReference.getDownloadUrl()
                                    .addOnSuccessListener(object : OnSuccessListener<Uri> {
                                        public override fun onSuccess(uri: Uri) {
                                            val userpic: String = uri.toString()
                                            val hashMap: HashMap<String, Any> = HashMap()
                                            hashMap.put("number", usernumber)
                                            hashMap.put("name", username)
                                            hashMap.put("image", userpic)
                                            FirebaseFirestore.getInstance().collection("AllUserG")
                                                .document(
                                                    (FirebaseAuth.getInstance().getUid())!!
                                                )
                                                .update(hashMap).addOnSuccessListener(object :
                                                    OnSuccessListener<Void?> {
                                                    public override fun onSuccess(unused: Void?) {
                                                        dialog.dismiss()
                                                        val intent: Intent = Intent(
                                                            this@GetOtherDetailsActivity,
                                                            MainActivity::class.java
                                                        )
                                                        Toast.makeText(
                                                            this@GetOtherDetailsActivity,
                                                            "Data Uploaded Successfully",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        startActivity(intent)
                                                        finishAffinity()
                                                    }
                                                })
                                        }
                                    })
                            }
                        })
                } else if (selectedImage == null) {
                    val hashMap: HashMap<String, Any> = HashMap()
                    hashMap.put("number", usernumber)
                    hashMap.put("name", username)
                    FirebaseFirestore.getInstance().collection("AllUserG")
                        .document((FirebaseAuth.getInstance().getUid())!!)
                        .update(hashMap).addOnSuccessListener(object : OnSuccessListener<Void?> {
                            public override fun onSuccess(unused: Void?) {
                                dialog.dismiss()
                                val intent: Intent =
                                    Intent(this@GetOtherDetailsActivity, MainActivity::class.java)
                                Toast.makeText(
                                    this@GetOtherDetailsActivity,
                                    "Data Uploaded Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(intent)
                                finishAffinity()
                            }
                        })
                } else {
                    Toast.makeText(
                        this@GetOtherDetailsActivity,
                        "Something is wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 45) {
            binding!!.userpic.setImageURI(data!!.getData())
            selectedImage = data.getData()
        }
    }
}
