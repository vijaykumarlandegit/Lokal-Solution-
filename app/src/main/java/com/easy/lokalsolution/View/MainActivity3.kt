package com.easy.lokalsolution.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.easy.lokalsolution.ViewModel.AdditionViewModel
import com.easy.lokalsolution.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var viewModel: AdditionViewModel
    lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[AdditionViewModel::class.java]


        // Observe the result LiveData
        viewModel.result.observe(this) { result ->
            binding.resultText.text = result.toString()
        }

        // Handle button click
        binding.addButton.setOnClickListener {
            val number1 =  binding.number1EditText.text.toString().toIntOrNull() ?: 0
            val number2 =  binding.number2EditText.text.toString().toIntOrNull() ?: 0
            viewModel.addNumbers(number1, number2)
        }
    }
}
