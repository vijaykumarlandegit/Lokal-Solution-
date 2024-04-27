package com.easy.lokalsolution.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.easy.lokalsolution.Class.IdClass;
import com.easy.lokalsolution.databinding.ActivityComIdDataShowBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class ComIdDataShowActivity extends AppCompatActivity {

    ActivityComIdDataShowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityComIdDataShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String id=getIntent().getStringExtra("id");


        FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        IdClass snapshot=documentSnapshot.toObject(IdClass.class);


                        String type=snapshot.getType();
                        String coursename=snapshot.getCname();
                        String coursedetails=snapshot.getCdetail();
                        String exptime=snapshot.getExptime();
                        String shopname=snapshot.getSname();
                        String shopaddress=snapshot.getSaddress();
                        String starttime=snapshot.getCustomtime();
                        String moredetails=snapshot.getMore();
                        String umname=snapshot.getUname();
                        String ucontact=snapshot.getUcontact();
                        String uwhatsapp=snapshot.getUwhatsapp();
                        String uemail=snapshot.getUemail();
                        String image=snapshot.getImage();



                        binding.idname.setText(type);
                        binding.experiseperiod.setText(exptime);
                        binding.oname.setText(umname);
                        binding.contact.setText(ucontact);

                        if (!image.equals("No")){
                            binding.extraimage.setVisibility(View.VISIBLE);

                            Picasso.get().load(image).into(binding.extraimage);

                        }else {
                            binding.extraimage.setVisibility(View.GONE);
                        }

                        if (shopname.equals("!@#$%")&&shopaddress.equals("!@#$%")){
                            binding.shopview.setVisibility(View.GONE);
                        }else {
                            binding.shopview.setVisibility(View.VISIBLE);

                            binding.shopname.setText("Shop Name : "+shopname);
                            binding.shopaddress.setText("Shop Address : "+shopaddress);
                        }

                        if (starttime.equals("!@#$%")){
                            binding.customtimeview.setVisibility(View.GONE);
                            binding.anytimetext.setVisibility(View.VISIBLE);
                        }else {
                            binding.customtimeview.setVisibility(View.VISIBLE);
                            binding.anytimetext.setVisibility(View.GONE);
                            binding.customtime.setText(starttime);
                        }

                        if (coursename.equals("!@#$%")&&coursedetails.equals("!@#$%")){
                            binding.courseview.setVisibility(View.GONE);
                        }else {
                            binding.courseview.setVisibility(View.VISIBLE);
                            binding.couresename.setText(coursename);
                            binding.coursedetaills.setText(coursedetails);
                        }
                        if (moredetails.isEmpty()){
                            binding.moreview.setVisibility(View.GONE);
                        }else {
                            binding.moreview.setVisibility(View.VISIBLE);
                            binding.moreaboutwork.setText(moredetails);

                        }

                        if (uwhatsapp.isEmpty()){
                            binding.whatsappview.setVisibility(View.GONE);
                        }else {
                            binding.whatsappview.setVisibility(View.VISIBLE);
                            binding.whatsapp.setText(uwhatsapp);

                        }
                         if (uemail.isEmpty()){
                            binding.emailview.setVisibility(View.GONE);
                        }else {
                            binding.emailview.setVisibility(View.VISIBLE);
                            binding.mail.setText(uemail);

                        }


                        if (!uwhatsapp.isEmpty()){
                            binding.cmswhatsapp.setVisibility(View.VISIBLE);

                            binding.cmswhatsapp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("https://wa.me/+91" + uwhatsapp + "?text= Hi is anyone there ?"));
                                    startActivity(intent);
                                }
                            });

                        }else {
                            binding.cmswhatsapp.setVisibility(View.GONE);
                        }
                        binding.cmscontact.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + ucontact));
                                 startActivity(intent);
                            }
                        });

                    }
                });



    }
}