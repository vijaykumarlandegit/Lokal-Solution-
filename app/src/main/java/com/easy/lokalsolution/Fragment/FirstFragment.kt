package com.easy.lokalsolution.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.NewsAdapter
import com.easy.lokalsolution.Class.NewsClass
import com.easy.lokalsolution.databinding.FragmentFirstBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FirstFragment() : Fragment() {
    var binding: FragmentFirstBinding? = null
    var list: ArrayList<NewsClass?>? = null
    var newsAdapter: NewsAdapter? = null
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        list = ArrayList()
        newsAdapter = NewsAdapter(list!!, context)
        binding!!.newrec.adapter = newsAdapter
        val layoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        binding!!.newrec.layoutManager = layoutManager
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer.startShimmer()
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("News")
            .orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                list!!.clear()
                for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                    val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                    list!!.add(data)
                }
                newsAdapter!!.notifyDataSetChanged()
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
        binding!!.showrentswip.setOnRefreshListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.showrentswip.isRefreshing = false
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
                .document("NandedCity").collection("News")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "All", Toast.LENGTH_SHORT).show()
        }
        binding!!.newsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "News")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "news", Toast.LENGTH_SHORT).show()
        }
        binding!!.addvetsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Advertisement")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.setVisibility(View.GONE)
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Advertisement", Toast.LENGTH_SHORT).show()
        }
        binding!!.storysort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Story")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Story", Toast.LENGTH_SHORT).show()
        }
        binding!!.poertysort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Poetry")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Poetry", Toast.LENGTH_SHORT).show()
        }
        binding!!.announsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Announcement")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Announcement", Toast.LENGTH_SHORT).show()
        }
        binding!!.puzzlesort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Puzzle")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Puzzle", Toast.LENGTH_SHORT).show()
        }
        binding!!.gksort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "GK Question")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.setVisibility(View.GONE)
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "GK Question", Toast.LENGTH_SHORT).show()
        }
        binding!!.othersort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Other")
                .orderBy("time", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Other", Toast.LENGTH_SHORT).show()
        }
        return binding!!.root
    }
}