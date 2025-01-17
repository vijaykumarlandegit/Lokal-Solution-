package com.easy.lokalsolution.Personal


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.databinding.ActivityPersonBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PersonActivity : AppCompatActivity() {

    private val personViewModel: PersonViewModel by viewModels()

    private lateinit var database: PersonDatabase
     lateinit var binding: ActivityPersonBinding
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = PersonDatabase.getDatabase(this)

        // Insert data in a background thread
       /* lifecycleScope.launch(Dispatchers.IO) {
            // Insert data into the database on a background thread
            database.userDao().insert(PersonEntity(id = 11, name = "John Doe", age = 25))
        }*/
        GlobalScope.launch {
            database.userDao().insert(PersonEntity(id = 11, name = "John Doe", age = 25))
        }

      /*  // Fetch data in a background thread
        lifecycleScope.launch(Dispatchers.IO) {
            val users = database.userDao().getAllUsers()
            // Use Dispatchers.Main to update UI after fetching data
            withContext(Dispatchers.Main) {
                Log.d("PersonActivity", "Users: $users")
            }
        }*/
    }
}
