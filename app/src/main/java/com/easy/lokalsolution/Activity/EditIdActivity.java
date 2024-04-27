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
import com.easy.lokalsolution.databinding.ActivityEditIdBinding;
import com.easy.lokalsolution.databinding.ActivityEditQueryBinding;
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

import java.util.Date;

public class EditIdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityEditIdBinding binding;
    Uri image11;
    Uri image12;
    String gender[], mechtype;
    double latitude, longitude;
    ProgressDialog dialog, dialog1;
    String id,image;
    Object item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.IdType1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.subtype.setAdapter(adapter1);
        binding.subtype.setOnItemSelectedListener(EditIdActivity.this);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait, Creating your ID .....");
        dialog.setCancelable(false);


         id = getIntent().getStringExtra("id");


        FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        IdClass snapshot = documentSnapshot.toObject(IdClass.class);

                        String type = snapshot.getType();
                        String coursename = snapshot.getCname();
                        String coursedetails = snapshot.getCdetail();
                        String exptime = snapshot.getExptime();
                        String shopname = snapshot.getSname();
                        String shopaddress = snapshot.getSaddress();
                        String starttime = snapshot.getCustomtime();
                        String moredetails = snapshot.getMore();
                        String umname = snapshot.getUname();
                        String ucontact = snapshot.getUcontact();
                        String uwhatsapp = snapshot.getUwhatsapp();
                        String uemail = snapshot.getUemail();
                         image = snapshot.getImage();


                        binding.typetext.setText(type);
                        binding.exptime.setText(exptime);
                        binding.moredetails.setText(moredetails);
                        binding.username.setText(umname);
                        binding.contactno.setText(ucontact);
                        binding.whatsappno.setText(uwhatsapp);
                        binding.cmmail.setText(uemail);

                        if (!image.equals("No")){
                            binding.imagee.setVisibility(View.VISIBLE);
                            Picasso.get().load(image).placeholder(R.drawable.placehald).into(binding.imagee);
                        }else {
                            binding.imagee.setVisibility(View.GONE);

                        }
                        if (coursename.equals("!@#$%")&&coursedetails.equals("!@#$%")){
                            binding.yescourcevieww.setVisibility(View.GONE);
                            binding.nocourse.setChecked(true);
                        }else {
                            binding.yescourcevieww.setVisibility(View.VISIBLE);
                            binding.yescourse.setChecked(true);
                            binding.coursename.setText(coursename);
                            binding.coursedetails.setText(coursedetails);
                        }
                        if (shopname.equals("!@#$%")&&shopaddress.equals("!@#$%")){
                            binding.yesshopvieww.setVisibility(View.GONE);
                            binding.noshop.setChecked(true);
                        }else {
                            binding.yesshopvieww.setVisibility(View.VISIBLE);
                            binding.yesshop.setChecked(true);
                            binding.shopname.setText(shopname);
                            binding.shopaddress.setText(shopaddress);
                        }

                        if (starttime.equals("!@#$%")){
                            binding.customtimeview.setVisibility(View.GONE);
                            binding.anytime.setChecked(true);
                        }else {
                            binding.customtimeview.setVisibility(View.VISIBLE);
                            binding.owntime.setChecked(true);
                            binding.customtime.setText(starttime);
                        }


                    }
                });

        binding.subtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position);

                if (item.toString().equals("Mechanic")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Mechanic");

                } else if (item.toString().equals("Plumber")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Plumber");

                } else if (item.toString().equals("Electrician")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Electrician");

                } else if (item.toString().equals("Electronic Technician")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Electronic Technician");

                } else if (item.toString().equals("Painter")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Painter");



                }else if (item.toString().equals("Carpenter")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Carpenter");



                }else if (item.toString().equals("Event Management")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Event Management");



                }else if (item.toString().equals("Photographer")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Photographer");



                }else if (item.toString().equals("Home Shifting")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Home Shifting");



                }else if (item.toString().equals("Other")) {
                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("Other");



                } else if (item.toString().equals("")) {

                    binding.typetext.setVisibility(View.VISIBLE);


                }


            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
        binding.anytime.setOnClickListener(new View.OnClickListener() {
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



        Long timestamp = System.currentTimeMillis() / 1000;
        String currentime = timestamp.toString();

        binding.uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditIdActivity.this)
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
                                            Toast.makeText(EditIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(EditIdActivity.this, "Please enter shop name", Toast.LENGTH_SHORT).show();
                                }
                                if (binding.shopaddress.getText().toString().isEmpty()) {
                                    binding.shopaddress.setError("Please enter shop address");
                                    dialog.dismiss();
                                    Toast.makeText(EditIdActivity.this, "Please enter shop address", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(EditIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(EditIdActivity.this, "Please enter course name", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(EditIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(EditIdActivity.this, "Please enter shop name", Toast.LENGTH_SHORT).show();
                            }
                            if (binding.shopaddress.getText().toString().isEmpty()) {
                                binding.shopaddress.setError("Please enter shop address");
                                dialog.dismiss();
                                Toast.makeText(EditIdActivity.this, "Please enter shop address", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(EditIdActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
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


        String subtype = binding.typetext.getText().toString();
        String exptime = binding.exptime.getText().toString();
        String more = binding.moredetails.getText().toString();
        String uname = binding.username.getText().toString();
        String ucontact = binding.contactno.getText().toString();
        String uwhatsapp = binding.whatsappno.getText().toString();
        String uemail = binding.cmmail.getText().toString();

        if (!more.isEmpty()&&!exptime.isEmpty() && !uname.isEmpty() && !ucontact.isEmpty()) {


            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Nanded")
                    .child(FirebaseAuth.getInstance().getUid()).child("Id").child(ts);
            CollectionReference queryCollectionRef = FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id");
            CollectionReference ownCollectionRef = FirebaseFirestore.getInstance().collection("OwnData")
                    .document(FirebaseAuth.getInstance().getUid()).collection("Id");


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
                                                        Toast.makeText(EditIdActivity.this, "ID Uploaded Successfully", Toast.LENGTH_SHORT).show();


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
                                Toast.makeText(EditIdActivity.this, "fail", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        });
            } else if (image11 == null) {


                Date date = new Date();
                IdClass cmidClass = new IdClass(subtype, cnamez, cdetailz, exptime, snamez, saddressz, sstimez, more, uname, ucontact, uwhatsapp, uemail, image, id, FirebaseAuth.getInstance().getUid(), date.getTime());
                queryCollectionRef.document(id).set(cmidClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        OwnIdClass ownQueryClass = new OwnIdClass(id, new Date().getTime());
                        ownCollectionRef.document(id).set(ownQueryClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                dialog.dismiss();
                                Toast.makeText(EditIdActivity.this, "ID Uploaded Successfully", Toast.LENGTH_SHORT).show();


                            }
                        });


                    }
                });
            } else {
                dialog.dismiss();

                Toast.makeText(EditIdActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }


        } else {
            if (more.isEmpty()) {
                binding.moredetails.setError("Please enter more about work");
                dialog.dismiss();
                Toast.makeText(this, "Please enter more about work", Toast.LENGTH_SHORT).show();
            }
            if (exptime.isEmpty()) {
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}