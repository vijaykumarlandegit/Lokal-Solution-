package com.easy.lokalsolution.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easy.lokalsolution.Activity.ComIdDataShowActivity
import com.easy.lokalsolution.Class.IdClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.IdsampleBinding
import com.squareup.picasso.Picasso

class IdAdapter(var context: Context, var list: ArrayList<IdClass?>?) :
    RecyclerView.Adapter<IdAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.idsample, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val snapshot = list?.get(position)
        val id = snapshot?.id
        val type = snapshot?.type
        val coursename = snapshot?.cname
        val exptime = snapshot?.exptime
        val shopname = snapshot?.sname
        val shopaddress = snapshot?.saddress
        val starttime = snapshot?.customtime
        val moredetails = snapshot?.more
        val umname = snapshot?.uname
        val ucontact = snapshot?.ucontact
        val uwhatsapp = snapshot?.uwhatsapp
        val image = snapshot?.image
        holder.binding.operatorname.text = umname
        holder.binding.type.text = type
        holder.binding.moredetails.text = moredetails
        holder.binding.experitext.text = exptime
        if (image != "No") {
            holder.binding.openimage.visibility = View.VISIBLE
            holder.binding.imagecard.visibility = View.GONE
            Picasso.get().load(image).into(holder.binding.image)
        } else {
            holder.binding.openimage.visibility = View.GONE
            holder.binding.imagecard.visibility = View.GONE
        }
        holder.binding.openimage.setOnClickListener {
            holder.binding.imagecard.visibility = View.VISIBLE
        }
        holder.binding.imagecard.setOnClickListener {
            holder.binding.imagecard.visibility = View.GONE
        }
        if (shopname == "!@#$%" && shopaddress == "!@#$%") {
            holder.binding.shopview.visibility = View.GONE
            holder.binding.shopaddressview.visibility = View.GONE
        } else {
            holder.binding.shopview.visibility = View.VISIBLE
            holder.binding.shopaddressview.visibility = View.VISIBLE
            holder.binding.shoptext.text = shopname
            holder.binding.shopaddresstext.text = shopaddress
        }
        if (starttime == "!@#$%") {
            holder.binding.owntimeview.visibility = View.GONE
            holder.binding.anytimeview.visibility = View.VISIBLE
        } else {
            holder.binding.owntimeview.visibility = View.VISIBLE
            holder.binding.anytimeview.visibility = View.GONE
            holder.binding.customtime.text = starttime
        }
        if (coursename == "!@#$%") {
            holder.binding.coursview.visibility = View.GONE
        } else {
            holder.binding.coursview.visibility = View.VISIBLE
            holder.binding.coursetext.text = coursename
        }
        if (!uwhatsapp!!.isEmpty()) {
            holder.binding.cmswhatsapp.visibility = View.VISIBLE
            holder.binding.cmswhatsapp.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("https://wa.me/+91$uwhatsapp?text= Hi is anyone available?"))
                context.startActivity(intent)
            }
        } else {
            holder.binding.cmswhatsapp.visibility = View.GONE
        }
        holder.binding.cmscontact.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:$ucontact"))
            context.startActivity(intent)
        }
        holder.binding.mainidshowlayout.setOnClickListener {
            val intent = Intent(context, ComIdDataShowActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: IdsampleBinding

        init {
            binding = IdsampleBinding.bind(itemView)
        }
    }
}
