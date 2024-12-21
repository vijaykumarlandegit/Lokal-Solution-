package com.easy.lokalsolution.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.easy.lokalsolution.Activity.MutipleIDShowActivity
import com.easy.lokalsolution.databinding.FragmentThirdBinding

class ThirdFragment() : Fragment() {
    var binding: FragmentThirdBinding? = null
    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        binding!!.mechanic.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "11")
                startActivity(intent)
            }
        })
        binding!!.plumber.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "12")
                startActivity(intent)
            }
        })
        binding!!.electrician.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "13")
                startActivity(intent)
            }
        })
        binding!!.eletronictech.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "14")
                startActivity(intent)
            }
        })
        binding!!.painter.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "15")
                startActivity(intent)
            }
        })
        binding!!.carpaenter.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "16")
                startActivity(intent)
            }
        })
        binding!!.photographer.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "17")
                startActivity(intent)
            }
        })
        binding!!.fomeshifting.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "18")
                startActivity(intent)
            }
        })
        binding!!.managment.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "19")
                startActivity(intent)
            }
        })
        binding!!.other.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(getActivity(), MutipleIDShowActivity::class.java)
                intent.putExtra("type", "20")
                startActivity(intent)
            }
        })
        return binding!!.getRoot()
    }
}