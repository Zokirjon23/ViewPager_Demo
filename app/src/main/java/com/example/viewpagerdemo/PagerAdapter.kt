package com.example.viewpagerdemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(lifeScope: Lifecycle, fm: FragmentManager,private val list: List<TableFragment>) :
    FragmentStateAdapter(fm, lifeScope) {

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
