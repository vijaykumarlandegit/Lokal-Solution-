package com.easy.lokalsolution.Activity
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.CommentAdapter
import com.easy.lokalsolution.Class.CommentClass
import com.easy.lokalsolution.databinding.ActivityCommnetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.Date
 class CommnetActivity() : AppCompatActivity() {
    var binding: ActivityCommnetBinding? = null
    var list: ArrayList<CommentClass?>? = null
    var commentAdapter: CommentAdapter? = null
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommnetBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val dialog: ProgressDialog = ProgressDialog(this)
        dialog.setCancelable(false)
        dialog.setMessage("Comment adding....")
        val id: String? = intent.getStringExtra("id")
        list = ArrayList()
        commentAdapter = CommentAdapter(this, list)
        binding!!.commentRec.adapter = commentAdapter
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        binding!!.commentRec.layoutManager = layoutManager
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer.startShimmer()
        FirebaseFirestore.getInstance().collection("Comment").document((id)!!).collection("Comment")
            .orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data  = snapshot.toObject(CommentClass::class.java)
                        list!!.add(data)
                    }
                    commentAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                } else {
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                    Toast.makeText(this@CommnetActivity, "No Comments Yet", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        binding!!.showrentswip.setOnRefreshListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Comment").document((id))
                .collection("Comment")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        list!!.clear()
                        for (snapshot: DocumentSnapshot in it.documents) {
                            val data = snapshot.toObject(CommentClass::class.java)
                            list!!.add(data)
                        }
                        commentAdapter!!.notifyDataSetChanged()
                        binding!!.shimmer.visibility = View.GONE
                        binding!!.shimmer.stopShimmer()
                    } else {
                        binding!!.shimmer.visibility = View.GONE
                        binding!!.shimmer.stopShimmer()
                        Toast.makeText(
                            this@CommnetActivity,
                            "No Comments Yet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            binding!!.showrentswip.isRefreshing = false
            Toast.makeText(this@CommnetActivity, "Data refresh", Toast.LENGTH_SHORT).show()
        }
        binding!!.commentSender.setOnClickListener {
            val comment: String = binding!!.commentText.text.toString()
            if (comment.isNotEmpty()) {
                dialog.show()
                val collectionReference: CollectionReference =
                    FirebaseFirestore.getInstance().collection("Comment").document(
                        (id)
                    )
                        .collection("Comment")
                val comid: String = collectionReference.document().id
                val commentClass: CommentClass = CommentClass(
                    id,
                    FirebaseAuth.getInstance().uid,
                    comid,
                    comment,
                    Date().time
                )
                collectionReference.document(comid).set(commentClass)
                    .addOnSuccessListener {
                        binding!!.commentText.setText("")
                        dialog.dismiss()
                        Toast.makeText(
                            this@CommnetActivity,
                            "Comment Addend Successfully, Please refresh",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(this@CommnetActivity, "Please enter comment", Toast.LENGTH_SHORT)
                    .show()
                binding!!.commentText.error = "Enter Comment"
            }
        }
        binding!!.back.setOnClickListener { finish() }
    }
}