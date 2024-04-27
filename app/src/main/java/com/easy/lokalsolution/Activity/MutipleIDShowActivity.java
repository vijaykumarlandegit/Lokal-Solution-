package com.easy.lokalsolution.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easy.lokalsolution.Adapter.IdAdapter;
import com.easy.lokalsolution.Class.IdClass;
import com.easy.lokalsolution.databinding.ActivityMutipleIdshowBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MutipleIDShowActivity extends AppCompatActivity {

    ActivityMutipleIdshowBinding binding;
    ArrayList<IdClass> list1;
    IdAdapter idAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMutipleIdshowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list1 = new ArrayList<>();
        idAdapter = new IdAdapter(MutipleIDShowActivity.this, list1);
        binding.carmechrecyview.setAdapter(idAdapter);

        linearLayoutManager = new LinearLayoutManager(MutipleIDShowActivity.this);
        binding.carmechrecyview.setLayoutManager(linearLayoutManager);


        String type = getIntent().getStringExtra("type");



        if (type.equals("11")) {
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();

            binding.typename.setText("Mechanic");
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Mechanic")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();

                        }
                    });


        } else if (type.equals("12")) {
            binding.typename.setText("Plumber");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Plumber")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");

                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        } else if (type.equals("13")) {
            binding.typename.setText("Eletrician");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Electrician")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        } else if (type.equals("14")) {
            binding.typename.setText("Electronic Technician");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Electronic Technician")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        } else if (type.equals("15")) {
            binding.typename.setText("Painter");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Painter")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");

                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        } else if (type.equals("16")) {
            binding.typename.setText("Carpenter");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Carpenter")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        } else if (type.equals("17")) {
            binding.typename.setText("Photographer");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Photographer")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        } else if (type.equals("18")) {
            binding.typename.setText("Home Shifting");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Home Shifting")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        } else if (type.equals("19")) {
            binding.typename.setText("Event Management");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Event Management")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        }else if (type.equals("20")) {
            binding.typename.setText("Other");
            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Id").whereEqualTo("type", "Other")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            list1.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                IdClass data = snapshot.toObject(IdClass.class);
                                list1.add(data);
                            }
                            idAdapter.notifyDataSetChanged();
                            binding.idcount.setText(list1.size()+" IDs");
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }
                    });
        }
        binding.cmcreatid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MutipleIDShowActivity.this, AddIdActivity.class);
                startActivity(intent);
            }
        }); binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}