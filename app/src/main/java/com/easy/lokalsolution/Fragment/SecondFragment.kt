package com.easy.lokalsolution.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.easy.lokalsolution.Adapter.QueryAdapter
import com.easy.lokalsolution.Class.QueryClass
import com.easy.lokalsolution.databinding.FragmentSecondBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class SecondFragment() : Fragment() {
    var binding: FragmentSecondBinding? = null
    var list: ArrayList<QueryClass?> = ArrayList()
    var adapter: QueryAdapter? = null
    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        adapter = QueryAdapter(getContext(), list)
        binding!!.queryrec.setAdapter(adapter)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        binding!!.queryrec.setLayoutManager(layoutManager)
        binding!!.shimmer.setVisibility(View.VISIBLE)
        binding!!.shimmer.startShimmer()
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("Query")
            .orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.setVisibility(View.GONE)
                    binding!!.shimmer.stopShimmer()
                }
            })
        binding!!.swiprefresh.setOnRefreshListener(object : OnRefreshListener {
            public override fun onRefresh() {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.swiprefresh.setRefreshing(false)
                Toast.makeText(getContext(), "Data refresh", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.sort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.sortboxview.setVisibility(View.VISIBLE)
                binding!!.justview.setVisibility(View.VISIBLE)
            }
        })
        binding!!.justview.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
            }
        })
        binding!!.allsort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "All", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.rentout.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "किरायाने देणे (Rent)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "किरायाने देणे", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.rentin.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "किरायाने पाहिजे (Rent)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "किरायाने पाहिजे", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.needbuy.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "विकत पाहिजे (Buy)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "विकत पाहिजे", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.sellout.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "विक्री आहे (Sell)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "विक्री आहे", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.jobsort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "नोकरी आहे (Job)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "नोकरी आहे", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.needjob.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "नोकरी पाहिजे (Job)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "नोकरी पाहिजे", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.needperson.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "कामासाठी व्यक्ती पाहिजे (Need)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "कामासाठी व्यक्ती पाहिजे", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.othersort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("Query")
                    .whereEqualTo("type", "वरील पैकी वेगळे (Other)")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                                list.add(data)
                            }
                            adapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "other", Toast.LENGTH_SHORT).show()
            }
        })
        return binding!!.getRoot()
    }
}