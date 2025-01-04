package com.easy.lokalsolution.Repository

import android.util.Log
import com.easy.lokalsolution.Class.QueryClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class QueryRepository(private val database: FirebaseFirestore) {

    suspend fun fetchAllQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }
    }


    suspend fun fetchRentOutQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "किरायाने देणे (Rent)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    }
     suspend fun fetchNeedByRentQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "किरायाने पाहिजे (Rent)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    }
      suspend fun fetchBuyQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "विकत पाहिजे (Buy)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    }

     suspend fun fetchSellQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "विक्री आहे (Sell)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    }


     suspend fun fetchJobQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "नोकरी आहे (Job)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    }



     suspend fun fetchNeedJobQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "नोकरी पाहिजे (Job)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    } suspend fun fetchNeedWorkerQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "कामासाठी व्यक्ती पाहिजे (Need)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    }
    suspend fun fetchOtherQuery(): List<QueryClass> {

        return try {
            val snapshot = database.collection("Nanded")
                .document("NandedCity").collection("Query")
                .whereEqualTo("type", "वरील पैकी वेगळे (Other)")
                .orderBy("time", Query.Direction.DESCENDING).get().await()
            snapshot.documents.map {
                QueryClass(
                    type = it.getString("type") ?: "",
                    subtype = it.getString("subtype") ?: "",
                    money = it.getString("money") ?: "",
                    address = it.getString("address") ?: "",
                    disc = it.getString("disc") ?: "",
                    note = it.getString("note") ?: "",
                    uname = it.getString("uname") ?: "",
                    contact = it.getString("contact") ?: "",
                    whatsapp = it.getString("whatsapp") ?: "",
                    contactime = it.getString("contactime") ?: "",
                    image = it.getString("image") ?: "",
                    id = it.getString("id") ?: "",
                    time = it.getLong("time") ?: 0,
                )
            }
        } catch (e: Exception) {
            Log.e("Error", "Error : $e", e)
            emptyList()
        }


    }



}