package com.toy.loginwork.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.toy.loginwork.ui.fragment.HomeFragment
import com.toy.loginwork.ui.fragment.PictureFragment

class ViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int  = 2

    override fun createFragment(position: Int): Fragment {
        var fragment =
            return when(position){
                0-> {
                    HomeFragment()
                }
                1-> PictureFragment()
                else-> HomeFragment()
            }
    }
}