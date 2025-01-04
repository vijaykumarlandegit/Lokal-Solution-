package com.easy.lokalsolution.Repository

import android.util.Log
import com.easy.lokalsolution.Class.NewsClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class PostRepository(private val firebase: FirebaseFirestore) {

    suspend fun fetchAllPost():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    } suspend fun fetchOnlyNews():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "News")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    } suspend fun fetchOnlyAdvertisement():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Advertisement")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    } suspend fun fetchOnlyStory():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Story")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    }suspend fun fetchOnlyPoetry():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Poetry")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    }suspend fun fetchOnlyAnnouncement():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Announcement")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    }suspend fun fetchOnlyPuzzle():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Puzzle")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    }suspend fun fetchOnlyQuestion():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "GK Question")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

                )
            }.also { userList ->
                // Log the fetched data
                Log.d("UserRepository", "Fetched users: $userList")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            emptyList()
        }
    }suspend fun fetchOnlyOther():List<NewsClass> {
        return try {
            val snapshot = firebase.collection("Nanded")
                .document("NandedCity").collection("News")
                .whereEqualTo("type", "Other")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map { document ->
                NewsClass(
                    type = document.getString("type") ?: "N/A",
                    title = document.getString("title") ?: "N/A",
                    disc = document.getString("disc") ?: "N/A",
                    usrerid = document.getString("usrerid") ?: "N/A",
                    id = document.getString("id") ?: "N/A",
                     image = document.getString("image") ?: "N/A",
                    time = document.getLong("time")?.toLong() ?: 0

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
