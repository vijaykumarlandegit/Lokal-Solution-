package com.easy.lokalsolution.Repository

import android.util.Log
import com.easy.lokalsolution.Model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
class UserRepository(private val apiService: ApiService) {
    suspend fun fetchUsers(): List<User> = apiService.getUsers()
}
*/

/*class UserRepository(private val firebaseDatabase: FirebaseDatabase) {

    suspend fun fetchUsers(): List<User> {
        return try {
            val snapshot = firebaseDatabase.reference.child("aa").get().await()
            snapshot.children.map { child ->
                val user = child.getValue<User>()
                user ?: User("tt", "tt", 0, "") // Default values if null
            }
        } catch (e: Exception) {
            emptyList() // Return an empty list if there's an error
        }
    }
}*/

class UserRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    suspend fun fetchUsers(): List<User> {
        return try {
            val snapshot = firestore.collection("AllUserG").get().await()
            snapshot.documents.map { document ->
                User(
                    id = document.getString("id") ?: "N/A",
                    name = document.getString("name") ?: "N/A",
                    age = document.getLong("age")?.toInt() ?: 0,
                    imageUrl = document.getString("imageUrl") ?: ""
                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    }
}

