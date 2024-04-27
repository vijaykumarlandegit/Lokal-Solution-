package com.easy.lokalsolution.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.easy.lokalsolution.Adapter.NewsAdapter;
import com.easy.lokalsolution.Class.NewsClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.FragmentFirstBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FirstFragment extends Fragment {
    FragmentFirstBinding binding;
    ArrayList<NewsClass> list;
    NewsAdapter newsAdapter;

    public FirstFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        list = new ArrayList<>();
        newsAdapter = new NewsAdapter(list, getContext());
        binding.newrec.setAdapter(newsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.newrec.setLayoutManager(layoutManager);


        binding.shimmer.setVisibility(View.VISIBLE);
        binding.shimmer.startShimmer();
        FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        list.clear();
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                            NewsClass data = snapshot.toObject(NewsClass.class);
                            list.add(data);
                        }
                        newsAdapter.notifyDataSetChanged();
                        binding.shimmer.setVisibility(View.GONE);
                        binding.shimmer.stopShimmer();
                    }
                });

        binding.showrentswip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });
                binding.showrentswip.setRefreshing(false);
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
                        .document("NandedCity").collection("News")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "All", Toast.LENGTH_SHORT).show();

            }
        });
        binding.newsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","News")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "news", Toast.LENGTH_SHORT).show();

            }
        });
        binding.addvetsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","Advertisement")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Advertisement", Toast.LENGTH_SHORT).show();
            }
        });
        binding.storysort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","Story")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Story", Toast.LENGTH_SHORT).show();            }
        });
        binding.poertysort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","Poetry")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Poetry", Toast.LENGTH_SHORT).show();            }
        });
        binding.announsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","Announcement")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Announcement", Toast.LENGTH_SHORT).show();            }
        });
        binding.puzzlesort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","Puzzle")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Puzzle", Toast.LENGTH_SHORT).show();            }
        });
        binding.gksort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","GK Question")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "GK Question", Toast.LENGTH_SHORT).show();            }
        });
        binding.othersort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Nanded")
                        .document("NandedCity").collection("News")
                        .whereEqualTo("type","Other")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                list.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    NewsClass data = snapshot.toObject(NewsClass.class);
                                    list.add(data);
                                }
                                newsAdapter.notifyDataSetChanged();
                                binding.shimmer.setVisibility(View.GONE);
                                binding.shimmer.stopShimmer();
                            }
                        });

                binding.sortboxview.setVisibility(View.GONE);
                binding.justview.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Other", Toast.LENGTH_SHORT).show();            }
        });



        return binding.getRoot();
    }
}