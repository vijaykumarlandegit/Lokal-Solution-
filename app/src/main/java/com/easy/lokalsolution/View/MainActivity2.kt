package com.easy.lokalsolution.View
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.UserAdapter
import com.easy.lokalsolution.Factory.UserViewModelFactory
import com.easy.lokalsolution.Repository.UserRepository
import com.easy.lokalsolution.ViewModel.UserViewModel
import com.easy.lokalsolution.databinding.ActivityMain2Binding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firestore
        val firestore = FirebaseFirestore.getInstance()

        // Pass Firestore to the repository
        val repository = UserRepository(firestore)

        // Initialize ViewModel using ViewModelProvider and a custom factory
        viewModel = ViewModelProvider(this, UserViewModelFactory(repository))[UserViewModel::class.java]

        binding.recyclerView2.layoutManager = LinearLayoutManager(this)

        // Observe data and set adapter
        viewModel.users.observe(this) { users ->
            Log.d("MainActivity2", "Observed users: $users")
            binding.recyclerView2.adapter = UserAdapter(users)
        }


        // Load users
        viewModel.loadUsers()
    }
}

 /*
class MainActivity2 : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    lateinit var binding:ActivityMain2Binding

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val repository = UserRepository(Networking.apiService)

        // Initialize ViewModel using ViewModelProvider and a custom factory
        viewModel = ViewModelProvider(this, UserViewModelFactory(repository))[UserViewModel::class.java]

         binding.recyclerView2.layoutManager = LinearLayoutManager(this)

        // Observe data and set adapter
        viewModel.users.observe(this) { users ->
            binding.recyclerView2.adapter = UserAdapter(users)
        }

        // Load users
        viewModel.loadUsers()
    }

}*/
