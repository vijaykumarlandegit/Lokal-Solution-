package com.easy.lokalsolution.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.Class.UserClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.ActivityGetOtherDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class GetOtherDetailsActivity() : AppCompatActivity() {
    var binding: ActivityGetOtherDetailsBinding? = null
    var image11: ImageView? = null
    var selectedImage: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetOtherDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val dialog: ProgressDialog = ProgressDialog(this)
        dialog.setMessage("Creating Profile, please wait . . . .")
        dialog.setCancelable(false)
        FirebaseFirestore.getInstance().collection("AllUserG")
            .document((FirebaseAuth.getInstance().uid)!!)
            .get().addOnCompleteListener { task ->
                val snapshot: DocumentSnapshot = task.result
                val data: UserClass? = snapshot.toObject(UserClass::class.java)
                val image: String? = data?.image
                Picasso.get().load(image).placeholder(R.drawable.userkk).into(binding!!.userpic)
            }
        binding!!.skipbutton.setOnClickListener {
            val intent: Intent = Intent(this@GetOtherDetailsActivity, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        binding!!.userpic.setOnClickListener {
            val intent: Intent = Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, 45)
        }
        binding!!.createprofile.setOnClickListener {
            dialog.show()
            val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
            val uid: String = user!!.uid
            val storageReference: StorageReference =
                FirebaseStorage.getInstance().reference.child("AllUser").child(uid)
            val username: String = binding!!.username.text.toString()
            val usernumber: String = binding!!.usernumber.text.toString()
            if (selectedImage != null) {
                storageReference.putFile(selectedImage!!)
                    .addOnSuccessListener {
                        storageReference.downloadUrl
                            .addOnSuccessListener { uri ->
                                val userpic: String = uri.toString()
                                val hashMap: HashMap<String, Any> = HashMap()
                                hashMap["number"] = usernumber
                                hashMap["name"] = username
                                hashMap["image"] = userpic
                                FirebaseFirestore.getInstance().collection("AllUserG")
                                    .document(
                                        (FirebaseAuth.getInstance().uid)!!
                                    )
                                    .update(hashMap).addOnSuccessListener {
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
                            }
                    }
            } else if (selectedImage == null) {
                val hashMap: HashMap<String, Any> = HashMap()
                hashMap["number"] = usernumber
                hashMap["name"] = username
                FirebaseFirestore.getInstance().collection("AllUserG")
                    .document((FirebaseAuth.getInstance().uid)!!)
                    .update(hashMap).addOnSuccessListener {
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
            } else {
                Toast.makeText(
                    this@GetOtherDetailsActivity,
                    "Something is wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 45) {
            binding!!.userpic.setImageURI(data!!.data)
            selectedImage = data.data
        }
    }
}
