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
import com.easy.lokalsolution.Activity.ComIdDataShowActivity
import com.easy.lokalsolution.Activity.EditIdActivity
import com.easy.lokalsolution.Class.IdClass
import com.easy.lokalsolution.Class.OwnIdClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.OwnidsampleBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OwnIdAdapter(var list: ArrayList<OwnIdClass?>?, var context: Context) :
    RecyclerView.Adapter<OwnIdAdapter.ViewHolder>() {
    var `in`: Int = 0
    var dialog: ProgressDialog? = null
    private fun getTime(time: String, timestamp: Long?): String {
        val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.setTimeInMillis(time.toLong())
        val timee: String = SimpleDateFormat("dd-MM-yy hh:mm aa").format(timestamp)
        return timee
    }

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.ownidsample, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: OwnIdClass? = list?.get(position)
        val id: String? = data?.id
        val time: Long? = data?.time
        val tt: String = time.toString()
        holder.binding.showtimetext.setText(getTime(tt, time))
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("Id").document((id)!!)
            .get().addOnSuccessListener(object : OnSuccessListener<DocumentSnapshot> {
                public override fun onSuccess(documentSnapshot: DocumentSnapshot) {
                    val snapshot: IdClass? = documentSnapshot.toObject(IdClass::class.java)
                    val type: String? = snapshot?.type
                    val coursename: String? = snapshot?.cname
                    val exptime: String? = snapshot?.exptime
                    val shopname: String? = snapshot?.sname
                    val shopaddress: String? = snapshot?.saddress
                    val starttime: String? = snapshot?.customtime
                    val moredetails: String? = snapshot?.more
                    val umname: String? = snapshot?.uname
                    val image: String? = snapshot?.image


                    holder.binding.type.setText(type)
                    holder.binding.moredetails.setText(moredetails)
                    holder.binding.operatorname.setText(umname)
                    holder.binding.experitext.setText(exptime)
                    if (!(image == "No")) {
                        holder.binding.imagecard.setVisibility(View.VISIBLE)
                        Picasso.get().load(image).placeholder(R.drawable.images)
                            .into(holder.binding.cmspic)
                    } else {
                        holder.binding.imagecard.setVisibility(View.GONE)
                    }
                    if ((shopname == "!@#$%") && (shopaddress == "!@#$%")) {
                        holder.binding.shopview.setVisibility(View.GONE)
                        holder.binding.shopaddressview.setVisibility(View.GONE)
                    } else {
                        holder.binding.shopview.setVisibility(View.VISIBLE)
                        holder.binding.shopaddressview.setVisibility(View.VISIBLE)
                        holder.binding.shoptext.setText(shopname)
                        holder.binding.shopaddresstext.setText(shopaddress)
                    }
                    if ((starttime == "!@#$%")) {
                        holder.binding.owntimeview.setVisibility(View.GONE)
                        holder.binding.anytimeview.setVisibility(View.VISIBLE)
                    } else {
                        holder.binding.owntimeview.setVisibility(View.VISIBLE)
                        holder.binding.anytimeview.setVisibility(View.GONE)
                        holder.binding.customtime.setText(starttime)
                    }
                    if ((coursename == "!@#$%")) {
                        holder.binding.coursview.setVisibility(View.GONE)
                    } else {
                        holder.binding.coursview.setVisibility(View.VISIBLE)
                        holder.binding.coursetext.setText(coursename)
                    }
                }
            })
        holder.binding.editview.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                Toast.makeText(context, "Edit Your ID", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(context, EditIdActivity::class.java)
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
                builder.setTitle("Delete ID")
                builder.setMessage("Are you sure you want to delete your Id")
                builder.setIcon(R.drawable.warna)
                builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    public override fun onClick(dialogInterface: DialogInterface, i: Int) {
                        dialog!!.show()
                        FirebaseFirestore.getInstance().collection("Nanded")
                            .document("NandedCity").collection("Id").document((id)!!).delete()
                            .addOnSuccessListener(object : OnSuccessListener<Void?> {
                                public override fun onSuccess(unused: Void?) {
                                    FirebaseFirestore.getInstance().collection("OwnData")
                                        .document((FirebaseAuth.getInstance().getUid())!!)
                                        .collection("Id").document(
                                            (id)
                                        ).delete()
                                    dialogInterface.dismiss()
                                    dialog!!.dismiss()
                                    Toast.makeText(
                                        context,
                                        "ID Deleted Successfully",
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
        holder.binding.viewview.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(context, ComIdDataShowActivity::class.java)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
        })
    }

    public override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: OwnidsampleBinding

        init {
            binding = OwnidsampleBinding.bind(itemView)
        }
    }
}
