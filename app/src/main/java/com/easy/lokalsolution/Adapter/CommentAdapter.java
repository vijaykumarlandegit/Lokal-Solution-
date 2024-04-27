package com.easy.lokalsolution.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.lokalsolution.Class.CommentClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.CommentsampleBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    Context context;
    ArrayList<CommentClass> list;

    public CommentAdapter(Context context, ArrayList<CommentClass> list) {
        this.context = context;
        this.list = list;
    }
    private String getTime(String time, Long timestamp) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(time));
        String timee = new SimpleDateFormat("dd-MM-yy hh:mm aa").format(timestamp);
        return timee;
    }
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.commentsample,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        CommentClass data=list.get(position);

        String comment=data.getComment();
        String userid=data.getUserid();
        Long time=data.getTime();

        String tt = String.valueOf(time);
        holder.binding.commTime.setText(getTime(tt, time));

        holder.binding.commComment.setText(comment);

        FirebaseFirestore.getInstance().collection("AllUserG").document(userid)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name=documentSnapshot.getString("name");
                String pic=documentSnapshot.getString("image");

                holder.binding.commUN.setText("@"+name);
                Picasso.get().load(pic).into(holder.binding.commUP);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CommentsampleBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CommentsampleBinding.bind(itemView);
        }
    }
}
