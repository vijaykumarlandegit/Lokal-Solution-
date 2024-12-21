package com.easy.lokalsolution.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.easy.lokalsolution.Adapter.NewsAdapter
import com.easy.lokalsolution.Class.NewsClass
import com.easy.lokalsolution.databinding.FragmentFirstBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

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
        newsAdapter = NewsAdapter(list!!, getContext())
        binding!!.newrec.setAdapter(newsAdapter)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        binding!!.newrec.setLayoutManager(layoutManager)
        binding!!.shimmer.setVisibility(View.VISIBLE)
        binding!!.shimmer.startShimmer()
        FirebaseFirestore.getInstance().collection("Nanded")
            .document("NandedCity").collection("News")
            .orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                    list!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                        val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                        list!!.add(data)
                    }
                    newsAdapter!!.notifyDataSetChanged()
                    binding!!.shimmer.setVisibility(View.GONE)
                    binding!!.shimmer.stopShimmer()
                }
            })
        binding!!.showrentswip.setOnRefreshListener(object : OnRefreshListener {
            public override fun onRefresh() {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.showrentswip.setRefreshing(false)
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
                    .document("NandedCity").collection("News")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "All", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.newsort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "News")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "news", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.addvetsort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "Advertisement")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "Advertisement", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.storysort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "Story")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "Story", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.poertysort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "Poetry")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "Poetry", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.announsort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "Announcement")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "Announcement", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.puzzlesort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "Puzzle")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "Puzzle", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.gksort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "GK Question")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "GK Question", Toast.LENGTH_SHORT).show()
            }
        })
        binding!!.othersort.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                binding!!.shimmer.setVisibility(View.VISIBLE)
                binding!!.shimmer.startShimmer()
                FirebaseFirestore.getInstance().collection("Nanded")
                    .document("NandedCity").collection("News")
                    .whereEqualTo("type", "Other")
                    .orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                        public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                            list!!.clear()
                            for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                                val data: NewsClass? = snapshot.toObject(NewsClass::class.java)
                                list!!.add(data)
                            }
                            newsAdapter!!.notifyDataSetChanged()
                            binding!!.shimmer.setVisibility(View.GONE)
                            binding!!.shimmer.stopShimmer()
                        }
                    })
                binding!!.sortboxview.setVisibility(View.GONE)
                binding!!.justview.setVisibility(View.GONE)
                Toast.makeText(getContext(), "Other", Toast.LENGTH_SHORT).show()
            }
        })
        return binding!!.getRoot()
    }
}