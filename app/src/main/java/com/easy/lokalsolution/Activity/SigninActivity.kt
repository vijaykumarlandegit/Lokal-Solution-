package com.easy.lokalsolution.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.Class.UserClass
import com.easy.lokalsolution.databinding.ActivitySigninBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
 class SigninActivity() : AppCompatActivity() {
    var binding: ActivitySigninBinding? = null
    var auth: FirebaseAuth? = null
    var mAuth: FirebaseAuth? = null
    var RC_SIGN_IN: Int = 100
    var personName: String? = null
    var personEmail: String? = null
    var personalNumber: String? = null
    var personPhoto: Uri? = null
    var image1: String? = null
    var progressDialog: ProgressDialog? = null
    var progressDialog2: ProgressDialog? = null
    var progressDialog3: ProgressDialog? = null
      var storage: FirebaseStorage? = null
    var database: FirebaseDatabase? = null
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        auth = FirebaseAuth.getInstance()
        mAuth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        database = FirebaseDatabase.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Creating Account")
        progressDialog!!.setMessage("Please wait, we are creating your account on LokalSolution .....")
        progressDialog!!.setCancelable(false)
        progressDialog2 = ProgressDialog(this)
        progressDialog2!!.setTitle("Fetching Your Account .....")
        progressDialog2!!.setCancelable(false)
        progressDialog3 = ProgressDialog(this)
        progressDialog3!!.setTitle("Please wait .....")
        progressDialog3!!.setCancelable(false)
        image1 =
            "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png"
        val googleSignInOptions: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("647544275082-7k4a9vli2m83lfovdd6k48tjb6s5feav.apps.googleusercontent.com")
                .requestEmail()
                .build()

        // Initialize sign in client
         var googleSignInClient = GoogleSignIn.getClient(this@SigninActivity, googleSignInOptions)
         binding!!.googlesigninbutton.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                progressDialog2!!.show()
                val signInIntent: Intent = googleSignInClient!!.getSignInIntent()
                startActivityForResult(signInIntent, 123)
            }
        })
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            progressDialog2!!.dismiss()
            progressDialog!!.show()
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId())
                firebaseAuthWithGoogle(account.getIdToken())
            } catch (e: ApiException) {
                progressDialog2!!.dismiss()
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth!!.signInWithCredential(credential)
            .addOnCompleteListener(this, object : OnCompleteListener<AuthResult?> {
                public override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", "signInWithCredential:success")
                        val user: FirebaseUser? = auth!!.getCurrentUser()
                        val acct: GoogleSignInAccount? =
                            GoogleSignIn.getLastSignedInAccount(this@SigninActivity)
                        if (acct != null) {
                            personName = user!!.getDisplayName()
                            personEmail = user.getEmail()
                            personalNumber = user.getPhoneNumber()
                            personPhoto = acct.getPhotoUrl()
                            FirebaseMessaging.getInstance().getToken()
                                .addOnCompleteListener(object : OnCompleteListener<String?> {
                                    public override fun onComplete(task: Task<String?>) {
                                        val tokenn: String? = task.getResult()
                                        val userClass: UserClass = UserClass(
                                            image1,
                                            "",
                                            personEmail,
                                            "",
                                            tokenn,
                                            FirebaseAuth.getInstance().getUid(),
                                            "Nanded"
                                        )
                                        FirebaseFirestore.getInstance().collection("AllUserG")
                                            .document((FirebaseAuth.getInstance().getUid())!!)
                                            .set(userClass).addOnSuccessListener(object :
                                                OnSuccessListener<Void?> {
                                                public override fun onSuccess(unused: Void?) {
                                                    progressDialog!!.dismiss()
                                                    Toast.makeText(
                                                        this@SigninActivity,
                                                        "Your Account Is Created",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                    Toast.makeText(
                                                        this@SigninActivity,
                                                        "Welcome To Lokal Solution",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                    val intent: Intent = Intent(
                                                        this@SigninActivity,
                                                        GetOtherDetailsActivity::class.java
                                                    )
                                                    startActivity(intent)
                                                    finishAffinity()
                                                }
                                            })
                                    }
                                })
                        }
                    } else {
                        progressDialog!!.dismiss()
                        Log.w("TAG", "signInWithCredential:failure", task.getException())
                    }
                }
            })
    }
}
