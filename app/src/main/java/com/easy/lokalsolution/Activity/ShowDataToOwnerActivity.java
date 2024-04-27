package com.easy.lokalsolution.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.easy.lokalsolution.Adapter.OwnIdAdapter;
import com.easy.lokalsolution.Adapter.OwnNewsAdapter;
import com.easy.lokalsolution.Adapter.OwnQueryAdapter;
import com.easy.lokalsolution.Class.OwnIdClass;
import com.easy.lokalsolution.Class.OwnNewsClass;
import com.easy.lokalsolution.Class.OwnQueryClass;
import com.easy.lokalsolution.databinding.ActivityShowDataToOwnerBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowDataToOwnerActivity extends AppCompatActivity {

    ActivityShowDataToOwnerBinding binding;
    ArrayList<OwnNewsClass> list1;
    OwnNewsAdapter ownNewsAdapter;
    ArrayList<OwnQueryClass> list2;
    OwnQueryAdapter ownQueryAdapter;
    ArrayList<OwnIdClass> list3;
    OwnIdAdapter ownIdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowDataToOwnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String type = getIntent().getStringExtra("type");


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (type.equals("11")) {

            list1 = new ArrayList<>();
            ownNewsAdapter = new OwnNewsAdapter(list1, ShowDataToOwnerActivity.this);
            binding.showdatarec.setAdapter(ownNewsAdapter);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(ShowDataToOwnerActivity.this);
            binding.showdatarec.setLayoutManager(layoutManager1);

            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("OwnData")
                    .document(FirebaseAuth.getInstance().getUid()).collection("News")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            if (!queryDocumentSnapshots.isEmpty()){
                                list1.clear();
                                for (DocumentSnapshot snapshot1 : queryDocumentSnapshots.getDocuments()) {
                                    OwnNewsClass data1= snapshot1.toObject(OwnNewsClass.class);
                                    list1.add(data1);
                                }
                                ownNewsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }else {
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();

                                Toast.makeText(ShowDataToOwnerActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

            binding.swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    binding.shimmer.setVisibility(View.VISIBLE);
                    binding.shimmer.startShimmer();
                    FirebaseFirestore.getInstance().collection("OwnData")
                            .document(FirebaseAuth.getInstance().getUid()).collection("News")
                            .orderBy("time", Query.Direction.DESCENDING).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                    if (!queryDocumentSnapshots.isEmpty()){
                                        list1.clear();
                                        for (DocumentSnapshot snapshot1 : queryDocumentSnapshots.getDocuments()) {
                                            OwnNewsClass data1= snapshot1.toObject(OwnNewsClass.class);
                                            list1.add(data1);
                                        }
                                        ownNewsAdapter.notifyDataSetChanged();
                                        binding.shimmer.setVisibility(View.GONE);
                                        binding.shimmer.stopShimmer();
                                    }else {
                                        binding.shimmer.setVisibility(View.GONE);
                                        binding.shimmer.stopShimmer();

                                        Toast.makeText(ShowDataToOwnerActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    binding.swiprefresh.setRefreshing(false);
                    Toast.makeText(ShowDataToOwnerActivity.this, "Data refresh", Toast.LENGTH_SHORT).show();
                }
            });
            binding.newpost.setVisibility(View.VISIBLE);
            binding.newid.setVisibility(View.GONE);
            binding.newquery.setVisibility(View.GONE);
            binding.newpost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ShowDataToOwnerActivity.this, AddNewsActivity.class);
                    startActivity(intent);
                }
            });
        } else if (type.equals("12")) {

            list2 = new ArrayList<>();
            ownQueryAdapter = new OwnQueryAdapter(list2, ShowDataToOwnerActivity.this);
            binding.showdatarec.setAdapter(ownQueryAdapter);
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(ShowDataToOwnerActivity.this);
            binding.showdatarec.setLayoutManager(layoutManager2);

            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("OwnData")
                    .document(FirebaseAuth.getInstance().getUid()).collection("Query")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (!queryDocumentSnapshots.isEmpty()){
                                list2.clear();
                                for (DocumentSnapshot snapshot2 : queryDocumentSnapshots.getDocuments()) {
                                    OwnQueryClass data2 = snapshot2.toObject(OwnQueryClass.class);
                                    list2.add(data2);
                                }
                                ownQueryAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }else {
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                                Toast.makeText(ShowDataToOwnerActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

            binding.swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    binding.shimmer.setVisibility(View.VISIBLE);
                    binding.shimmer.startShimmer();
                    FirebaseFirestore.getInstance().collection("OwnData")
                            .document(FirebaseAuth.getInstance().getUid()).collection("Query")
                            .orderBy("time", Query.Direction.DESCENDING).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                    if (!queryDocumentSnapshots.isEmpty()){
                                        list2.clear();
                                        for (DocumentSnapshot snapshot2 : queryDocumentSnapshots.getDocuments()) {
                                            OwnQueryClass data2 = snapshot2.toObject(OwnQueryClass.class);
                                            list2.add(data2);
                                        }
                                        ownQueryAdapter.notifyDataSetChanged();
                                        binding.shimmer.setVisibility(View.GONE);
                                        binding.shimmer.stopShimmer();
                                    }else {
                                        binding.shimmer.setVisibility(View.GONE);
                                        binding.shimmer.stopShimmer();
                                        Toast.makeText(ShowDataToOwnerActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                    binding.swiprefresh.setRefreshing(false);
                    Toast.makeText(ShowDataToOwnerActivity.this, "Data refresh", Toast.LENGTH_SHORT).show();

                }
            });
            binding.newpost.setVisibility(View.GONE);
            binding.newid.setVisibility(View.GONE);
            binding.newquery.setVisibility(View.VISIBLE);
            binding.newquery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ShowDataToOwnerActivity.this, AddQueryActivity.class);
                    startActivity(intent);
                }
            });
        } else if (type.equals("13")) {

            list3 = new ArrayList<>();
            ownIdAdapter = new OwnIdAdapter(list3, ShowDataToOwnerActivity.this);
            binding.showdatarec.setAdapter(ownIdAdapter);
            LinearLayoutManager layoutManager3 = new LinearLayoutManager(ShowDataToOwnerActivity.this);
            binding.showdatarec.setLayoutManager(layoutManager3);



            binding.shimmer.setVisibility(View.VISIBLE);
            binding.shimmer.startShimmer();
            FirebaseFirestore.getInstance().collection("OwnData")
                    .document(FirebaseAuth.getInstance().getUid()).collection("Id")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            if (!queryDocumentSnapshots.isEmpty()){
                                list3.clear();
                                for (DocumentSnapshot snapshot3 : queryDocumentSnapshots.getDocuments()) {
                                    OwnIdClass data3 = snapshot3.toObject(OwnIdClass.class);
                                    list3.add(data3);
                                }
                                ownIdAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }else {
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                                Toast.makeText(ShowDataToOwnerActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

            binding.swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    binding.shimmer.setVisibility(View.VISIBLE);
                    binding.shimmer.startShimmer();

                    FirebaseFirestore.getInstance().collection("OwnData")
                            .document(FirebaseAuth.getInstance().getUid()).collection("Id")
                            .orderBy("time", Query.Direction.DESCENDING).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                    if (!queryDocumentSnapshots.isEmpty()){
                                        list3.clear();
                                        for (DocumentSnapshot snapshot3 : queryDocumentSnapshots.getDocuments()) {
                                            OwnIdClass data3 = snapshot3.toObject(OwnIdClass.class);
                                            list3.add(data3);
                                        }
                                        ownIdAdapter.notifyDataSetChanged();
                                        binding.shimmer.setVisibility(View.GONE);
                                        binding.shimmer.stopShimmer();
                                    }else {
                                        binding.shimmer.setVisibility(View.GONE);
                                        binding.shimmer.stopShimmer();
                                        Toast.makeText(ShowDataToOwnerActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                    binding.swiprefresh.setRefreshing(false);
                    Toast.makeText(ShowDataToOwnerActivity.this, "Data refresh", Toast.LENGTH_SHORT).show();


                }
            });


            binding.newpost.setVisibility(View.GONE);
            binding.newid.setVisibility(View.VISIBLE);
            binding.newquery.setVisibility(View.GONE);
            binding.newid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ShowDataToOwnerActivity.this, AddIdActivity.class);
                    startActivity(intent);

                }
            });

        }
    }
}