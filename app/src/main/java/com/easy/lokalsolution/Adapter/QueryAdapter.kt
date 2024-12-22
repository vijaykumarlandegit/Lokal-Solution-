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
        calendar.timeInMillis = time.toLong()
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

        holder.binding.type.text = type
        holder.binding.subtype.text = subtype
        if (!disc!!.isEmpty()) {
            holder.binding.disc.visibility = View.VISIBLE
            holder.binding.disc.text = disc
        } else {
            holder.binding.disc.visibility = View.GONE
        }
        if ((type == "किरायाने देणे (Rent)")) {
            holder.binding.monetyjust.text = "किराया :"
            holder.binding.type.visibility = View.VISIBLE
        } else if ((type == "किरायाने पाहिजे (Rent)")) {
            holder.binding.monetyjust.text = "किराया :"
            holder.binding.type.visibility = View.VISIBLE
        } else if ((type == "विकत पाहिजे (Buy)")) {
            holder.binding.monetyjust.text = "किंमत :"
            holder.binding.type.visibility = View.VISIBLE
        } else if ((type == "विक्री आहे (Sell)")) {
            holder.binding.monetyjust.text = "किंमत :"
            holder.binding.type.visibility = View.VISIBLE
        } else if ((type == "नोकरी आहे (Job)")) {
            holder.binding.monetyjust.text = "पगार :"
            holder.binding.type.visibility = View.VISIBLE
        } else if ((type == "नोकरी पाहिजे (Job)")) {
            holder.binding.monetyjust.text = "पगार :"
            holder.binding.type.visibility = View.VISIBLE
        } else if ((type == "कामासाठी व्यक्ती पाहिजे (Need)")) {
            holder.binding.type.visibility = View.VISIBLE
            holder.binding.monetyjust.text = "पगार :"
        } else if ((type == "वरील पैकी वेगळे (Other)")) {
            holder.binding.monetyjust.text = ""
            holder.binding.type.visibility = View.GONE
        }
        if (image != "No") {
            holder.binding.openimage.visibility = View.VISIBLE
            holder.binding.imagecard.visibility = View.GONE
            Picasso.get().load(image).into(holder.binding.squeryimage)
        } else {
            holder.binding.openimage.visibility = View.GONE
            holder.binding.imagecard.visibility = View.GONE
        }
        holder.binding.openimage.setOnClickListener { holder.binding.imagecard.visibility = View.VISIBLE }
        holder.binding.imagecard.setOnClickListener {
            holder.binding.imagecard.visibility = View.GONE
        }
        if (money!!.isNotEmpty()) {
            holder.binding.moneyview.visibility = View.VISIBLE
            holder.binding.money.text = money
        } else {
            holder.binding.moneyview.visibility = View.GONE
        }
        if (uname!!.isNotEmpty()) {
            holder.binding.unameview.visibility = View.VISIBLE
            holder.binding.uname.text = uname
        } else {
            holder.binding.unameview.visibility = View.GONE
        }
        if (address!!.isNotEmpty()) {
            holder.binding.addressview.visibility = View.VISIBLE
            holder.binding.address.text = address
        } else {
            holder.binding.addressview.visibility = View.GONE
        }
        if (contactime!!.isNotEmpty()) {
            holder.binding.contactimeview.visibility = View.VISIBLE
            holder.binding.contactime.text = contactime
        } else {
            holder.binding.contactimeview.visibility = View.GONE
        }
        if (note!!.isNotEmpty()) {
            holder.binding.noteview.visibility = View.VISIBLE
            holder.binding.note.text = note
        } else {
            holder.binding.noteview.visibility = View.GONE
        }
        holder.binding.squerycontact.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:$number"))
            context!!.startActivity(intent)
        }
        if (!whatsapp!!.isEmpty()) {
            holder.binding.squerywhatsapp.visibility = View.VISIBLE
            holder.binding.squerywhatsapp.setOnClickListener {
                val wn: String = "https://wa.me/+917028297606?text= Hi is anyone available?"
                val intent1: Intent = Intent(Intent.ACTION_VIEW)
                intent1.setData(Uri.parse("https://wa.me/+91$whatsapp?text= Hi is anyone available?"))
                context!!.startActivity(intent1)
            }
        } else {
            holder.binding.squerywhatsapp.visibility = View.GONE
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
