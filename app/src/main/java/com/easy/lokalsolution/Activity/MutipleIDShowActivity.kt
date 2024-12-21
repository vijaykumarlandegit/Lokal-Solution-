package com.easy.lokalsolution.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.easy.lokalsolution.Adapter.IdAdapter
import com.easy.lokalsolution.Class.IdClass
import com.easy.lokalsolution.databinding.ActivityMutipleIdshowBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class MutipleIDShowActivity() : AppCompatActivity() {
    var binding: ActivityMutipleIdshowBinding? = null
    var list1: ArrayList<IdClass?>? = null
    var idAdapter: IdAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMutipleIdshowBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())
        list1 = ArrayList()
        idAdapter = IdAdapter(this@MutipleIDShowActivity, list1)
        binding!!.carmechrecyview.setAdapter(idAdapter)
        linearLayoutManager = LinearLayoutManager(this@MutipleIDShowActivity)
        binding!!.carmechrecyview.setLayoutManager(linearLayoutManager)
        val type: String? = getIntent().getStringExtra("type")
        if ((type == "11")) {
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            binding!!.typename.setText("Mechanic")
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Mechanic")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "12")) {
            binding!!.typename.setText("Plumber")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Plumber")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "13")) {
            binding!!.typename.setText("Eletrician")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Electrician")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "14")) {
            binding!!.typename.setText("Electronic Technician")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id")
                .whereEqualTo("type", "Electronic Technician")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "15")) {
            binding!!.typename.setText("Painter")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Painter")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "16")) {
            binding!!.typename.setText("Carpenter")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Carpenter")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "17")) {
            binding!!.typename.setText("Photographer")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Photographer")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "18")) {
            binding!!.typename.setText("Home Shifting")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Home Shifting")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "19")) {
            binding!!.typename.setText("Event Management")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Event Management")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        } else if ((type == "20")) {
            binding!!.typename.setText("Other")
            binding!!.shimmer.setVisibility(View.VISIBLE)
            binding!!.shimmer.startShimmer()
            FirebaseFirestore.getInstance().collection("Nanded")
                .document("NandedCity").collection("Id").whereEqualTo("type", "Other")
                .get().addOnSuccessListener(object : OnSuccessListener<QuerySnapshot> {
                    public override fun onSuccess(queryDocumentSnapshots: QuerySnapshot) {
                        list1!!.clear()
                        for (snapshot: DocumentSnapshot in queryDocumentSnapshots.getDocuments()) {
                            val data: IdClass? = snapshot.toObject(IdClass::class.java)
                            list1!!.add(data)
                        }
                        idAdapter!!.notifyDataSetChanged()
                        binding!!.idcount.setText(list1!!.size.toString() + " IDs")
                        binding!!.shimmer.setVisibility(View.GONE)
                        binding!!.shimmer.stopShimmer()
                    }
                })
        }
        binding!!.cmcreatid.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(this@MutipleIDShowActivity, AddIdActivity::class.java)
                startActivity(intent)
            }
        })
        binding!!.back.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                finish()
            }
        })
    }
}