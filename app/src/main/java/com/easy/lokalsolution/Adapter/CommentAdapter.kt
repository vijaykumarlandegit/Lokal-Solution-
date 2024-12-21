package com.easy.lokalsolution.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easy.lokalsolution.Class.CommentClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.CommentsampleBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CommentAdapter(var context: Context, var list: ArrayList<CommentClass?>?) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    private fun getTime(time: String, timestamp: Long?): String {
        val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.setTimeInMillis(time.toLong())
        val timee: String = SimpleDateFormat("dd-MM-yy hh:mm aa").format(timestamp)
        return timee
    }

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.commentsample, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: CommentClass? = list?.get(position)
        val comment: String? = data?.comment
        val userid: String? = data?.userid
        val time: Long? = data?.time

        val tt: String = time.toString()
        holder.binding.commTime.setText(getTime(tt, time))
        holder.binding.commComment.setText(comment)
        FirebaseFirestore.getInstance().collection("AllUserG").document((userid)!!)
            .get().addOnSuccessListener(object : OnSuccessListener<DocumentSnapshot> {
                public override fun onSuccess(documentSnapshot: DocumentSnapshot) {
                    val name: String? = documentSnapshot.getString("name")
                    val pic: String? = documentSnapshot.getString("image")
                    holder.binding.commUN.setText("@" + name)
                    Picasso.get().load(pic).into(holder.binding.commUP)
                }
            })
    }

    public override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CommentsampleBinding

        init {
            binding = CommentsampleBinding.bind(itemView)
        }
    }
}
