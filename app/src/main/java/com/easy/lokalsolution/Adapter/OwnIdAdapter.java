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

import com.easy.lokalsolution.Activity.ComIdDataShowActivity;
import com.easy.lokalsolution.Activity.EditIdActivity;
import com.easy.lokalsolution.Class.IdClass;
import com.easy.lokalsolution.Class.OwnIdClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.OwnidsampleBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OwnIdAdapter extends RecyclerView.Adapter<OwnIdAdapter.ViewHolder> {

    Context context;
    ArrayList<OwnIdClass> list;
    int in;

    ProgressDialog dialog;

    public OwnIdAdapter(ArrayList<OwnIdClass> list, Context context) {
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
    public OwnIdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ownidsample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnIdAdapter.ViewHolder holder, int position) {
        OwnIdClass data = list.get(position);
        String id = data.getId();
        Long time = data.getTime();
        String tt = String.valueOf(time);
        holder.binding.showtimetext.setText(getTime(tt, time));

        FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        IdClass snapshot = documentSnapshot.toObject(IdClass.class);

                        String type = snapshot.getType();
                        String coursename = snapshot.getCname();
                        String exptime = snapshot.getExptime();
                        String shopname = snapshot.getSname();
                        String shopaddress = snapshot.getSaddress();
                        String starttime = snapshot.getCustomtime();
                        String moredetails = snapshot.getMore();
                        String umname = snapshot.getUname();
                        String image = snapshot.getImage();
                        holder.binding.type.setText(type);
                        holder.binding.moredetails.setText(moredetails);
                        holder.binding.operatorname.setText(umname);
                        holder.binding.experitext.setText(exptime);


                        if (!image.equals("No")) {
                            holder.binding.imagecard.setVisibility(View.VISIBLE);
                            Picasso.get().load(image).placeholder(R.drawable.images).into(holder.binding.cmspic);
                        } else {
                            holder.binding.imagecard.setVisibility(View.GONE);

                        }
                        if (shopname.equals("!@#$%") && shopaddress.equals("!@#$%")) {
                            holder.binding.shopview.setVisibility(View.GONE);
                            holder.binding.shopaddressview.setVisibility(View.GONE);
                        } else {
                            holder.binding.shopview.setVisibility(View.VISIBLE);
                            holder.binding.shopaddressview.setVisibility(View.VISIBLE);

                            holder.binding.shoptext.setText(shopname);
                            holder.binding.shopaddresstext.setText(shopaddress);

                        }

                        if (starttime.equals("!@#$%")) {
                            holder.binding.owntimeview.setVisibility(View.GONE);
                            holder.binding.anytimeview.setVisibility(View.VISIBLE);
                        } else {
                            holder.binding.owntimeview.setVisibility(View.VISIBLE);
                            holder.binding.anytimeview.setVisibility(View.GONE);
                            holder.binding.customtime.setText(starttime);
                        }

                        if (coursename.equals("!@#$%")) {
                            holder.binding.coursview.setVisibility(View.GONE);
                        } else {
                            holder.binding.coursview.setVisibility(View.VISIBLE);
                            holder.binding.coursetext.setText(coursename);

                        }


                    }
                });

        holder.binding.editview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit Your ID", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, EditIdActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait....");
        holder.binding.deleteview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Delete ID");
                builder.setMessage("Are you sure you want to delete your Id");
                builder.setIcon(R.drawable.warna);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.show();
                        FirebaseFirestore.getInstance().collection("Nanded")
                                .document("NandedCity").collection("Id").document(id).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        FirebaseFirestore.getInstance().collection("OwnData")
                                                .document(FirebaseAuth.getInstance().getUid()).collection("Id").document(id).delete();
                                        dialogInterface.dismiss();
                                        dialog.dismiss();
                                        Toast.makeText(context, "ID Deleted Successfully", Toast.LENGTH_SHORT).show();
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

        holder.binding.viewview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ComIdDataShowActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        OwnidsampleBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = OwnidsampleBinding.bind(itemView);

        }
    }

}
