package com.easy.lokalsolution.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.easy.lokalsolution.Adapter.CommentAdapter
import com.easy.lokalsolution.Class.CommentClass
import com.easy.lokalsolution.databinding.ActivityCommnetBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.util.Date
 class CommnetActivity() : AppCompatActivity() {
    var binding: ActivityCommnetBinding? = null
    var list: ArrayList<CommentClass?>? = null
    var commentAdapter: CommentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommnetBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        val dialog: ProgressDialog = ProgressDialog(this)
        dialog.setCancelable(false)
        dialog.setMessage("Comment adding....")
        val id: String? = getIntent().getStringExtra("id")
        list = ArrayList()
        commentAdapter = CommentAdapter(this, list)
        binding!!.commentRec.setAdapter(commentAdapter)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        binding!!.commentRec.setLayoutManager(layoutManager)
        binding!!.shimmer.setVisibility(View.VISIBLE)
        binding!!.shimmer.startShimmer()
        FirebaseFirestore.getInstance().collection("Comment").document((id)!!).collection("Comment")
            .orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        list!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: CommentClass? = snapshot.toObject(CommentClass::class.java)
                            list!!.add(data)
                        }
                        commentAdapter!!.notifyDataSetChanged()
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    } else {
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                        Toast.makeText(this@CommnetActivity, "No Comments Yet", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        binding!!.showrentswip.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            public override fun onRefresh() {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Comment").document((id)!!)
                    .collection("Comment")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                list!!.clear()
                                for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                    val data: CommentClass? = snapshot.toObject(
                                        CommentClass::class.java
                                    )
                                    list!!.add(data)
                                }
                                commentAdapter!!.notifyDataSetChanged()
                                binding!!.shimmer.setVisibility(View.GONE)
                                binding!!.shimmer.stopShimmer()
                            } else {
                                binding!!.shimmer.setVisibility(View.GONE)
                                binding!!.shimmer.stopShimmer()
                                Toast.makeText(
                                    this@CommnetActivity,
                                    "No Comments Yet",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                binding!!.showrentswip.setRefreshing(false)
                Toast.makeText(this@CommnetActivity, "Data refresh", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.commentSender.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val comment: String = binding!!.commentText.getText().toString()
                if (!comment.isEmpty()) {
                    dialog.show()
                    val collectionReference: CollectionReference =
                        FirebaseFirestore.getInstance().collection("Comment").document(
                            (id)!!
                        )
                            .collection("Comment")
                    val comid: String = collectionReference.document().getId()
                    val commentClass: CommentClass = CommentClass(
                        id,
                        FirebaseAuth.getInstance().getUid(),
                        comid,
                        comment,
                        Date().getTime()
                    )
                    collectionReference.document(comid).set(commentClass)
                        .addOnSuccessListener(object : OnSuccessListener<Void?> {
                            public override fun onSuccess(unused: Void?) {
                                binding!!.commentText.setText("")
                                dialog.dismiss()
                                Toast.makeText(
                                    this@CommnetActivity,
                                    "Comment Addend Successfully, Please refresh",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                } else {
                    Toast.makeText(this@CommnetActivity, "Please enter comment", Toast.LENGTH_SHORT)
                        .show()
                    binding!!.commentText.setError("Enter Comment")
                }
            }
        })
        binding!!.back.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                finish()
            }
        })
    }
}