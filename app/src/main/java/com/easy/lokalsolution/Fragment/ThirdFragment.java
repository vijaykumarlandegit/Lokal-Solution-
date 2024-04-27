package com.easy.lokalsolution.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.lokalsolution.Activity.MutipleIDShowActivity;
import com.easy.lokalsolution.databinding.FragmentThirdBinding;


public class ThirdFragment extends Fragment {


    public ThirdFragment() {

    }

    FragmentThirdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);


        binding.mechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","11");
                startActivity(intent);
            }
        });
        binding.plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","12");
                startActivity(intent);
            }
        });
        binding.electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","13");
                startActivity(intent);
            }
        });
        binding.eletronictech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","14");
                startActivity(intent);
            }
        });
        binding.painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","15");
                startActivity(intent);
            }
        });
        binding.carpaenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","16");
                startActivity(intent);
            }
        });
        binding.photographer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","17");
                startActivity(intent);
            }
        });
        binding.fomeshifting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","18");
                startActivity(intent);
            }
        });
        binding.managment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","19");
                startActivity(intent);
            }
        }); binding.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MutipleIDShowActivity.class);
                intent.putExtra("type","20");
                startActivity(intent);
            }
        });



        return binding.getRoot();
    }
}