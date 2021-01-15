package com.example.esoftwarica.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter (
    private val listFragments : ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
        ): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {

        return listFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragments[position]
    }
}