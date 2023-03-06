package com.example.viewpagerdemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment: FragmentActivity,private val list: List<Coordinate>) :
    FragmentStateAdapter(fragment) {

    private var listeners = ArrayList<(Event) -> Unit>()

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = list[position]
        listeners.add {
            fragment.setData(it)
        }
        return fragment
    }

    fun clickEvent(pos : Int,event: Event){
        listeners[pos](event)
    }
}
