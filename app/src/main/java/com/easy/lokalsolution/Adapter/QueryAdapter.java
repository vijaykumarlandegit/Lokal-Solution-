package com.easy.lokalsolution.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.lokalsolution.Class.QueryClass;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.databinding.QuerysampleBinding;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {

    Context context;
    ArrayList<QueryClass> list;

    public QueryAdapter(Context context, ArrayList<QueryClass> list) {
        this.context = context;
        this.list = list;
    }

    private String getTime(String time, Long timestamp) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(time));
        String timee = new SimpleDateFormat("dd-MM-yy").format(timestamp);
        return timee;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.querysample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryAdapter.ViewHolder holder, int position) {
        QueryClass data = list.get(position);

        Long time = data.getTime();
        String tt = String.valueOf(time);
        holder.binding.showtimetext.setText(getTime(tt, time));

        String type = data.getType();
        String subtype = data.getSubtype();
        String money = data.getMoney();
        String address = data.getAddress();
        String uname = data.getUname();
        String disc = data.getDisc();
        String note = data.getNote();
        String image = data.getImage();
        String number = data.getContact();
        String whatsapp = data.getWhatsapp();
        String contactime = data.getContactime();

        holder.binding.type.setText(type);
        holder.binding.subtype.setText(subtype);

        if (!disc.isEmpty()) {
            holder.binding.disc.setVisibility(View.VISIBLE);
            holder.binding.disc.setText(disc);

        } else {
            holder.binding.disc.setVisibility(View.GONE);

        }


        if (type.equals("किरायाने देणे (Rent)")) {
            holder.binding.monetyjust.setText("किराया :");
            holder.binding.type.setVisibility(View.VISIBLE);

        } else if (type.equals("किरायाने पाहिजे (Rent)")) {
            holder.binding.monetyjust.setText("किराया :");
            holder.binding.type.setVisibility(View.VISIBLE);

        } else if (type.equals("विकत पाहिजे (Buy)")) {
            holder.binding.monetyjust.setText("किंमत :");
            holder.binding.type.setVisibility(View.VISIBLE);

        } else if (type.equals("विक्री आहे (Sell)")) {
            holder.binding.monetyjust.setText("किंमत :");
            holder.binding.type.setVisibility(View.VISIBLE);

        } else if (type.equals("नोकरी आहे (Job)")) {
            holder.binding.monetyjust.setText("पगार :");
            holder.binding.type.setVisibility(View.VISIBLE);

        } else if (type.equals("नोकरी पाहिजे (Job)")) {
            holder.binding.monetyjust.setText("पगार :");
            holder.binding.type.setVisibility(View.VISIBLE);

        } else if (type.equals("कामासाठी व्यक्ती पाहिजे (Need)")) {
            holder.binding.type.setVisibility(View.VISIBLE);

            holder.binding.monetyjust.setText("पगार :");

        } else if (type.equals("वरील पैकी वेगळे (Other)")) {
            holder.binding.monetyjust.setText("");
            holder.binding.type.setVisibility(View.GONE);


        }


        if (!image.equals("No")) {
            holder.binding.openimage.setVisibility(View.VISIBLE);
            holder.binding.imagecard.setVisibility(View.GONE);
            Picasso.get().load(image).into(holder.binding.squeryimage);

        } else {
            holder.binding.openimage.setVisibility(View.GONE);
            holder.binding.imagecard.setVisibility(View.GONE);

        }

        holder.binding.openimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.imagecard.setVisibility(View.VISIBLE);
            }
        });
        holder.binding.imagecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.imagecard.setVisibility(View.GONE);
            }
        });
        if (!money.isEmpty()) {
            holder.binding.moneyview.setVisibility(View.VISIBLE);
            holder.binding.money.setText(money);
        } else {
            holder.binding.moneyview.setVisibility(View.GONE);
        }
        if (!uname.isEmpty()) {
            holder.binding.unameview.setVisibility(View.VISIBLE);
            holder.binding.uname.setText(uname);
        } else {
            holder.binding.unameview.setVisibility(View.GONE);
        }
        if (!address.isEmpty()) {
            holder.binding.addressview.setVisibility(View.VISIBLE);
            holder.binding.address.setText(address);
        } else {
            holder.binding.addressview.setVisibility(View.GONE);
        }if (!contactime.isEmpty()) {
            holder.binding.contactimeview.setVisibility(View.VISIBLE);
            holder.binding.contactime.setText(contactime);
        } else {
            holder.binding.contactimeview.setVisibility(View.GONE);
        }
        if (!note.isEmpty()) {
            holder.binding.noteview.setVisibility(View.VISIBLE);
            holder.binding.note.setText(note);
        } else {
            holder.binding.noteview.setVisibility(View.GONE);
        }

        holder.binding.squerycontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                context.startActivity(intent);

            }
        });

        if (!whatsapp.isEmpty()) {
            holder.binding.squerywhatsapp.setVisibility(View.VISIBLE);
            holder.binding.squerywhatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String wn = "https://wa.me/+917028297606?text= Hi is anyone available?";
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse("https://wa.me/+91" + whatsapp + "?text= Hi is anyone available?"));
                    context.startActivity(intent1);

                }
            });
        } else {
            holder.binding.squerywhatsapp.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        QuerysampleBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = QuerysampleBinding.bind(itemView);
        }
    }
}
