package com.easy.lokalsolution.Student


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.databinding.ActivityStudentBinding

class StudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentBinding
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database, repository, and ViewModel with factory
        val studentDao = StudentDatabase.getDatabase(this).studentDao()
        val repository = StudentRepository(studentDao)

        studentViewModel = ViewModelProvider(this, StudentViewModelFactory(repository))[StudentViewModel::class.java]

        // Setup RecyclerView
        studentAdapter = StudentAdapter(emptyList())
        binding.showalluserrecyclerview.apply {
            layoutManager = LinearLayoutManager(this@StudentActivity)
            adapter = studentAdapter
        }
        // Observe and update UI
        studentViewModel.allStudents.observe(this) { students ->
            studentAdapter.updatelist(students)
        }

        // Add student button click
        binding.buttonaddstuudent.setOnClickListener {
            val id = binding.addstudentid.text.toString().toIntOrNull()
            val name = binding.addstudentname.text.toString()
            val age = binding.addstudentage.text.toString().toIntOrNull()

            if (id != null && age != null && name.isNotBlank()) {
                val student = Student(id, name, age)
                studentViewModel.addStudent(student)
                Toast.makeText(this, "Student added!", Toast.LENGTH_SHORT).show()
                clearInputFields()
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        // Update student button click
        binding.buttonupdatestudent.setOnClickListener {
            val id = binding.updatestudentid.text.toString().toIntOrNull()
            val name = binding.updatestudentname.text.toString()
            val age = binding.updatestudentage.text.toString().toIntOrNull()

            if (id != null && age != null && name.isNotBlank()) {
                val student = Student(id, name, age)
                studentViewModel.updateStudent(student)
                Toast.makeText(this, "Student updated!", Toast.LENGTH_SHORT).show()
                clearInputFields()
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        // Delete student button click
        binding.buttondeletestuudentid.setOnClickListener {
            val id = binding.deletestudentid.text.toString().toIntOrNull()

            if (id != null) {
                val student = Student(id, "", 0) // Only ID is needed for deletion
                studentViewModel.deleteStudent(student)
                Toast.makeText(this, "Student deleted!", Toast.LENGTH_SHORT).show()
                clearInputFields()
            } else {
                Toast.makeText(this, "Please enter a valid ID!", Toast.LENGTH_SHORT).show()
            }
        }

        // Fetch student by ID button click
        binding.buttonfetchstuudentid.setOnClickListener {
            val id = binding.fetchstudentid.text.toString().toIntOrNull()

            if (id != null) {
                studentViewModel.fetchStudentById(id) { student ->
                    if (student != null) {
                        binding.showfetchname.text = "Name: ${student.name}"
                        binding.showfetchage.text = "Age: ${student.age}"
                    } else {
                        Toast.makeText(this, "Student not found!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter a valid ID!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearInputFields() {
        binding.addstudentid.text.clear()
        binding.addstudentname.text.clear()
        binding.addstudentage.text.clear()
        binding.updatestudentid.text.clear()
        binding.updatestudentname.text.clear()
        binding.updatestudentage.text.clear()
        binding.deletestudentid.text.clear()
        binding.fetchstudentid.text.clear()
    }
}
