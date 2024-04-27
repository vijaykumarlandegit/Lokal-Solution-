package com.easy.lokalsolution.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.easy.lokalsolution.Class.UserClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.ActivityGetOtherDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class GetOtherDetailsActivity extends AppCompatActivity {

    ActivityGetOtherDetailsBinding binding;
    ImageView image11;

    Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGetOtherDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Creating Profile, please wait . . . .");
        dialog.setCancelable(false);


        FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot = task.getResult();

                            UserClass data = snapshot.toObject(UserClass.class);
                            String image= data.getImage();
                            Picasso.get().load(image).placeholder(R.drawable.userkk).into(binding.userpic);
                    }
                });


        binding.skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GetOtherDetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        binding.userpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 45);
            }
        });
        binding.createprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("AllUser").child(uid);

                String username = binding.username.getText().toString();
                String usernumber = binding.usernumber.getText().toString();

                if (selectedImage != null) {
                    storageReference.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String userpic = uri.toString();

                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    hashMap.put("number", usernumber);
                                    hashMap.put("name", username);
                                    hashMap.put("image", userpic);

                                    FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                                            .update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    dialog.dismiss();
                                                    Intent intent=new Intent(GetOtherDetailsActivity.this, MainActivity.class);
                                                    Toast.makeText(GetOtherDetailsActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();

                                                    startActivity(intent);
                                                    finishAffinity();

                                                }
                                            });

                                }
                            });
                        }
                    });

                } else if (selectedImage == null) {

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("number", usernumber);
                    hashMap.put("name", username);

                    FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                            .update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    dialog.dismiss();
                                    Intent intent=new Intent(GetOtherDetailsActivity.this, MainActivity.class);
                                    Toast.makeText(GetOtherDetailsActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();

                                    startActivity(intent);
                                    finishAffinity();
                                }
                            });
                } else {
                    Toast.makeText(GetOtherDetailsActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45) {
            binding.userpic.setImageURI(data.getData());
            selectedImage = data.getData();
        }


    }
}
