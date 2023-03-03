package com.example.viewpagerdemo

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class TableFragment(@LayoutRes id : Int) : Fragment(id) {
    abstract fun setData(event: Event)
}