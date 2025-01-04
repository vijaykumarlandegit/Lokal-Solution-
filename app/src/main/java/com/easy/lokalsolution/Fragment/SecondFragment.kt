package com.easy.lokalsolution.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.QueryAdapter
import com.easy.lokalsolution.Factory.QueryViewModelFactory
import com.easy.lokalsolution.Repository.QueryRepository
import com.easy.lokalsolution.ViewModel.QueryViewModel
import com.easy.lokalsolution.databinding.FragmentSecondBinding
import com.google.firebase.firestore.FirebaseFirestore

class SecondFragment() : Fragment() {
    var binding: FragmentSecondBinding? = null
     lateinit var viewModel: QueryViewModel
    lateinit var adapter: QueryAdapter
    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        adapter = QueryAdapter(context, emptyList())
        binding!!.queryrec.adapter = adapter
         binding!!.queryrec.layoutManager = LinearLayoutManager(context)
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer.startShimmer()


        val firebase=FirebaseFirestore.getInstance()
        val repository=QueryRepository(firebase)
         viewModel= ViewModelProvider(this, QueryViewModelFactory(repository))[QueryViewModel::class.java]

        viewModel.allquery.observe(viewLifecycleOwner){
            adapter.updateData(it)

            binding!!.shimmer.visibility = View.GONE
            binding!!.shimmer.stopShimmer()
        }
        viewModel.loadAllQuery()









        binding!!.swiprefresh.setOnRefreshListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allquery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllQuery()
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
            viewModel.allquery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "All", Toast.LENGTH_SHORT).show()
        }




        binding!!.rentout.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allRentOutQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllRentOutQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "किरायाने देणे", Toast.LENGTH_SHORT).show()
        }




        binding!!.rentin.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allNeedByRentQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllNeedByRentQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "किरायाने पाहिजे", Toast.LENGTH_SHORT).show()
        }



        binding!!.needbuy.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allBuyQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllBuyQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "विकत पाहिजे", Toast.LENGTH_SHORT).show()
        }




        binding!!.sellout.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allSellQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllSellQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "विक्री आहे", Toast.LENGTH_SHORT).show()
        }




        binding!!.jobsort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allJobQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllJobQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "नोकरी आहे", Toast.LENGTH_SHORT).show()
        }



        binding!!.needjob.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allNeedJobQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllNeedJobQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "नोकरी पाहिजे", Toast.LENGTH_SHORT).show()
        }



        binding!!.needperson.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allNeedWorkerQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllNeedWorkerQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "कामासाठी व्यक्ती पाहिजे", Toast.LENGTH_SHORT).show()
        }



        binding!!.othersort.setOnClickListener {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            viewModel.allOtherQuery.observe(viewLifecycleOwner){
                adapter.updateData(it)

                binding!!.shimmer.visibility = View.GONE
                binding!!.shimmer.stopShimmer()
            }
            viewModel.loadAllOtherQuery()
            binding!!.sortboxview.visibility = View.GONE
            binding!!.justview.visibility = View.GONE
            Toast.makeText(context, "other", Toast.LENGTH_SHORT).show()
        }
        return binding!!.root
    }
}