package com.easy.lokalsolution.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.easy.lokalsolution.Class.NewsClass;
import com.easy.lokalsolution.Class.OwnNewsClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.ActivityAddNewsBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AddNewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityAddNewsBinding binding;

    String rentaltype[], areatype[];

    int PICK_IMG = 123, in;
    double latitude, longitude;
    Uri image11;
    HashMap<String, String> hashMap;


    ProgressDialog dialog, dialog1;

    private static final int PICK_IMAGE = 1;


    private int upload_count = 0;

    ArrayList<Uri> newlist = new ArrayList<>();
    Uri newuri;
    int mainum = 0;

    ArrayList newStrings = new ArrayList<>();
    String subtype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait, Uploading post .....");
        dialog.setCancelable(false);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.NewsType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.newtype.setAdapter(adapter);
        binding.newtype.setOnItemSelectedListener(AddNewsActivity.this);

        binding.uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(AddNewsActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(11);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.uploadnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                String title = binding.newtitle.getText().toString();
                String disc = binding.newdis.getText().toString();

                if (!title.isEmpty() && !disc.isEmpty()) {


                    dialog.show();

                    String userid = FirebaseAuth.getInstance().getUid();

                    Long tsLong = System.currentTimeMillis() / 1000;
                    String ts = tsLong.toString();
                    StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Nanded")
                            .child(FirebaseAuth.getInstance().getUid()).child("News").child(ts);
                    CollectionReference newsCollectionRef = FirebaseFirestore.getInstance().collection("Nanded")
                            .document("NandedCity").collection("News");
                    CollectionReference ownCollectionRef = FirebaseFirestore.getInstance().collection("OwnData")
                            .document(userid).collection("News");

                    String id = newsCollectionRef.document().getId();
                    if (image11 != null) {
                        ImageFolder.putFile(image11)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        ImageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                String image11 = uri.toString();
                                                String type = binding.newtype.getSelectedItem().toString();


                                                Date date = new Date();
                                                NewsClass cmidClass = new NewsClass(type, title, disc, userid, id, image11, date.getTime());
                                                OwnNewsClass cmidClass1 = new OwnNewsClass(id, date.getTime());
                                                newsCollectionRef.document(id).set(cmidClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        ownCollectionRef.document(id).set(cmidClass1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                finish();
                                                                dialog.dismiss();
                                                                Toast.makeText(AddNewsActivity.this, "News Uploaded Successfully", Toast.LENGTH_SHORT).show();


                                                            }
                                                        });


                                                    }
                                                });

                                            }
                                        });


                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AddNewsActivity.this, "fail", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }
                                });
                    } else if (image11 == null) {

                        String type = binding.newtype.getSelectedItem().toString();

                        Date date = new Date();
                        NewsClass cmidClass = new NewsClass(type, title, disc, userid, id, "No", date.getTime());
                        OwnNewsClass cmidClass1 = new OwnNewsClass(id, date.getTime());


                        newsCollectionRef.document(id).set(cmidClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                ownCollectionRef.document(id).set(cmidClass1)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialog.dismiss();
                                                finish();
                                                Toast.makeText(AddNewsActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                            }
                        });


                    } else {
                        dialog.dismiss();

                        Toast.makeText(AddNewsActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (title.isEmpty()) {
                        dialog.dismiss();

                        binding.newtitle.setError("Please enter news title");
                        Toast.makeText(AddNewsActivity.this, "Please enter news title", Toast.LENGTH_SHORT).show();

                    }
                    if (disc.isEmpty()) {
                        dialog.dismiss();

                        binding.newdis.setError("Please enter new description");
                        Toast.makeText(AddNewsActivity.this, "Please enter new description", Toast.LENGTH_SHORT).show();


                    }


                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            binding.imagee.setVisibility(View.VISIBLE);
            binding.imagee.setImageURI(data.getData());
            image11 = data.getData();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}