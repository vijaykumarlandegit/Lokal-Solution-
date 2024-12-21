package com.easy.lokalsolution.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.easy.lokalsolution.Adapter.OwnIdAdapter
import com.easy.lokalsolution.Adapter.OwnNewsAdapter
import com.easy.lokalsolution.Adapter.OwnQueryAdapter
import com.easy.lokalsolution.Class.OwnIdClass
import com.easy.lokalsolution.Class.OwnNewsClass
import com.easy.lokalsolution.Class.OwnQueryClass
import com.easy.lokalsolution.databinding.ActivityShowDataToOwnerBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class ShowDataToOwnerActivity() : AppCompatActivity() {
    var binding: ActivityShowDataToOwnerBinding? = null
    var list1: ArrayList<OwnNewsClass?>? = null
    var ownNewsAdapter: OwnNewsAdapter? = null
    var list2: ArrayList<OwnQueryClass?>? = null
    var ownQueryAdapter: OwnQueryAdapter? = null
    var list3: ArrayList<OwnIdClass?>? = null
    var ownIdAdapter: OwnIdAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataToOwnerBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        val type: String? = getIntent().getStringExtra("type")
        binding!!.back.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                finish()
            }
        })
        if ((type == "11")) {
            list1 = ArrayList()
            ownNewsAdapter = OwnNewsAdapter(list1, this@ShowDataToOwnerActivity)
            binding!!.showdatarec.setAdapter(ownNewsAdapter)
            val layoutManager1: LinearLayoutManager =
                LinearLayoutManager(this@ShowDataToOwnerActivity)
            binding!!.showdatarec.setLayoutManager(layoutManager1)
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("OwnData")
                .document((FirebaseAuth.getInstance().getUid())!!).collection("News")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            list1!!.clear()
                            for (snapshot1: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data1: OwnNewsClass? = snapshot1.toObject(
                                    OwnNewsClass::class.java
                                )
                                list1!!.add(data1)
                            }
                            ownNewsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        } else {
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                            Toast.makeText(
                                this@ShowDataToOwnerActivity,
                                "No Data Available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            binding!!.swiprefresh.setOnRefreshListener(object : OnRefreshListener {
                public override fun onRefresh() {
                    binding!!.shimmer.setVisibility(View.VISIBLE)
                    binding!!.shimmer.startShimmer()
                    FirebaseFirestore.getInstance().collection("OwnData")
                        .document((FirebaseAuth.getInstance().getUid())!!).collection("News")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                            public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    list1!!.clear()
                                    for (snapshot1: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                        val data1: OwnNewsClass? = snapshot1.toObject(
                                            OwnNewsClass::class.java
                                        )
                                        list1!!.add(data1)
                                    }
                                    ownNewsAdapter!!.notifyDataSetChanged()
                                    binding!!.shimmer.setVisibility(View.GONE)
                                    binding!!.shimmer.stopShimmer()
                                } else {
                                    binding!!.shimmer.setVisibility(View.GONE)
                                    binding!!.shimmer.stopShimmer()
                                    Toast.makeText(
                                        this@ShowDataToOwnerActivity,
                                        "No Data Available",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        })
                    binding!!.swiprefresh.setRefreshing(false)
                    Toast.makeText(this@ShowDataToOwnerActivity, "Data refresh", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            binding!!.newpost.setVisibility(View.VISIBLE)
            binding!!.newid.setVisibility(View.GONE)
            binding!!.newquery.setVisibility(View.GONE)
            binding!!.newpost.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(view: View) {
                    val intent: Intent =
                        Intent(this@ShowDataToOwnerActivity, AddNewsActivity::class.java)
                    startActivity(intent)
                }
            })
        } else if ((type == "12")) {
            list2 = ArrayList()
            ownQueryAdapter = OwnQueryAdapter(list2, this@ShowDataToOwnerActivity)
            binding!!.showdatarec.setAdapter(ownQueryAdapter)
            val layoutManager2: LinearLayoutManager =
                LinearLayoutManager(this@ShowDataToOwnerActivity)
            binding!!.showdatarec.setLayoutManager(layoutManager2)
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("OwnData")
                .document((FirebaseAuth.getInstance().getUid())!!).collection("Query")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            list2!!.clear()
                            for (snapshot2: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data2: OwnQueryClass? = snapshot2.toObject(
                                    OwnQueryClass::class.java
                                )
                                list2!!.add(data2)
                            }
                            ownQueryAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        } else {
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                            Toast.makeText(
                                this@ShowDataToOwnerActivity,
                                "No Data Available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            binding!!.swiprefresh.setOnRefreshListener(object : OnRefreshListener {
                public override fun onRefresh() {
                    binding!!.shimmer.setVisibility(View.VISIBLE)
                    binding!!.shimmer.startShimmer()
                    FirebaseFirestore.getInstance().collection("OwnData")
                        .document((FirebaseAuth.getInstance().getUid())!!).collection("Query")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                            public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    list2!!.clear()
                                    for (snapshot2: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                        val data2: OwnQueryClass? = snapshot2.toObject(
                                            OwnQueryClass::class.java
                                        )
                                        list2!!.add(data2)
                                    }
                                    ownQueryAdapter!!.notifyDataSetChanged()
                                    binding!!.shimmer.setVisibility(View.GONE)
                                    binding!!.shimmer.stopShimmer()
                                } else {
                                    binding!!.shimmer.setVisibility(View.GONE)
                                    binding!!.shimmer.stopShimmer()
                                    Toast.makeText(
                                        this@ShowDataToOwnerActivity,
                                        "No Data Available",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        })
                    binding!!.swiprefresh.setRefreshing(false)
                    Toast.makeText(this@ShowDataToOwnerActivity, "Data refresh", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            binding!!.newpost.setVisibility(View.GONE)
            binding!!.newid.setVisibility(View.GONE)
            binding!!.newquery.setVisibility(View.VISIBLE)
            binding!!.newquery.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(view: View) {
                    val intent: Intent =
                        Intent(this@ShowDataToOwnerActivity, AddQueryActivity::class.java)
                    startActivity(intent)
                }
            })
        } else if ((type == "13")) {
            list3 = ArrayList()
            ownIdAdapter = OwnIdAdapter(list3, this@ShowDataToOwnerActivity)
            binding!!.showdatarec.setAdapter(ownIdAdapter)
            val layoutManager3: LinearLayoutManager =
                LinearLayoutManager(this@ShowDataToOwnerActivity)
            binding!!.showdatarec.setLayoutManager(layoutManager3)
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("OwnData")
                .document((FirebaseAuth.getInstance().getUid())!!).collection("Id")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            list3!!.clear()
                            for (snapshot3: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data3: OwnIdClass? = snapshot3.toObject(OwnIdClass::class.java)
                                list3!!.add(data3)
                            }
                            ownIdAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        } else {
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                            Toast.makeText(
                                this@ShowDataToOwnerActivity,
                                "No Data Available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            binding!!.swiprefresh.setOnRefreshListener(object : OnRefreshListener {
                public override fun onRefresh() {
                    binding!!.shimmer.setVisibility(View.VISIBLE)
                    binding!!.shimmer.startShimmer()
                    FirebaseFirestore.getInstance().collection("OwnData")
                        .document((FirebaseAuth.getInstance().getUid())!!).collection("Id")
                        .orderBy("time", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                            public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    list3!!.clear()
                                    for (snapshot3: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                        val data3: OwnIdClass? = snapshot3.toObject(
                                            OwnIdClass::class.java
                                        )
                                        list3!!.add(data3)
                                    }
                                    ownIdAdapter!!.notifyDataSetChanged()
                                    binding!!.shimmer.setVisibility(View.GONE)
                                    binding!!.shimmer.stopShimmer()
                                } else {
                                    binding!!.shimmer.setVisibility(View.GONE)
                                    binding!!.shimmer.stopShimmer()
                                    Toast.makeText(
                                        this@ShowDataToOwnerActivity,
                                        "No Data Available",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        })
                    binding!!.swiprefresh.setRefreshing(false)
                    Toast.makeText(this@ShowDataToOwnerActivity, "Data refresh", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            binding!!.newpost.setVisibility(View.GONE)
            binding!!.newid.setVisibility(View.VISIBLE)
            binding!!.newquery.setVisibility(View.GONE)
            binding!!.newid.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(view: View) {
                    val intent: Intent =
                        Intent(this@ShowDataToOwnerActivity, AddIdActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }
}