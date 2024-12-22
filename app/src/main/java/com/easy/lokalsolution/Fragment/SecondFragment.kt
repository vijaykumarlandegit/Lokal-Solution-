package com.easy.lokalsolution.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.QueryAdapter
import com.easy.lokalsolution.Class.QueryClass
import com.easy.lokalsolution.databinding.FragmentSecondBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SecondFragment() : Fragment() {
    var binding: FragmentSecondBinding? = null
    var list: ArrayList<QueryClass?> = ArrayList()
    var adapter: QueryAdapter? = null
    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        adapter = QueryAdapter(context, list)
        binding!!.queryrec.adapter = adapter
        val layoutManager: LinearLayoutManager = LinearLayoutManager(context)
        binding!!.queryrec.layoutManager = layoutManager
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer.startShimmer()
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("Query")
            .orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                list.clear()
                for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                    val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                    list.add(data)
                }
                adapter!!.notifyDataSetChanged()
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
        binding!!.swiprefresh.setOnRefreshListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.swiprefresh.isRefreshing = false
            Toast.makeText(context, "Data refresh", Toast.LENGTH_SHORT).show()
        }
        binding!!.sort.setOnClickListener {
            binding!!.sortboxview.visibility = View.VISIBLE
            binding!!.justview.visibility = View.VISIBLE
        }
        binding!!.justview.setOnClickListener {
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
        }
        binding!!.allsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "All", Toast.LENGTH_SHORT).show()
        }
        binding!!.rentout.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "किरायाने देणे (Rent)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "किरायाने देणे", Toast.LENGTH_SHORT).show()
        }
        binding!!.rentin.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "किरायाने पाहिजे (Rent)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "किरायाने पाहिजे", Toast.LENGTH_SHORT).show()
        }
        binding!!.needbuy.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "विकत पाहिजे (Buy)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "विकत पाहिजे", Toast.LENGTH_SHORT).show()
        }
        binding!!.sellout.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "विक्री आहे (Sell)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "विक्री आहे", Toast.LENGTH_SHORT).show()
        }
        binding!!.jobsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "नोकरी आहे (Job)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "नोकरी आहे", Toast.LENGTH_SHORT).show()
        }
        binding!!.needjob.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "नोकरी पाहिजे (Job)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "नोकरी पाहिजे", Toast.LENGTH_SHORT).show()
        }
        binding!!.needperson.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "कामासाठी व्यक्ती पाहिजे (Need)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "कामासाठी व्यक्ती पाहिजे", Toast.LENGTH_SHORT).show()
        }
        binding!!.othersort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "वरील पैकी वेगळे (Other)")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: QueryClass? = snapshot.toObject(QueryClass::class.java)
                        list.add(data)
                    }
                    adapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "other", Toast.LENGTH_SHORT).show()
        }
        return binding!!.root
    }
}