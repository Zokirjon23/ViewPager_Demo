package com.example.viewpagerdemo

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

open class OnSwipeTouchListener: View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when{
            event.x > v.x -> onSwipeRight()
            event.x < v.x -> onSwipeLeft()
            event.y < v.y -> onSwipeTop()
            event.y > v.y -> onSwipeBottom()
        }
        return false
    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}

//    open fun onClick(){}
}