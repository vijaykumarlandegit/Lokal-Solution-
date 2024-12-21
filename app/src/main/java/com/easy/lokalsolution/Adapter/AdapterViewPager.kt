package com.easy.lokalsolution.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterViewPager(fragmentActivity: FragmentActivity, var arr: ArrayList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    public override fun createFragment(position: Int): Fragment {
        return arr.get(position)
    }

    public override fun getItemCount(): Int {
        return arr.size
    }
}
