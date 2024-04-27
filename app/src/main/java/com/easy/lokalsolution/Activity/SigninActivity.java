package com.easy.lokalsolution.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.easy.lokalsolution.Class.UserClass;
import com.easy.lokalsolution.databinding.ActivitySigninBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SigninActivity extends AppCompatActivity {

    ActivitySigninBinding binding;
    FirebaseAuth auth,mAuth;
    int RC_SIGN_IN = 100;
    String personName, personEmail,personalNumber;
    Uri personPhoto;
    String image1;

    ProgressDialog progressDialog,progressDialog2,progressDialog3;

    GoogleSignInClient googleSignInClient;
    StorageReference sreference;
    FirebaseStorage storage;
    FirebaseDatabase database;
    DatabaseReference dreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Please wait, we are creating your account on LokalSolution .....");
        progressDialog.setCancelable(false);
        progressDialog2=new ProgressDialog(this);
        progressDialog2.setTitle("Fetching Your Account ....." );
        progressDialog2.setCancelable(false);
        progressDialog3=new ProgressDialog(this);
        progressDialog3.setTitle("Please wait ....." );
        progressDialog3.setCancelable(false);

        image1="https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png";





        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("647544275082-7k4a9vli2m83lfovdd6k48tjb6s5feav.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(SigninActivity.this, googleSignInOptions);



        binding.googlesigninbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog2.show();
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 123);


            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
                progressDialog2.dismiss();

                progressDialog.show();

                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    progressDialog2.dismiss();
                    Log.w("TAG", "Google sign in failed", e);
                }

            }

    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SigninActivity.this);
                            if (acct != null) {
                                personName = user.getDisplayName();
                                personEmail = user.getEmail();
                                personalNumber = user.getPhoneNumber();
                                personPhoto = acct.getPhotoUrl();


                                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                                    @Override
                                    public void onComplete(@NonNull Task<String> task) {
                                        String tokenn=task.getResult();

                                        UserClass userClass=new UserClass(image1,"",personEmail,"",tokenn,FirebaseAuth.getInstance().getUid(),"Nanded");

                                        FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                                                .set(userClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {

                                                        progressDialog.dismiss();
                                                        Toast.makeText(SigninActivity.this, "Your Account Is Created", Toast.LENGTH_LONG).show();
                                                        Toast.makeText(SigninActivity.this, "Welcome To Lokal Solution", Toast.LENGTH_LONG).show();

                                                        Intent intent=new Intent(SigninActivity.this, GetOtherDetailsActivity.class);
                                                        startActivity(intent);
                                                        finishAffinity();

                                                    }
                                                });
                                    }
                                });


                            }
                        } else {
                            progressDialog.dismiss();

                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}


