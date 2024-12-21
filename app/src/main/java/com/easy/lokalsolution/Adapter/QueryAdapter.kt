package com.easy.lokalsolution.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easy.lokalsolution.Class.QueryClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.QuerysampleBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class QueryAdapter(var context: Context?, var list: ArrayList<QueryClass?>) :
    RecyclerView.Adapter<QueryAdapter.ViewHolder>() {
    private fun getTime(time: String, timestamp: Long?): String {
        val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.setTimeInMillis(time.toLong())
        val timee: String = SimpleDateFormat("dd-MM-yy").format(timestamp)
        return timee
    }

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.querysample, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: QueryClass? = list.get(position)
        val time: Long? = data?.time
        val tt: String = time.toString()
        holder.binding.showtimetext.setText(getTime(tt, time))
        val type: String? = data?.type
        val subtype: String? = data?.subtype
        val money: String? = data?.money
        val address: String? = data?.address
        val uname: String? = data?.uname
        val disc: String? = data?.disc
        val note: String? = data?.note
        val image: String? = data?.image
        val number: String? = data?.contact
        val whatsapp: String? = data?.whatsapp
        val contactime: String? = data?.contactime

        holder.binding.type.setText(type)
        holder.binding.subtype.setText(subtype)
        if (!disc!!.isEmpty()) {
            holder.binding.disc.setVisibility(View.VISIBLE)
            holder.binding.disc.setText(disc)
        } else {
            holder.binding.disc.setVisibility(View.GONE)
        }
        if ((type == "किरायाने देणे (Rent)")) {
            holder.binding.monetyjust.setText("किराया :")
            holder.binding.type.setVisibility(View.VISIBLE)
        } else if ((type == "किरायाने पाहिजे (Rent)")) {
            holder.binding.monetyjust.setText("किराया :")
            holder.binding.type.setVisibility(View.VISIBLE)
        } else if ((type == "विकत पाहिजे (Buy)")) {
            holder.binding.monetyjust.setText("किंमत :")
            holder.binding.type.setVisibility(View.VISIBLE)
        } else if ((type == "विक्री आहे (Sell)")) {
            holder.binding.monetyjust.setText("किंमत :")
            holder.binding.type.setVisibility(View.VISIBLE)
        } else if ((type == "नोकरी आहे (Job)")) {
            holder.binding.monetyjust.setText("पगार :")
            holder.binding.type.setVisibility(View.VISIBLE)
        } else if ((type == "नोकरी पाहिजे (Job)")) {
            holder.binding.monetyjust.setText("पगार :")
            holder.binding.type.setVisibility(View.VISIBLE)
        } else if ((type == "कामासाठी व्यक्ती पाहिजे (Need)")) {
            holder.binding.type.setVisibility(View.VISIBLE)
            holder.binding.monetyjust.setText("पगार :")
        } else if ((type == "वरील पैकी वेगळे (Other)")) {
            holder.binding.monetyjust.setText("")
            holder.binding.type.setVisibility(View.GONE)
        }
        if (!(image == "No")) {
            holder.binding.openimage.setVisibility(View.VISIBLE)
            holder.binding.imagecard.setVisibility(View.GONE)
            Picasso.get().load(image).into(holder.binding.squeryimage)
        } else {
            holder.binding.openimage.setVisibility(View.GONE)
            holder.binding.imagecard.setVisibility(View.GONE)
        }
        holder.binding.openimage.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                holder.binding.imagecard.setVisibility(View.VISIBLE)
            }
        })
        holder.binding.imagecard.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                holder.binding.imagecard.setVisibility(View.GONE)
            }
        })
        if (!money!!.isEmpty()) {
            holder.binding.moneyview.setVisibility(View.VISIBLE)
            holder.binding.money.setText(money)
        } else {
            holder.binding.moneyview.setVisibility(View.GONE)
        }
        if (!uname!!.isEmpty()) {
            holder.binding.unameview.setVisibility(View.VISIBLE)
            holder.binding.uname.setText(uname)
        } else {
            holder.binding.unameview.setVisibility(View.GONE)
        }
        if (!address!!.isEmpty()) {
            holder.binding.addressview.setVisibility(View.VISIBLE)
            holder.binding.address.setText(address)
        } else {
            holder.binding.addressview.setVisibility(View.GONE)
        }
        if (!contactime!!.isEmpty()) {
            holder.binding.contactimeview.setVisibility(View.VISIBLE)
            holder.binding.contactime.setText(contactime)
        } else {
            holder.binding.contactimeview.setVisibility(View.GONE)
        }
        if (!note!!.isEmpty()) {
            holder.binding.noteview.setVisibility(View.VISIBLE)
            holder.binding.note.setText(note)
        } else {
            holder.binding.noteview.setVisibility(View.GONE)
        }
        holder.binding.squerycontact.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:" + number))
                context!!.startActivity(intent)
            }
        })
        if (!whatsapp!!.isEmpty()) {
            holder.binding.squerywhatsapp.setVisibility(View.VISIBLE)
            holder.binding.squerywhatsapp.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(view: View) {
                    val wn: String = "https://wa.me/+917028297606?text= Hi is anyone available?"
                    val intent1: Intent = Intent(Intent.ACTION_VIEW)
                    intent1.setData(Uri.parse("https://wa.me/+91" + whatsapp + "?text= Hi is anyone available?"))
                    context!!.startActivity(intent1)
                }
            })
        } else {
            holder.binding.squerywhatsapp.setVisibility(View.GONE)
        }
    }

    public override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: QuerysampleBinding

        init {
            binding = QuerysampleBinding.bind(itemView)
        }
    }
}
