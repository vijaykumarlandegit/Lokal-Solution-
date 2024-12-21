package com.easy.lokalsolution.Adapter

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.easy.lokalsolution.Activity.EditNewsActivity
import com.easy.lokalsolution.Class.NewsClass
import com.easy.lokalsolution.Class.OwnNewsClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.OwnnewsampleBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OwnNewsAdapter(var list: ArrayList<OwnNewsClass?>?, var context: Context) :
    RecyclerView.Adapter<OwnNewsAdapter.ViewHolder>() {
    var dialog: ProgressDialog? = null
    var `in`: Int = 0
    private fun getTime(time: String, timestamp: Long?): String {
        val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.setTimeInMillis(time.toLong())
        val timee: String = SimpleDateFormat("dd-MM-yy hh:mm aa").format(timestamp)
        return timee
    }

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.ownnewsample, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: OwnNewsClass? = list?.get(position)
        val id: String? = data?.id
        val time: Long? = data?.time
        val tt: String = time.toString()
        holder.binding.showtimetext.setText(getTime(tt, time))
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("News").document((id)!!).get()
            .addOnSuccessListener(object : OnSuccessListener<DocumentSnapshot> {
                public override fun onSuccess(snapshot: DocumentSnapshot) {
                    val data1: NewsClass? = snapshot.toObject(NewsClass::class.java)
                    val type: String? = data1?.type
                    val title: String? = data1?.title
                    val dis: String? = data1?.disc
                    val image: String? = data1?.image
                    holder.binding.type.setText(type)
                    holder.binding.title.setText(title)
                    holder.binding.disc.setText(dis)
                    if (!(image == "No")) {
                        holder.binding.imagecard.setVisibility(View.VISIBLE)
                        holder.binding.shareview.setVisibility(View.VISIBLE)
                        holder.binding.saveview.setVisibility(View.VISIBLE)
                        Picasso.get().load(image).placeholder(R.drawable.images)
                            .into(holder.binding.newsimage)
                    } else {
                        holder.binding.imagecard.setVisibility(View.GONE)
                        holder.binding.shareview.setVisibility(View.GONE)
                        holder.binding.saveview.setVisibility(View.GONE)
                    }
                }
            })
        FirebaseFirestore.getInstance().collection("Like").document((id)!!).collection("Like")
            .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                    val count: String = queryDocumentSnapshots.size().toString()
                    holder.binding.likecount.setText(count)
                }
            })
        FirebaseFirestore.getInstance().collection("Comment").document((id)!!).collection("Comment")
            .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                    val count: String = queryDocumentSnapshots.size().toString()
                    holder.binding.commentcount.setText(count)
                }
            })
        holder.binding.editview.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                Toast.makeText(context, "Edit Your Post", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(context, EditNewsActivity::class.java)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
        })
        dialog = ProgressDialog(context)
        dialog!!.setCancelable(false)
        dialog!!.setMessage("Please wait....")
        holder.binding.deleteview.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(
                    context
                )
                builder.setCancelable(true)
                builder.setTitle("Delete Post")
                builder.setMessage("Are you sure you want to delete your post")
                builder.setIcon(R.drawable.warna)
                builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    public override fun onClick(dialogInterface: DialogInterface, i: Int) {
                        dialog!!.show()
                        FirebaseFirestore.getInstance().collection("Nanded")
                            .document("NandedCity").collection("News").document((id)!!).delete()
                            .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                public override fun onSuccess(unused: Void?) {
                                    FirebaseFirestore.getInstance().collection("OwnData")
                                        .document((FirebaseAuth.getInstance().getUid())!!)
                                        .collection("News").document(
                                            (id)
                                        ).delete()
                                    dialogInterface.dismiss()
                                    dialog!!.dismiss()
                                    Toast.makeText(
                                        context,
                                        "Post Deleted Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    }
                })
                builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    public override fun onClick(dialogInterface: DialogInterface, i: Int) {
                        dialogInterface.dismiss()
                        Toast.makeText(context, "Thanks !!!", Toast.LENGTH_SHORT).show()
                    }
                })
                builder.show()
            }
        })
    }

    public override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: OwnnewsampleBinding

        init {
            binding = OwnnewsampleBinding.bind(itemView)
        }
    }
}
