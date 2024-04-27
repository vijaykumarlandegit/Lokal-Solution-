package com.easy.lokalsolution.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.lokalsolution.Activity.EditNewsActivity;
import com.easy.lokalsolution.Class.NewsClass;
import com.easy.lokalsolution.Class.OwnNewsClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.OwnnewsampleBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OwnNewsAdapter extends RecyclerView.Adapter<OwnNewsAdapter.ViewHolder> {

    Context context;
    ArrayList<OwnNewsClass> list;
    ProgressDialog dialog;

    int in;

    public OwnNewsAdapter(ArrayList<OwnNewsClass> list, Context context) {
        this.list = list;
        this.context = context;

    }

    private String getTime(String time, Long timestamp) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(time));
        String timee = new SimpleDateFormat("dd-MM-yy hh:mm aa").format(timestamp);
        return timee;
    }

    @NonNull
    @Override
    public OwnNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ownnewsample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnNewsAdapter.ViewHolder holder, int position) {

        OwnNewsClass data = list.get(position);
        String id = data.getId();
        Long time = data.getTime();
        String tt = String.valueOf(time);
        holder.binding.showtimetext.setText(getTime(tt, time));



        FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        NewsClass data1 = snapshot.toObject(NewsClass.class);
                        String type = data1.getType();
                        String title = data1.getTitle();
                        String dis = data1.getDisc();
                        String image = data1.getImage();

                        holder.binding.type.setText(type);
                        holder.binding.title.setText(title);
                        holder.binding.disc.setText(dis);

                        if (!image.equals("No")){
                            holder.binding.imagecard.setVisibility(View.VISIBLE);
                            holder.binding.shareview.setVisibility(View.VISIBLE);
                            holder.binding.saveview.setVisibility(View.VISIBLE);
                            Picasso.get().load(image).placeholder(R.drawable.images).into(holder.binding.newsimage);
                        }else {
                            holder.binding.imagecard.setVisibility(View.GONE);
                            holder.binding.shareview.setVisibility(View.GONE);
                            holder.binding.saveview.setVisibility(View.GONE);
                        }

                    }
                });
        FirebaseFirestore.getInstance().collection("Like").document(id).collection("Like")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String count = String.valueOf(queryDocumentSnapshots.size());
                        holder.binding.likecount.setText(count);

                    }
                });
        FirebaseFirestore.getInstance().collection("Comment").document(id).collection("Comment")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String count = String.valueOf(queryDocumentSnapshots.size());
                        holder.binding.commentcount.setText(count);

                    }
                });

        holder.binding.editview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit Your Post", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context, EditNewsActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);

            }
        });
        dialog=new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait....");
        holder.binding.deleteview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Delete Post");
                builder.setMessage("Are you sure you want to delete your post");
                builder.setIcon(R.drawable.warna);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.show();
                        FirebaseFirestore.getInstance().collection("Nanded")
                                .document("NandedCity").collection("News").document(id).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        FirebaseFirestore.getInstance().collection("OwnData")
                                                .document(FirebaseAuth.getInstance().getUid()).collection("News").document(id).delete();
                                        dialogInterface.dismiss();
                                        dialog.dismiss();
                                        Toast.makeText(context, "Post Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(context, "Thanks !!!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        OwnnewsampleBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = OwnnewsampleBinding.bind(itemView);
        }
    }
}
