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
import com.easy.lokalsolution.Activity.EditQueryActivity
import com.easy.lokalsolution.Class.OwnQueryClass
import com.easy.lokalsolution.Class.QueryClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.OwnquerysampleBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OwnQueryAdapter(var list: ArrayList<OwnQueryClass?>?, var context: Context) :
    RecyclerView.Adapter<OwnQueryAdapter.ViewHolder>() {
    var dialog: ProgressDialog? = null
    var `in`: Int = 0
    private fun getTime(time: String, timestamp: Long?): String {
        val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.setTimeInMillis(time.toLong())
        val timee: String = SimpleDateFormat("dd-MM-yy hh:mm aa").format(timestamp)
        return timee
    }

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.ownquerysample, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: OwnQueryClass? = list?.get(position)
        val id: String? = data?.id
        val time: Long? = data?.time
        val tt: String = time.toString()
        holder.binding.showtimetext.setText(getTime(tt, time))
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("Query").document((id)!!).get()
            .addOnSuccessListener(object : OnSuccessListener<DocumentSnapshot> {
                public override fun onSuccess(snapshot: DocumentSnapshot) {
                    val data1: QueryClass? = snapshot.toObject(QueryClass::class.java)
                    val type: String? = data1?.type
                    val subtype: String? = data1?.subtype
                    val disc: String? = data1?.disc
                    val image: String? = data1?.image
                    holder.binding.type.setText(type)
                    holder.binding.subtype.setText(subtype)
                    holder.binding.squerydisc.setText(disc)
                    if (!(image == "No")) {
                        holder.binding.imagecard.setVisibility(View.VISIBLE)
                        Picasso.get().load(image).into(holder.binding.squeryimage)
                    } else {
                        holder.binding.imagecard.setVisibility(View.GONE)
                    }
                }
            })
        holder.binding.editview.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                Toast.makeText(context, "Edit Your Query", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(context, EditQueryActivity::class.java)
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
                builder.setTitle("Delete Query")
                builder.setMessage("Are you sure you want to delete your query")
                builder.setIcon(R.drawable.warna)
                builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    public override fun onClick(dialogInterface: DialogInterface, i: Int) {
                        dialog!!.show()
                        FirebaseFirestore.getInstance().collection("Nanded")
                            .document("NandedCity").collection("Query").document((id)!!).delete()
                            .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                public override fun onSuccess(unused: Void?) {
                                    FirebaseFirestore.getInstance().collection("OwnData")
                                        .document((FirebaseAuth.getInstance().getUid())!!)
                                        .collection("Query").document(
                                            (id)
                                        ).delete()
                                    dialogInterface.dismiss()
                                    dialog!!.dismiss()
                                    Toast.makeText(
                                        context,
                                        "Query Deleted Successfully",
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
        var binding: OwnquerysampleBinding

        init {
            binding = OwnquerysampleBinding.bind(itemView)
        }
    }
}
