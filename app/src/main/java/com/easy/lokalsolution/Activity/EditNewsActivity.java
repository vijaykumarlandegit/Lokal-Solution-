package com.easy.lokalsolution.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.easy.lokalsolution.Class.NewsClass;
import com.easy.lokalsolution.Class.OwnNewsClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.ActivityEditNewsBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EditNewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityEditNewsBinding binding;
    String rentaltype[], areatype[];
    Uri image11;
    int PICK_IMG = 123, in;
    double latitude, longitude;

    HashMap<String, String> hashMap;


    ProgressDialog dialog, dialog1;

    private static final int PICK_IMAGE = 1;


    private int upload_count = 0;

    ArrayList<Uri> newlist = new ArrayList<>();
    Uri newuri;
    int mainum = 0;

    ArrayList newStrings = new ArrayList<>();
    String id;
    String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait, Editing post .....");
        dialog.setCancelable(false);


      
        
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.NewsType1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.newtype.setAdapter(adapter);
        binding.newtype.setOnItemSelectedListener(EditNewsActivity.this);

         id=getIntent().getStringExtra("id");

        FirebaseFirestore.getInstance().collection("Nanded").document("NandedCity")
                .collection("News").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        NewsClass data=documentSnapshot.toObject(NewsClass.class);
                        
                        
                        String type=data.getType();
                        String title=data.getTitle();
                        String disc=data.getDisc();
                         image=data.getImage();

                        binding.typetext.setText(type);
                        binding.newtitle.setText(title);
                        binding.newdis.setText(disc);



                        if (!image.equals("No")){
                            binding.imagee.setVisibility(View.VISIBLE);
                            Picasso.get().load(image).placeholder(R.drawable.images).into(binding.imagee);
                        }else {
                            binding.imagee.setVisibility(View.GONE);

                        }
                        
                        



                    }
                });

        binding.newtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);

                if (item.toString().equals("News")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("News");

                } else if (item.toString().equals("Advertisement")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Advertisement");

                } else if (item.toString().equals("Story")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Story");

                } else if (item.toString().equals("Poetry")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Poetry");



                }else if (item.toString().equals("Announcement")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Announcement");



                }else if (item.toString().equals("Puzzle")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Puzzle");



                }else if (item.toString().equals("GK Question")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("GK Question");



                }else if (item.toString().equals("Other")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Other");



                }else if (item.toString().equals("")) {

                    binding.typetext.setVisibility(View.VISIBLE);


                }


            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        binding.uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditNewsActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(11);
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

                    if (image11 != null) {
                        ImageFolder.putFile(image11)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        ImageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                String image11 = uri.toString();
                                                String type = binding.typetext.getText().toString();


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
                                                                Toast.makeText(EditNewsActivity.this, "News Uploaded Successfully", Toast.LENGTH_SHORT).show();


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
                                        Toast.makeText(EditNewsActivity.this, "fail", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }
                                });
                    } else if (image11 == null) {

                        String type = binding.typetext.getText().toString();

                        Date date = new Date();
                        NewsClass cmidClass = new NewsClass(type, title, disc, userid, id, image, date.getTime());
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
                                                Toast.makeText(EditNewsActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                            }
                        });


                    } else {
                        dialog.dismiss();

                        Toast.makeText(EditNewsActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (title.isEmpty()) {
                        dialog.dismiss();

                        binding.newtitle.setError("Please enter news title");
                        Toast.makeText(EditNewsActivity.this, "Please enter news title", Toast.LENGTH_SHORT).show();

                    }
                    if (disc.isEmpty()) {
                        dialog.dismiss();

                        binding.newdis.setError("Please enter new description");
                        Toast.makeText(EditNewsActivity.this, "Please enter new description", Toast.LENGTH_SHORT).show();


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