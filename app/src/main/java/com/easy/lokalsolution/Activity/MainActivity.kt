package com.easy.lokalsolution.Activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.easy.lokalsolution.Adapter.AdapterViewPager
import com.easy.lokalsolution.Class.UserClass
import com.easy.lokalsolution.Fragment.FirstFragment
import com.easy.lokalsolution.Fragment.SecondFragment
import com.easy.lokalsolution.Fragment.ThirdFragment
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
 class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var auth: FirebaseAuth? = null
    var mGoogleSignInClient: GoogleSignInClient? = null
    var googleSignInClient: GoogleSignInClient? = null
    var dialog: ProgressDialog? = null
    var image11: ImageView? = null
    var selectedImage: Uri? = null
    var name: String? = null
    var number: String? = null
    var gmail: String? = null
    var pic: String? = null
    var fragmentarrylist = ArrayList<Fragment>()
    var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        auth = FirebaseAuth.getInstance()
        dialog = ProgressDialog(this@MainActivity)
        dialog!!.setCancelable(false)
        dialog!!.setMessage("Please wait .....")
        fragmentarrylist.add(FirstFragment())
        fragmentarrylist.add(SecondFragment())
        fragmentarrylist.add(ThirdFragment())
        bottomNavigationView = findViewById(R.id.bottomnav)
        val adapterViewPager = AdapterViewPager(this@MainActivity, fragmentarrylist)
        binding!!.pagermain.adapter = adapterViewPager
        binding!!.pagermain.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomNavigationView?.let {
                    when (position) {
                        0 -> it.selectedItemId = R.id.news
                        1 -> it.selectedItemId = R.id.query
                        2 -> it.selectedItemId = R.id.ids
                    }
                }
                super.onPageSelected(position)
            }

        })
        bottomNavigationView?.setOnItemSelectedListener { item ->
            val itemId = item.itemId
            when (itemId) {
                R.id.news -> binding?.pagermain?.currentItem = 0
                R.id.query -> binding?.pagermain?.currentItem = 1
                R.id.ids -> binding?.pagermain?.currentItem = 2
            }
            true
        }

        binding!!.progressBar.visibility = View.VISIBLE
        FirebaseFirestore.getInstance().collection("AllUserG")
            .document(FirebaseAuth.getInstance().uid!!)
            .get().addOnCompleteListener { task ->
                val snapshot = task.result
                if (snapshot.exists()) {
                    binding!!.progressBar.visibility = View.GONE
                    binding!!.allogoogleprofile.visibility = View.VISIBLE
                    val data = snapshot.toObject(UserClass::class.java)
                    name = data!!.name
                    number = data.number
                    gmail = data.email
                    pic = data.image
                    binding!!.profilename.text = name
                    binding!!.profileemail.text = gmail
                    Picasso.get().load(pic).placeholder(R.drawable.qqqa).into(binding!!.profilepic)
                } else {
                    binding!!.progressBar.visibility = View.GONE
                    binding!!.allogoogleprofile.visibility = View.GONE
                }
            }
        binding!!.editbtninprofile.setOnClickListener {
            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val dname: TextView
            val dnumber: TextView
            val dadd: Button
            val builder = AlertDialog.Builder(this@MainActivity)
            val view = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.updateuserdatadialog, viewGroup, false)
            builder.setCancelable(true)
            builder.setView(view)
            dname = view.findViewById(R.id.dialogeditusername)
            dnumber = view.findViewById(R.id.dialogeditusernumber)
            image11 = view.findViewById(R.id.dialogedituserpic)
            dadd = view.findViewById(R.id.dialogedituserupdatebtn)
            val alertDialog = builder.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dname.text = name
            dnumber.text = number
            Picasso.get().load(pic).placeholder(R.drawable.qqqa).into(image11)

            image11?.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "image/*"
                }
                startActivityForResult(intent, 45)
            }


            dadd.setOnClickListener {
                dialog!!.show()
                val user = FirebaseAuth.getInstance().currentUser
                val uid = user!!.uid
                val storageReference =
                    FirebaseStorage.getInstance().reference.child("AllUser").child(uid)
                val username = dname.text.toString()
                val usernumber = dnumber.text.toString()
                if (selectedImage != null) {
                    Toast.makeText(this@MainActivity, "with image", Toast.LENGTH_SHORT).show()
                    storageReference.putFile(selectedImage!!).addOnSuccessListener {
                        storageReference.downloadUrl.addOnSuccessListener { uri ->
                            val userpic = uri.toString()
                            val hashMap = HashMap<String, Any>()
                            hashMap["number"] = usernumber
                            hashMap["name"] = username
                            hashMap["image"] = userpic
                            FirebaseFirestore.getInstance().collection("AllUserG").document(
                                FirebaseAuth.getInstance().uid!!
                            )
                                .update(hashMap).addOnSuccessListener {
                                    dialog!!.dismiss()
                                    alertDialog.dismiss()
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Data Uploaded Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }
                } else if (selectedImage == null) {
                    Toast.makeText(this@MainActivity, "with no image", Toast.LENGTH_SHORT).show()
                    val hashMap = HashMap<String, Any>()
                    hashMap["number"] = usernumber
                    hashMap["name"] = username
                    FirebaseFirestore.getInstance().collection("AllUserG")
                        .document(FirebaseAuth.getInstance().uid!!)
                        .update(hashMap).addOnSuccessListener {
                            dialog!!.dismiss()
                            alertDialog.dismiss()
                            Toast.makeText(
                                this@MainActivity,
                                "Data Uploaded Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    Toast.makeText(this@MainActivity, "Something is wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            alertDialog.show()
        }
        binding!!.sidebarmainbtn.setOnClickListener {
            binding!!.sidebarmain.visibility = View.VISIBLE
            binding!!.just1.visibility = View.VISIBLE
        }
        binding!!.just1.setOnClickListener {
            binding!!.sidebarmain.visibility = View.GONE
            binding!!.just1.visibility = View.GONE
        }
        binding!!.contactus.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingShowActivity::class.java)
            intent.putExtra("type", "contactus")
            startActivity(intent)
        }
        binding!!.aboutus.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingShowActivity::class.java)
            intent.putExtra("type", "aboutus")
            startActivity(intent)
        }
        binding!!.privacypolicy.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingShowActivity::class.java)
            intent.putExtra("type", "pp")
            startActivity(intent)
        }
        binding!!.termandcondition.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingShowActivity::class.java)
            intent.putExtra("type", "tc")
            startActivity(intent)
        }
        binding!!.moreapps.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/developer?id=ResiEasy-+Buy/Rent/Sell+Residency")
                )
            )

            /* try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:ResiEasy-+Buy/Rent/Sell+Residency")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=ResiEasy-+Buy/Rent/Sell+Residency")));
                    }*/
        }
        binding!!.shareapp.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, "ResiEasy")
                val applink =
                    "https://play.google.com/store/apps/details?id=" + applicationContext.packageName
                intent.putExtra(Intent.EXTRA_TEXT, applink)
                startActivity(Intent.createChooser(intent, "Share ResiEasy Application"))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Something is wrong", Toast.LENGTH_SHORT).show()
            }
        }
        binding!!.rateus.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + applicationContext.packageName)
                    )
                )
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this@MainActivity, "Something is wrong", Toast.LENGTH_SHORT).show()
            }
        }
        binding!!.logoutsidebar.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this@MainActivity)
            builder.setIcon(R.drawable.warna)
            builder.setTitle("LOGOUT")
            builder.setMessage("you are sure, you want to logout your account.")
            builder.setPositiveButton("Yes") { dialog, which ->
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val googleSignInClient = GoogleSignIn.getClient(this@MainActivity, gso)
                googleSignInClient.signOut().addOnSuccessListener {
                    val hashMap = HashMap<String, Any>()
                    hashMap["token"] = ""
                    FirebaseFirestore.getInstance().collection("AllUserG")
                        .document(FirebaseAuth.getInstance().uid!!)
                        .update(hashMap).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                auth!!.signOut()
                                val intent = Intent(this@MainActivity, SigninActivity::class.java)
                                startActivity(intent)
                                finishAffinity()
                                Toast.makeText(
                                    this@MainActivity,
                                    "Logout Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
                .setNegativeButton("No") { dialog, which -> dialog.dismiss() }
                .setNeutralButton("Help") { dialog, which ->
                    Toast.makeText(
                        this@MainActivity,
                        "for logout, press yes",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            builder.show()
        }
        binding!!.uploadhere.setOnClickListener {
            val intent = Intent(this@MainActivity, UploadFromHareActivity::class.java)
            startActivity(intent)
        }
        binding!!.newsidebar.setOnClickListener {
            val intent = Intent(this@MainActivity, ShowDataToOwnerActivity::class.java)
            intent.putExtra("type", "11")
            startActivity(intent)
        }
        binding!!.querysidebar.setOnClickListener {
            val intent = Intent(this@MainActivity, ShowDataToOwnerActivity::class.java)
            intent.putExtra("type", "12")
            startActivity(intent)
        }
        binding!!.idsidebar.setOnClickListener {
            val intent = Intent(this@MainActivity, ShowDataToOwnerActivity::class.java)
            intent.putExtra("type", "13")
            startActivity(intent)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        android.app.AlertDialog.Builder(this@MainActivity)
            .setTitle("Exit")
            .setMessage("Are you sure you want to close application ?")
            .setIcon(R.drawable.warna)
            .setCancelable(true)
            .setPositiveButton("Yes") { dialogInterface, i ->
                dialogInterface.dismiss()
                finishAffinity()
            }
            .setNegativeButton("No") { dialogInterface, i -> dialogInterface.dismiss() }.create()
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 45) {
            image11!!.setImageURI(data!!.data)
            selectedImage = data.data
        }
    }
}