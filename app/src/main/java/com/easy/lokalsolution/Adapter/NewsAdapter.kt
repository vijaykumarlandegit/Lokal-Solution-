package com.easy.lokalsolution.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.easy.lokalsolution.Activity.CommnetActivity
import com.easy.lokalsolution.Class.LikeClass
import com.easy.lokalsolution.Class.NewsClass
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.NewssampleBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NewsAdapter(var list: List<NewsClass> ,var context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var `in` = 0
    private fun getTime(time: String, timestamp: Long?): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = time.toLong()
        return SimpleDateFormat("dd-MM-yy hh:mm aa").format(timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newssample, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userr = list[position]
        val type = userr.type
        val title = userr.title
        val disc = userr.disc
        val id = userr.id
        val image = userr.image
        val time = userr.time
        val tt = userr.time.toString()
        holder.binding.showtimetext.text = getTime(tt, time)
        holder.binding.getnewstitle.text = title
        holder.binding.expandTextView.text = disc
        if ((type == "Other")) {
            holder.binding.type.visibility = View.GONE
        } else {
            holder.binding.type.visibility = View.VISIBLE
            holder.binding.type.text = type
        }
        if (image != "No") {
            holder.binding.newsimage.visibility = View.VISIBLE
            holder.binding.shareview.visibility = View.VISIBLE
            holder.binding.saveview.visibility = View.VISIBLE
            Picasso.get().load(image).placeholder(R.drawable.placeholder)
                .into(holder.binding.newsimage)
        } else {
            holder.binding.newsimage.visibility = View.GONE
            holder.binding.shareview.visibility = View.GONE
            holder.binding.saveview.visibility = View.GONE
        }
        FirebaseFirestore.getInstance().collection("AllUserG")
            .document((FirebaseAuth.getInstance().uid)!!)
            .get().addOnCompleteListener { task ->
                val snapshot = task.result
                if (snapshot.exists()) {
                    val pic = snapshot.getString("image")
                    val name = snapshot.getString("name")
                    holder.binding.getnewsownername.text = name
                    Picasso.get().load(pic).placeholder(R.drawable.useraaa)
                        .into(holder.binding.getnewownerpic)
                }
            }
        FirebaseFirestore.getInstance().collection("Like").document((id)!!).collection("Like")
            .get().addOnSuccessListener { queryDocumentSnapshots ->
                val count = queryDocumentSnapshots.size().toString()
                holder.binding.likecount.text = count
                holder.binding.unlikecount.text = count
            }
        FirebaseFirestore.getInstance().collection("Comment").document((id)!!).collection("Comment")
            .get().addOnSuccessListener { queryDocumentSnapshots ->
                val count = queryDocumentSnapshots.size().toString()
                holder.binding.commentcount.text = count
            }
        FirebaseFirestore.getInstance().collection("Like")
            .document((id)!!).collection("Like").document((FirebaseAuth.getInstance().uid)!!)
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        holder.binding.unlikeview.visibility = View.VISIBLE
                        holder.binding.likeview.visibility = View.GONE
                    } else {
                        holder.binding.unlikeview.visibility = View.GONE
                        holder.binding.likeview.visibility = View.VISIBLE
                    }
                }
            }
        holder.binding.unlikeview.setOnClickListener {
            holder.binding.unlikeview.visibility = View.GONE
            holder.binding.likeview.visibility = View.VISIBLE
            val ulc = holder.binding.unlikecount.text.toString().toInt()
            holder.binding.likecount.text = (ulc - 1).toString() + ""
            FirebaseFirestore.getInstance().collection("Like").document((id))
                .collection("Like").document((FirebaseAuth.getInstance().uid)!!).delete()
                .addOnSuccessListener {

                }
        }
        holder.binding.likeview.setOnClickListener {
            holder.binding.unlikeview.visibility = View.VISIBLE
            holder.binding.likeview.visibility = View.GONE
            val ulc = holder.binding.likecount.text.toString().toInt()
            holder.binding.unlikecount.text = (ulc + 1).toString() + ""
            val commentClass = LikeClass(FirebaseAuth.getInstance().uid, Date().time)
            FirebaseFirestore.getInstance().collection("Like").document((id)!!)
                .collection("Like").document((FirebaseAuth.getInstance().uid)!!)
                .set(commentClass).addOnSuccessListener(
                    OnSuccessListener {
                        Toast.makeText(
                            context,
                            "Like Added",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
        }
        holder.binding.commentview.setOnClickListener { // Toast.makeText(context, "comment", Toast.LENGTH_SHORT).show();
            val intent = Intent(context, CommnetActivity::class.java)
            intent.putExtra("id", id)
            context!!.startActivity(intent)
        }
        holder.binding.shareview.setOnClickListener { // Toast.makeText(context, "share", Toast.LENGTH_SHORT).show();
            val bitmap = (holder.binding.newsimage.drawable as BitmapDrawable).bitmap
            val shareIntent = Intent()
            shareIntent.setAction(Intent.ACTION_SEND)
            shareIntent.setType("image/png")
            val bytes = ByteArrayOutputStream()
            // .compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            val path = MediaStore.Images.Media.insertImage(
                context!!.contentResolver,
                bitmap,
                "Title",
                null
            )
            // Uri uri = Uri.parse("android.resource://your package
            //name/"+R.drawable.ic_launcher);
            val uri = Uri.parse(path)
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "")
            context!!.startActivity(Intent.createChooser(shareIntent, "Share image"))
        }
        holder.binding.saveview.setOnClickListener {
            val draw = holder.binding.newsimage.drawable as BitmapDrawable
            val bitmap = draw.bitmap
            var outStream: FileOutputStream? = null
            val sdCard = Environment.getExternalStorageDirectory()
            val dir = File(sdCard.absolutePath + "/Download")
            dir.mkdirs()
            val fileName = String.format("%d.jpg", System.currentTimeMillis())
            val outFile = File(dir, fileName)
            Toast.makeText(context, "Image Saved", Toast.LENGTH_SHORT).show()
            try {
                outStream = FileOutputStream(outFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
                outStream.flush()
                outStream.close()
                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                intent.setData(Uri.fromFile(outFile))
                context!!.sendBroadcast(intent)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: NewssampleBinding

        init {
            binding = NewssampleBinding.bind(itemView)
        }
    }

    companion object {
        private fun scanFile(context: Context, imageUri: Uri) {
            val scanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            scanIntent.setData(imageUri)
            context.sendBroadcast(scanIntent)
        }
    }
}
