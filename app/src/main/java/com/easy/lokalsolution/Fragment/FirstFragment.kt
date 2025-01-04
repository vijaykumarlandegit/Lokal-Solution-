package com.easy.lokalsolution.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.NewsAdapter
import com.easy.lokalsolution.Factory.PostViewModelFactory
import com.easy.lokalsolution.Repository.PostRepository
import com.easy.lokalsolution.ViewModel.PostViewModel
import com.easy.lokalsolution.databinding.FragmentFirstBinding
import com.google.firebase.firestore.FirebaseFirestore


class FirstFragment() : Fragment() {
    var binding: FragmentFirstBinding? = null

     lateinit var postviewModel:PostViewModel
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)





        //MVVM
        val firebase = FirebaseFirestore.getInstance()
        val repository=PostRepository(firebase)
        postviewModel= ViewModelProvider(this, PostViewModelFactory(repository))[PostViewModel::class.java]



          binding!!.newrec.layoutManager =  LinearLayoutManager(context)
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer.startShimmer()


        postviewModel.allpost.observe(viewLifecycleOwner) {
            binding!!.newrec.adapter = NewsAdapter(it,requireContext())
            binding!!.shimmer.visibility = View.GONE
            binding!!.shimmer.stopShimmer()
        }
        postviewModel.loadAllPost()


        binding!!.showrentswip.setOnRefreshListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.allpost.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadAllPost()
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
            postviewModel.allpost.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadAllPost()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "All", Toast.LENGTH_SHORT).show()
        }




        binding!!.newsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlynews.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyNews()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "news", Toast.LENGTH_SHORT).show()
        }





        binding!!.addvetsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlyAdvertisement.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyAdvertisement()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Advertisement", Toast.LENGTH_SHORT).show()
        }




        binding!!.storysort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlyStory.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyStory()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Story", Toast.LENGTH_SHORT).show()
        }





        binding!!.poertysort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlyPoetry.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyPoetry()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Poetry", Toast.LENGTH_SHORT).show()
        }




        binding!!.announsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlyAnnouncement.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyAnnouncement()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Announcement", Toast.LENGTH_SHORT).show()
        }



        binding!!.puzzlesort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlyPuzzle.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyPuzzle()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Puzzle", Toast.LENGTH_SHORT).show()
        }



        binding!!.gksort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlyQuestion.observe(viewLifecycleOwner) {
                binding!!.newrec.adapter = NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyQuestion()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "GK Question", Toast.LENGTH_SHORT).show()
        }





        binding!!.othersort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            postviewModel.onlyOther.observe(viewLifecycleOwner){
                binding!!.newrec.adapter=NewsAdapter(it,requireContext())
                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            postviewModel.loadOnlyOther()
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "Other", Toast.LENGTH_SHORT).show()
        }
        return binding!!.root
    }
}