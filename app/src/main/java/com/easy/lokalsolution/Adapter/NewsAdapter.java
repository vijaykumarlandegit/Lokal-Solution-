package com.easy.lokalsolution.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.lokalsolution.Activity.CommnetActivity;
import com.easy.lokalsolution.Class.LikeClass;
import com.easy.lokalsolution.Class.NewsClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.NewssampleBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;
    ArrayList<NewsClass> list;
    int in;



    public NewsAdapter(ArrayList<NewsClass> list, Context context) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newssample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsClass userr = list.get(position);
        String type = userr.getType();
        String title = userr.getTitle();
        String disc = userr.getDisc();
        String id = userr.getId();
        String image = userr.getImage();
        Long time = userr.getTime();
        String tt = String.valueOf(userr.getTime());
        holder.binding.showtimetext.setText(getTime(tt, time));
        holder.binding.getnewstitle.setText(title);
        holder.binding.expandTextView.setText(disc);

        if (type.equals("Other")){
            holder.binding.type.setVisibility(View.GONE);
        }else {
            holder.binding.type.setVisibility(View.VISIBLE);
            holder.binding.type.setText(type);

        }

        if (!image.equals("No")){
            holder.binding.newsimage.setVisibility(View.VISIBLE);
            holder.binding.shareview.setVisibility(View.VISIBLE);
            holder.binding.saveview.setVisibility(View.VISIBLE);
            Picasso.get().load(image).placeholder(R.drawable.placeholder).into(holder.binding.newsimage);
        }else {
            holder.binding.newsimage.setVisibility(View.GONE);
            holder.binding.shareview.setVisibility(View.GONE);
            holder.binding.saveview.setVisibility(View.GONE);

        }



        FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot = task.getResult();

                        if (snapshot.exists()) {
                            String pic = snapshot.getString("image");
                            String name = snapshot.getString("name");

                            holder.binding.getnewsownername.setText(name);
                            Picasso.get().load(pic).placeholder(R.drawable.useraaa).into(holder.binding.getnewownerpic);

                        }

                    }
                });

        FirebaseFirestore.getInstance().collection("Like").document(id).collection("Like")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String count= String.valueOf(queryDocumentSnapshots.size());
                        holder.binding.likecount.setText(count);
                        holder.binding.unlikecount.setText(count);

                    }
                });
        FirebaseFirestore.getInstance().collection("Comment").document(id).collection("Comment")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String count= String.valueOf(queryDocumentSnapshots.size());
                        holder.binding.commentcount.setText(count);

                    }
                });

        FirebaseFirestore.getInstance().collection("Like")
                .document(id).collection("Like").document(FirebaseAuth.getInstance().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document=task.getResult();
                            if (document.exists()){
                                holder.binding.unlikeview.setVisibility(View.VISIBLE);
                                holder.binding.likeview.setVisibility(View.GONE);

                            }else {
                                holder.binding.unlikeview.setVisibility(View.GONE);
                                holder.binding.likeview.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

        holder.binding.unlikeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.unlikeview.setVisibility(View.GONE);
                holder.binding.likeview.setVisibility(View.VISIBLE);
                int ulc= Integer.parseInt(holder.binding.unlikecount.getText().toString());
                holder.binding.likecount.setText(ulc-1+"");

                FirebaseFirestore.getInstance().collection("Like").document(id)
                        .collection("Like").document(FirebaseAuth.getInstance().getUid()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Like Removed", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        holder.binding.likeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.unlikeview.setVisibility(View.VISIBLE);
                holder.binding.likeview.setVisibility(View.GONE);
                int ulc= Integer.parseInt(holder.binding.likecount.getText().toString());
                holder.binding.unlikecount.setText(ulc+1+"");

                LikeClass commentClass = new LikeClass(FirebaseAuth.getInstance().getUid(), new Date().getTime());

                FirebaseFirestore.getInstance().collection("Like").document(id)
                        .collection("Like").document(FirebaseAuth.getInstance().getUid()).set(commentClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Like Added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.binding.commentview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, "comment", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CommnetActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
        holder.binding.shareview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, "share", Toast.LENGTH_SHORT).show();

                Bitmap bitmap = ((BitmapDrawable) holder.binding.newsimage.getDrawable()).getBitmap();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("image/png");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                // .compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
                // Uri uri = Uri.parse("android.resource://your package
                //name/"+R.drawable.ic_launcher);
                Uri uri = Uri.parse(path);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "");
                context.startActivity(Intent.createChooser(shareIntent, "Share image"));
            }
        });
        holder.binding.saveview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               /* BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.binding.newsimage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                FileOutputStream outputStream = null;
                File file = Environment.getExternalStorageDirectory();
                File gimage = new File(file.getAbsolutePath() + "/Lokal Solution Downloads");
                gimage.mkdir();
                String filename = String.format("%d.jpg", System.currentTimeMillis());
                File outfile = new File(gimage, filename);

                try {
                    outputStream = new FileOutputStream(outfile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outfile));
                    Toast.makeText(context, "Image Downloaded", Toast.LENGTH_SHORT).show();
                    context.sendBroadcast(intent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
                BitmapDrawable draw = (BitmapDrawable) holder.binding.newsimage.getDrawable();
                Bitmap bitmap = draw.getBitmap();

                FileOutputStream outStream = null;
                File sdCard = Environment.getExternalStorageDirectory();
                File dir = new File(sdCard.getAbsolutePath() + "/Download");
                dir.mkdirs();
                String fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(dir, fileName);

                Toast.makeText(context, "Image Saved", Toast.LENGTH_SHORT).show();
                try {
                    outStream = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outFile));
                    context.sendBroadcast(intent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }



            }
        });


    }
    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NewssampleBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NewssampleBinding.bind(itemView);
        }
    }
}
