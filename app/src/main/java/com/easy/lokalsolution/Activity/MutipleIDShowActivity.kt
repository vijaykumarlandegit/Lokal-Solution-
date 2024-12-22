package com.easy.lokalsolution.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.IdAdapter
import com.easy.lokalsolution.Class.IdClass
import com.easy.lokalsolution.databinding.ActivityMutipleIdshowBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class MutipleIDShowActivity() : AppCompatActivity() {
    var binding: ActivityMutipleIdshowBinding? = null
    var list1: ArrayList<IdClass?>? = null
    var idAdapter: IdAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMutipleIdshowBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        list1 = ArrayList()
        idAdapter = IdAdapter(this@MutipleIDShowActivity, list1)
        binding!!.carmechrecyview.adapter = idAdapter
        linearLayoutManager = LinearLayoutManager(this@MutipleIDShowActivity)
        binding!!.carmechrecyview.layoutManager = linearLayoutManager
        val type: String? = getIntent().getStringExtra("type")
        if ((type == "11")) {
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            binding!!.typename.text = "Mechanic"
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Mechanic")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "12")) {
            binding!!.typename.text = "Plumber"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Plumber")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "13")) {
            binding!!.typename.text = "Eletrician"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Electrician")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "14")) {
            binding!!.typename.text = "Electronic Technician"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id")
                .whereEqualTo("type", "Electronic Technician")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "15")) {
            binding!!.typename.text = "Painter"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Painter")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "16")) {
            binding!!.typename.text = "Carpenter"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Carpenter")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "17")) {
            binding!!.typename.text = "Photographer"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Photographer")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "18")) {
            binding!!.typename.text = "Home Shifting"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Home Shifting")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "19")) {
            binding!!.typename.setText("Event Management")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Event Management")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        } else if ((type == "20")) {
            binding!!.typename.text = "Other"
            binding!!.shimmer.visibility = View.VISIBLE
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Other")
                .get().addOnSuccessListener { queryDocumentSnapshots ->
                    list1!!.clear()
                    for (snapshot: DocumentSnapshot in queryDocumentSnapshots.documents) {
                        val data: IdClass? = snapshot.toObject(IdClass::class.java)
                        list1!!.add(data)
                    }
                    idAdapter!!.notifyDataSetChanged()
                    binding!!.idcount.text = list1!!.size.toString() + " IDs"
                    binding!!.shimmer.visibility = View.GONE
                    binding!!.shimmer.stopShimmer()
                }
        }
        binding!!.cmcreatid.setOnClickListener {
            val intent: Intent = Intent(this@MutipleIDShowActivity, AddIdActivity::class.java)
            startActivity(intent)
        }
        binding!!.back.setOnClickListener { finish() }
    }
}