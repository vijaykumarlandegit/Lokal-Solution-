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
import android.widget.RadioButton;
import android.widget.Toast;

import com.easy.lokalsolution.Class.IdClass;
import com.easy.lokalsolution.Class.OwnIdClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.ActivityAddIdBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class AddIdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityAddIdBinding binding;
    Uri image11;
    Uri image12;
    String gender[], mechtype;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.IdType, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.subtype.setAdapter(adapter1);
        binding.subtype.setOnItemSelectedListener(AddIdActivity.this);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait, Creating your ID .....");
        dialog.setCancelable(false);


        binding.yescourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.yescourcevieww.setVisibility(View.VISIBLE);
            }
        });

        binding.nocourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.yescourcevieww.setVisibility(View.GONE);
            }
        });

        binding.alltime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.customtimeview.setVisibility(View.GONE);
            }
        });binding.owntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.customtimeview.setVisibility(View.VISIBLE);
            }
        });

        binding.yesshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.yesshopvieww.setVisibility(View.VISIBLE);
            }
        });
        binding.noshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.yesshopvieww.setVisibility(View.GONE);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        int id12 = binding.yescourceradiogroupview.getCheckedRadioButtonId();
        RadioButton radioButton01 = findViewById(id12);

        if (radioButton01.getText().equals("Yes")) {


        }
        if (radioButton01.getText().equals("No")) {


        }

        Long timestamp = System.currentTimeMillis() / 1000;
        String currentime = timestamp.toString();

        binding.uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(AddIdActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(11);
            }
        });

        binding.submitcmid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

                int id1 = binding.yescourceradiogroupview.getCheckedRadioButtonId();
                RadioButton radioButton1 = findViewById(id1);
                int id2 = binding.yesshopradiogroupview.getCheckedRadioButtonId();
                RadioButton radioButton2 = findViewById(id2);
                int id3 = binding.servitimergroupview.getCheckedRadioButtonId();
                RadioButton radioButton3 = findViewById(id3);

                if (radioButton1.getText().toString().equals("Yes")) {

                    if (!binding.coursename.getText().toString().isEmpty()) {

                        String cname = binding.coursename.getText().toString();
                        String cdetail = binding.coursedetails.getText().toString();

                        if (radioButton2.getText().toString().equals("Yes")) {

                            if (!binding.shopname.getText().toString().isEmpty() && !binding.shopaddress.getText().toString().isEmpty()) {
                                String sname = binding.shopname.getText().toString();
                                String saddress = binding.shopaddress.getText().toString();


                                if (radioButton3.getText().toString().equals("Customize time")) {

                                    if (!binding.customtime.getText().toString().isEmpty()) {

                                        String sstime = binding.customtime.getText().toString();
                                        uploaddata(cname, cdetail, sname, saddress, sstime);

                                    } else {

                                        if (binding.customtime.getText().toString().isEmpty()) {
                                            binding.customtime.setError("Please select start time");
                                            dialog.dismiss();
                                            Toast.makeText(AddIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                } else {
                                    String sstime = "!@#$%";
                                    uploaddata(cname, cdetail, sname, saddress, sstime);

                                }


                            } else {
                                if (binding.shopname.getText().toString().isEmpty()) {
                                    binding.shopname.setError("Please enter shop name");
                                    dialog.dismiss();
                                    Toast.makeText(AddIdActivity.this, "Please enter shop name", Toast.LENGTH_SHORT).show();
                                }
                                if (binding.shopaddress.getText().toString().isEmpty()) {
                                    binding.shopaddress.setError("Please enter shop address");
                                    dialog.dismiss();
                                    Toast.makeText(AddIdActivity.this, "Please enter shop address", Toast.LENGTH_SHORT).show();
                                }
                            }


                        } else {
                            String sname = "!@#$%";
                            String saddress = "!@#$%";
                            if (radioButton3.getText().toString().equals("Customize time")) {

                                if (!binding.customtime.getText().toString().isEmpty()) {

                                    String sstime = binding.customtime.getText().toString();
                                    uploaddata(cname, cdetail, sname, saddress, sstime);

                                } else {

                                    if (binding.customtime.getText().toString().isEmpty()) {
                                        binding.customtime.setError("Please select start time");
                                        dialog.dismiss();
                                        Toast.makeText(AddIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
                                    }

                                }

                            } else {
                                String sstime = "!@#$%";
                                uploaddata(cname, cdetail, sname, saddress, sstime);

                            }

                        }
                    } else {
                        binding.coursename.setError("Please enter course name");
                        dialog.dismiss();
                        Toast.makeText(AddIdActivity.this, "Please enter course name", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    String cname = "!@#$%";
                    String cdetail = "!@#$%";

                    if (radioButton2.getText().toString().equals("Yes")) {

                        if (!binding.shopname.getText().toString().isEmpty() && !binding.shopaddress.getText().toString().isEmpty()) {
                            String sname = binding.shopname.getText().toString();
                            String saddress = binding.shopaddress.getText().toString();

                            if (radioButton3.getText().toString().equals("Customize time")) {

                                if (!binding.customtime.getText().toString().isEmpty()) {

                                    String sstime = binding.customtime.getText().toString();
                                    uploaddata(cname, cdetail, sname, saddress, sstime);

                                } else {

                                    if (binding.customtime.getText().toString().isEmpty()) {
                                        binding.customtime.setError("Please select start time");
                                        dialog.dismiss();
                                        Toast.makeText(AddIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
                                    }

                                }

                            } else {
                                String sstime = "!@#$%";
                                uploaddata(cname, cdetail, sname, saddress, sstime);

                            }

                        } else {
                            if (binding.shopname.getText().toString().isEmpty()) {
                                binding.shopname.setError("Please enter shop name");
                                dialog.dismiss();
                                Toast.makeText(AddIdActivity.this, "Please enter shop name", Toast.LENGTH_SHORT).show();
                            }
                            if (binding.shopaddress.getText().toString().isEmpty()) {
                                binding.shopaddress.setError("Please enter shop address");
                                dialog.dismiss();
                                Toast.makeText(AddIdActivity.this, "Please enter shop address", Toast.LENGTH_SHORT).show();
                            }
                        }


                    } else {
                        String sname = "!@#$%";
                        String saddress = "!@#$%";
                        if (radioButton3.getText().toString().equals("Customize time")) {

                            if (!binding.customtime.getText().toString().isEmpty()) {

                                String sstime = binding.customtime.getText().toString();
                                uploaddata(cname, cdetail, sname, saddress, sstime);

                            } else {

                                if (binding.customtime.getText().toString().isEmpty()) {
                                    binding.customtime.setError("Please select start time");
                                    dialog.dismiss();
                                    Toast.makeText(AddIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
                                }

                            }

                        } else {
                            String sstime = "!@#$%";
                            uploaddata(cname, cdetail, sname, saddress, sstime);

                        }

                    }

                }


            }
        });

    }



    private void uploaddata(String cnamez, String cdetailz, String snamez, String saddressz, String sstimez) {


        String subtype = binding.subtype.getSelectedItem().toString();
        String exptime = binding.exptime.getText().toString();
        String more = binding.moredetails.getText().toString();
        String uname = binding.username.getText().toString();
        String ucontact = binding.contactno.getText().toString();
        String uwhatsapp = binding.whatsappno.getText().toString();
        String uemail = binding.cmmail.getText().toString();

        if (!subtype.isEmpty()&&!exptime.isEmpty() && !uname.isEmpty() && !ucontact.isEmpty()) {


            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Nanded")
                    .child(FirebaseAuth.getInstance().getUid()).child("Id").child(ts);
            CollectionReference queryCollectionRef = FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id");
            CollectionReference ownCollectionRef = FirebaseFirestore.getInstance().collection("OwnData")
                    .document(FirebaseAuth.getInstance().getUid()).collection("Id");
            String id = queryCollectionRef.document().getId();


            if (image11 != null) {
                ImageFolder.putFile(image11)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                ImageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        String image11 = uri.toString();

                                        Date date = new Date();
                                        IdClass cmidClass = new IdClass(subtype, cnamez, cdetailz, exptime, snamez, saddressz, sstimez, more, uname, ucontact, uwhatsapp, uemail, image11, id, FirebaseAuth.getInstance().getUid(), date.getTime());
                                        queryCollectionRef.document(id).set(cmidClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                OwnIdClass ownQueryClass = new OwnIdClass(id, new Date().getTime());
                                                ownCollectionRef.document(id).set(ownQueryClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        finish();
                                                        dialog.dismiss();
                                                        Toast.makeText(AddIdActivity.this, "ID Uploaded Successfully", Toast.LENGTH_SHORT).show();


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
                                Toast.makeText(AddIdActivity.this, "fail", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        });
            } else if (image11 == null) {


                Date date = new Date();
                IdClass cmidClass = new IdClass(subtype, cnamez, cdetailz, exptime, snamez, saddressz, sstimez, more, uname, ucontact, uwhatsapp, uemail, "No", id, FirebaseAuth.getInstance().getUid(), date.getTime());
                queryCollectionRef.document(id).set(cmidClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        OwnIdClass ownQueryClass = new OwnIdClass(id, new Date().getTime());
                        ownCollectionRef.document(id).set(ownQueryClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                dialog.dismiss();
                                Toast.makeText(AddIdActivity.this, "ID Uploaded Successfully", Toast.LENGTH_SHORT).show();


                            }
                        });


                    }
                });
            } else {
                dialog.dismiss();

                Toast.makeText(AddIdActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }


        } else {
            if (more.isEmpty()) {
                binding.moredetails.setError("Please enter more about work");
                dialog.dismiss();
                Toast.makeText(this, "Please enter more about work", Toast.LENGTH_SHORT).show();
            } if (exptime.isEmpty()) {
                binding.exptime.setError("Please enter experience period");
                dialog.dismiss();
                Toast.makeText(this, "Please enter experience period", Toast.LENGTH_SHORT).show();
            }
            if (uname.isEmpty()) {
                binding.username.setError("Please enter your name");
                dialog.dismiss();
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
            if (ucontact.isEmpty()) {
                binding.contactno.setError("Please enter contact number");
                dialog.dismiss();
                Toast.makeText(this, "Please enter contact number", Toast.LENGTH_SHORT).show();
            }

        }



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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String querytype = adapterView.getItemAtPosition(position).toString();
        String mechtype = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}