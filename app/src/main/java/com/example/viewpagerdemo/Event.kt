package com.example.viewpagerdemo

import android.view.View

sealed interface Event {
    class OnReceive(val resId : Int, val width : Int, val height : Int,val type: FoodType) : Event
    object RemoveLast : Event
    object ZoomOut : Event
}


