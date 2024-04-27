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

import com.easy.lokalsolution.Class.OwnQueryClass;
import com.easy.lokalsolution.Class.QueryClass;
import com.easy.lokalsolution.R;
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

public class EditQueryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityEditQueryBinding binding;
    ProgressDialog dialog;

    Uri image11;
    String querytype[];
    String id;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditQueryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.QueryType1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.querytype.setAdapter(adapter);
        binding.querytype.setOnItemSelectedListener(EditQueryActivity.this);


        id = getIntent().getStringExtra("id");


        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait, Editing query .....");
        dialog.setCancelable(false);


        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();


        FirebaseFirestore.getInstance().collection("Nanded").document("NandedCity")
                .collection("Query").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        QueryClass data = documentSnapshot.toObject(QueryClass.class);


                        String type = data.getType();
                        String subtype = data.getSubtype();
                        String money = data.getMoney();
                        String address = data.getAddress();
                        String uname = data.getUname();
                        String disc = data.getDisc();
                        String note = data.getNote();
                        String contact = data.getContact();
                        String whatsapp = data.getWhatsapp();
                        String contactime = data.getContactime();
                        image = data.getImage();


                        binding.typetext.setText(type);
                        binding.subtype.setText(subtype);
                        binding.moneyedittext.setText(money);
                        binding.addressedittext.setText(address);
                        binding.note.setText(note);
                        binding.uname.setText(uname);
                        binding.disc.setText(disc);
                        binding.querycontact.setText(contact);
                        binding.querywhatapp.setText(whatsapp);
                        binding.contactime.setText(contactime);


                        if (!image.equals("No")) {
                            binding.queryimage.setVisibility(View.VISIBLE);
                            Picasso.get().load(image).placeholder(R.drawable.placehald).into(binding.queryimage);
                        } else {
                            binding.queryimage.setVisibility(View.GONE);
                        }
                    }
                });

        binding.querytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object data = adapterView.getItemAtPosition(i);

                if (data.toString().equals("किरायाने देणे (Rent)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("किरायाने देणे (Rent)");

                    binding.whatyouwanttext.setText("तुम्हाला काय किरायाने द्यायचे आहे ? *");
                    binding.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर");
                    binding.moneytext.setText("महिनेवारी भाडे किती असेल ?");
                    binding.moneyedittext.setHint("महिनेवारी भाडे टाका");


                } else if (data.toString().equals("किरायाने पाहिजे (Rent)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("किरायाने पाहिजे (Rent)");

                    binding.whatyouwanttext.setText("तुम्हाला काय किरायाने पाहिजे आहे ? *");
                    binding.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर");
                    binding.moneytext.setText("किरायाने घेण्याची किंमत किती असायला पाहिजे ?");
                    binding.moneyedittext.setHint("किरायाने घेण्याची किंमत टाका");

                } else if (data.toString().equals("विकत पाहिजे (Buy)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("विकत पाहिजे (Buy)");

                    binding.whatyouwanttext.setText("तुम्हाला काय विकत पाहिजे आहे ? *");
                    binding.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर");
                    binding.moneytext.setText("विकत घेण्याची किंमत किती असायला पाहिजे ?");
                    binding.moneyedittext.setHint("विकत घेण्याची किंमत टाका");

                } else if (data.toString().equals("विक्री आहे (Sell)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("विक्री आहे (Sell)");

                    binding.whatyouwanttext.setText("तुमच्याकडे काय विक्री आहे ? *");
                    binding.subtype.setHint("फ्लॅट/रूम/शटर/घर/जागा/वाहन इतर");
                    binding.moneytext.setText("विक्रीची किंमत किती आहे ?");
                    binding.moneyedittext.setHint("विक्रीची किंमत टाका");

                } else if (data.toString().equals("नोकरी आहे (Job)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("नोकरी आहे (Job)");

                    binding.whatyouwanttext.setText("तुमच्याकडे कोणती नोकरी उपलब्ध आहे ? *");
                    binding.subtype.setHint("तुमच्याकडे कोणती नोकरी उपलब्ध आहे");
                    binding.moneytext.setText("नोकरीसाठी महिनेवारी पगार किती देत आहात ?");
                    binding.moneyedittext.setHint("महिनेवारी पगार टाका");

                } else if (data.toString().equals("नोकरी पाहिजे (Job)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("नोकरी पाहिजे (Job)");

                    binding.whatyouwanttext.setText("तुम्हाला कोणती नोकरी पाहिजे ? *");
                    binding.subtype.setHint("तुम्हाला कोणती नोकरी पाहिजे");
                    binding.moneytext.setText("नोकरीसाठी महिनेवारी पगार किती असायला पाहिजे ?");
                    binding.moneyedittext.setHint("पगार टाका");

                } else if (data.toString().equals("कामासाठी व्यक्ती पाहिजे (Need)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("कामासाठी व्यक्ती पाहिजे (Need)");

                    binding.whatyouwanttext.setText("तुम्हाला कोणत्या कामासाठी व्यक्ती पाहिजे ? *");
                    binding.subtype.setHint("तुम्हाला कोणत्या कामासाठी व्यक्ती पाहिजे");
                    binding.moneytext.setText("कामासाठी महिनेवारी पगार किती देत आहात ?");
                    binding.moneyedittext.setHint("पगार टाका");

                } else if (data.toString().equals("वरील पैकी वेगळे (Other)")) {

                    binding.typetext.setVisibility(View.GONE);
                    binding.typetext.setText("वरील पैकी वेगळे (Other)");

                    binding.whatyouwanttext.setText("तुम्हाला दुसरे काय करायचे आहे ? *");
                    binding.subtype.setHint("तुम्हाला दुसरे काय करायचे आहे");
                    binding.moneytext.setText("पैशाविषयी माहिती लिहा");
                    binding.moneyedittext.setHint("पैशाविषयी माहिती लिहा");

                } else if (data.toString().equals("")) {

                    binding.typetext.setVisibility(View.VISIBLE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        binding.uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditQueryActivity.this)
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

        binding.submitqerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                String disc = binding.disc.getText().toString();
                String contact = binding.querycontact.getText().toString();
                String whatsapp = binding.querywhatapp.getText().toString();
                String cotactime = binding.contactime.getText().toString();
                String subtype = binding.subtype.getText().toString();
                String money = binding.moneyedittext.getText().toString();
                String address = binding.addressedittext.getText().toString();
                String note = binding.note.getText().toString();
                String uname = binding.uname.getText().toString();
                String selectedtype = binding.typetext.getText().toString();


                if (!subtype.isEmpty() && !contact.isEmpty()) {
                    String userid = FirebaseAuth.getInstance().getUid();

                    StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Nanded")
                            .child(FirebaseAuth.getInstance().getUid()).child("Query").child(ts);
                    CollectionReference queryCollectionRef = FirebaseFirestore.getInstance().collection("Nanded")
                            .document("NandedCity").collection("Query");
                    CollectionReference ownCollectionRef = FirebaseFirestore.getInstance().collection("OwnData")
                            .document(userid).collection("Query");


                    if (image11 != null) {
                        ImageFolder.putFile(image11)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        ImageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                String uri1 = uri.toString();

                                                QueryClass cmidClass = new QueryClass(selectedtype, subtype, money, address, disc, note, uname, contact, whatsapp, cotactime, uri1, userid, new Date().getTime());
                                                queryCollectionRef.document(id).set(cmidClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        OwnQueryClass ownQueryClass = new OwnQueryClass(id, new Date().getTime());
                                                        ownCollectionRef.document(id).set(ownQueryClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                dialog.dismiss();
                                                                finish();
                                                                Toast.makeText(EditQueryActivity.this, "Query Uploaded Successfully", Toast.LENGTH_SHORT).show();


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
                                        Toast.makeText(EditQueryActivity.this, "fail", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }
                                });
                    } else if (image11 == null) {


                        QueryClass cmidClass = new QueryClass(selectedtype, subtype, money, address, disc, note, uname, contact, whatsapp, cotactime, image, userid, new Date().getTime());
                        queryCollectionRef.document(id).set(cmidClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                OwnQueryClass ownQueryClass = new OwnQueryClass(id, new Date().getTime());
                                ownCollectionRef.document(id).set(ownQueryClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialog.dismiss();
                                        finish();
                                        Toast.makeText(EditQueryActivity.this, "Query Uploaded Successfully", Toast.LENGTH_SHORT).show();


                                    }
                                });

                            }
                        });

                    } else {
                        dialog.dismiss();

                        Toast.makeText(EditQueryActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    if (subtype.isEmpty()) {
                        binding.subtype.setError("तुम्हाला दुसरे काय करायचे आहे");
                        dialog.dismiss();
                        Toast.makeText(EditQueryActivity.this, "तुम्हाला दुसरे काय करायचे आहे", Toast.LENGTH_SHORT).show();
                    }
                    if (contact.isEmpty()) {
                        binding.querycontact.setError("संपर्क टाका");
                        dialog.dismiss();
                        Toast.makeText(EditQueryActivity.this, "संपर्क टाका", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            binding.queryimage.setVisibility(View.VISIBLE);

            binding.queryimage.setImageURI(data.getData());
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