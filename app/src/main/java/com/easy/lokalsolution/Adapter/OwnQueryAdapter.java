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

import com.easy.lokalsolution.Activity.EditQueryActivity;
import com.easy.lokalsolution.Class.OwnQueryClass;
import com.easy.lokalsolution.Class.QueryClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.OwnquerysampleBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OwnQueryAdapter extends RecyclerView.Adapter<OwnQueryAdapter.ViewHolder> {

    Context context;
    ArrayList<OwnQueryClass> list;
    ProgressDialog dialog;

    int in;
    public OwnQueryAdapter(ArrayList<OwnQueryClass> list, Context context) {
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
    public OwnQueryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ownquerysample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnQueryAdapter.ViewHolder holder, int position) {
        OwnQueryClass data=list.get(position);
        String id= data.getId();
        Long time= data.getTime();
        String tt= String.valueOf(time);
        holder.binding.showtimetext.setText(getTime(tt,time));



        FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        QueryClass data1=snapshot.toObject(QueryClass.class);
                        String type=data1.getType();
                        String subtype=data1.getSubtype();
                        String disc=data1.getDisc();
                        String image=data1.getImage();


                        holder.binding.type.setText(type);
                        holder.binding.subtype.setText(subtype);
                        holder.binding.squerydisc.setText(disc);

                        if (!image.equals("No")){
                            holder.binding.imagecard.setVisibility(View.VISIBLE);
                            Picasso.get().load(image).into(holder.binding.squeryimage);

                        }else {
                            holder.binding.imagecard.setVisibility(View.GONE);

                        }
                    }
                });
        holder.binding.editview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit Your Query", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context, EditQueryActivity.class);
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
                builder.setTitle("Delete Query");
                builder.setMessage("Are you sure you want to delete your query");
                builder.setIcon(R.drawable.warna);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.show();
                        FirebaseFirestore.getInstance().collection("Nanded")
                                .document("NandedCity").collection("Query").document(id).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        FirebaseFirestore.getInstance().collection("OwnData")
                                                .document(FirebaseAuth.getInstance().getUid()).collection("Query").document(id).delete();
                                        dialogInterface.dismiss();
                                        dialog.dismiss();
                                        Toast.makeText(context, "Query Deleted Successfully", Toast.LENGTH_SHORT).show();

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

    public class ViewHolder extends RecyclerView.ViewHolder{
        OwnquerysampleBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=OwnquerysampleBinding.bind(itemView);
        }
    }
}
