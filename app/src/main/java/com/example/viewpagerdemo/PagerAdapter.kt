package com.example.viewpagerdemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {

    private var listeners = ArrayList<(Event) -> Unit>()
    private val coordinates = listOf(
        table2Coordinate, table4Coordinate, table6Coordinate, table8Coordinate, table10Coordinate,
        table12Coordinate
    )
    private val images = listOf(
        R.drawable.table2,
        R.drawable.table4,
        R.drawable.table6,
        R.drawable.table8,
        R.drawable.table10,
        R.drawable.table12
    )

    override fun getItemCount() = images.size

    override fun createFragment(pos: Int): Fragment {
        val fragment = TableFourScreen(coordinates[pos], images[pos])
        listeners.add {
            fragment.setData(it)
        }
        return fragment
    }

    fun clickEvent(pos: Int, event: Event) {
        listeners[pos](event)
    }
}
