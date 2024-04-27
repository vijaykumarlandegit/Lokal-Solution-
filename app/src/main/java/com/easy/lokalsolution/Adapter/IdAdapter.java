package com.easy.lokalsolution.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.lokalsolution.Activity.ComIdDataShowActivity;
import com.easy.lokalsolution.Class.IdClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.IdsampleBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IdAdapter extends RecyclerView.Adapter<IdAdapter.ViewHolder> {

    Context context;
    ArrayList<IdClass> list;

    public IdAdapter(Context context, ArrayList<IdClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.idsample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IdAdapter.ViewHolder holder, int position) {
        IdClass snapshot = list.get(position);

        String id = snapshot.getId();
        String type = snapshot.getType();
        String coursename = snapshot.getCname();
        String exptime = snapshot.getExptime();
        String shopname = snapshot.getSname();
        String shopaddress = snapshot.getSaddress();
        String starttime = snapshot.getCustomtime();
        String moredetails = snapshot.getMore();
        String umname = snapshot.getUname();
        String ucontact = snapshot.getUcontact();
        String uwhatsapp = snapshot.getUwhatsapp();
        String image = snapshot.getImage();

        holder.binding.operatorname.setText(umname);
        holder.binding.type.setText(type);
        holder.binding.moredetails.setText(moredetails);
        holder.binding.experitext.setText(exptime);

        if (!image.equals("No")) {
            holder.binding.openimage.setVisibility(View.VISIBLE);
            holder.binding.imagecard.setVisibility(View.GONE);
            Picasso.get().load(image).into(holder.binding.image);

        } else {
            holder.binding.openimage.setVisibility(View.GONE);
            holder.binding.imagecard.setVisibility(View.GONE);

        }
        holder.binding.openimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.imagecard.setVisibility(View.VISIBLE);
            }
        }); holder.binding.imagecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.imagecard.setVisibility(View.GONE);
            }
        });
        if (shopname.equals("!@#$%")&&shopaddress.equals("!@#$%")) {
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

        if (!uwhatsapp.isEmpty()) {
            holder.binding.cmswhatsapp.setVisibility(View.VISIBLE);

            holder.binding.cmswhatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://wa.me/+91" + uwhatsapp + "?text= Hi is anyone available?"));
                    context.startActivity(intent);
                }
            });

        }
        else {
            holder.binding.cmswhatsapp.setVisibility(View.GONE);
        }
        holder.binding.cmscontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + ucontact));
                context.startActivity(intent);
            }
        });

        holder.binding.mainidshowlayout.setOnClickListener(new View.OnClickListener() {
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
        IdsampleBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = IdsampleBinding.bind(itemView);
        }
    }
}
