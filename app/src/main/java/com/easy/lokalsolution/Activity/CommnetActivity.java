package com.easy.lokalsolution.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.easy.lokalsolution.Adapter.CommentAdapter;
import com.easy.lokalsolution.Class.CommentClass;
import com.easy.lokalsolution.databinding.ActivityCommnetBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class CommnetActivity extends AppCompatActivity {
    ActivityCommnetBinding binding;

    ArrayList<CommentClass> list;
    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommnetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Comment adding....");

        String id = getIntent().getStringExtra("id");

        list=new ArrayList<>();

        commentAdapter=new CommentAdapter(this,list);
        binding.commentRec.setAdapter(commentAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.commentRec.setLayoutManager(layoutManager);



        binding.shimmer.setVisibility(View.VISIBLE);
        binding.shimmer.startShimmer();

        FirebaseFirestore.getInstance().collection("Comment").document(id).collection("Comment")
                .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()){
                            list.clear();

                            for (DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments()){
                                CommentClass data=snapshot.toObject(CommentClass.class);
                                list.add(data);
                            }
                            commentAdapter.notifyDataSetChanged();
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                        }else {
                            binding.shimmer.setVisibility(View.GONE);
                            binding.shimmer.stopShimmer();
                            Toast.makeText(CommnetActivity.this, "No Comments Yet", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        binding.showrentswip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.shimmer.setVisibility(View.VISIBLE);
                binding.shimmer.startShimmer();
                FirebaseFirestore.getInstance().collection("Comment").document(id).collection("Comment")
                        .orderBy("time", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()){
                                    list.clear();

                                    for (DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments()){
                                        CommentClass data=snapshot.toObject(CommentClass.class);
                                        list.add(data);
                                    }
                                    commentAdapter.notifyDataSetChanged();
                                    binding.shimmer.setVisibility(View.GONE);
                                    binding.shimmer.stopShimmer();
                                }else {
                                    binding.shimmer.setVisibility(View.GONE);
                                    binding.shimmer.stopShimmer();
                                    Toast.makeText(CommnetActivity.this, "No Comments Yet", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                binding.showrentswip.setRefreshing(false);
                Toast.makeText(CommnetActivity.this, "Data refresh", Toast.LENGTH_SHORT).show();

            }
        });


        binding.commentSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String comment = binding.commentText.getText().toString();
                if (!comment.isEmpty()) {
                    dialog.show();
                    CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Comment").document(id)
                            .collection("Comment");
                    String comid = collectionReference.document().getId();

                    CommentClass commentClass = new CommentClass(id, FirebaseAuth.getInstance().getUid(), comid, comment, new Date().getTime());

                    collectionReference.document(comid).set(commentClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            binding.commentText.setText("");
                            dialog.dismiss();
                            Toast.makeText(CommnetActivity.this, "Comment Addend Successfully, Please refresh", Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    Toast.makeText(CommnetActivity.this, "Please enter comment", Toast.LENGTH_SHORT).show();
                    binding.commentText.setError("Enter Comment");
                }
            }
        });


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}