package com.easy.lokalsolution.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.easy.lokalsolution.Class.QueryClass;
import com.easy.lokalsolution.Adapter.QueryAdapter;
import com.easy.lokalsolution.databinding.FragmentSecondBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class SecondFragment extends Fragment {

    public SecondFragment() {

    }

    FragmentSecondBinding binding;
    ArrayList<QueryClass> list = new ArrayList<>();
    QueryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        adapter = new QueryAdapter(getContext(), list);
        binding.queryrec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.queryrec.setLayoutManager(layoutManager);


        binding.shimmer.setVisibility(View.VISIBLE);
        binding.shimmer.startShimmer();
        FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        list.clear();
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                            QueryClass data = snapshot.toObject(QueryClass.class);
                            list.add(data);
                        }
                        adapter.notifyDataSetChanged();
                        binding.shimmer.setVisibility(View.GONE);
                        binding.shimmer.stopShimmer();
                    }
                });

        binding.swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.swiprefresh.setRefreshing(false);
                Toast.makeText(getContext(), "Data refresh", Toast.LENGTH_SHORT).show();

            }
        });

        binding.sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.sortboxview.setVisibility(View.VISIBLE);
                binding.justview.setVisibility(View.VISIBLE);
            }
        });
        binding.justview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
            }
        });
        binding.allsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });


                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "All", Toast.LENGTH_SHORT).show();

            }
        });
        binding.rentout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","किरायाने देणे (Rent)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "किरायाने देणे", Toast.LENGTH_SHORT).show();

            }
        });  binding.rentin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","किरायाने पाहिजे (Rent)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "किरायाने पाहिजे", Toast.LENGTH_SHORT).show();

            }
        });
        binding.needbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","विकत पाहिजे (Buy)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "विकत पाहिजे", Toast.LENGTH_SHORT).show();
            }
        });
        binding.sellout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","विक्री आहे (Sell)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "विक्री आहे", Toast.LENGTH_SHORT).show();
            }
        });
        binding.jobsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","नोकरी आहे (Job)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "नोकरी आहे", Toast.LENGTH_SHORT).show();
            }
        });
        binding.needjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","नोकरी पाहिजे (Job)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });
                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "नोकरी पाहिजे", Toast.LENGTH_SHORT).show();
            }
        });  binding.needperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","कामासाठी व्यक्ती पाहिजे (Need)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });
                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "कामासाठी व्यक्ती पाहिजे", Toast.LENGTH_SHORT).show();
            }
        });
        binding.othersort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("Query")
                        .whereEqualTo("type","वरील पैकी वेगळे (Other)")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    QueryClass data = snapshot.toObject(QueryClass.class);
                                    list.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "other", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}