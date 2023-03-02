package com.example.viewpagerdemo

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.viewpagerdemo.databinding.FragmentInnerBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.EventListener


class InnerFragment : Fragment(R.layout.fragment_inner) {

    private val binding by viewBinding(FragmentInnerBinding::bind)
    private var drinkListPos = 0
    private var breadListPos = 0
    private var heavyListPos = 0
    private var liquidListPos = 0
    private val liquidFoods = listOf(Point(0.375f, 0.338f), Point(0.68f, 0.25f))
    private val heavyFoods = listOf(Point(0.479f, 0.253f), Point(0.61f, 0.378f))
    private val breads = listOf(Point(0.472f, 0.448f), Point(0.58f, 0.178f))
    private val drinks = listOf(
        Point(0.226f, 0.243f),
        Point(0.30f, 0.22f),
        Point(0.733f, 0.347f),
        Point(0.8f, 0.325f)
    )

    @SuppressLint("ClickableViewAccessibility")
    fun setData(it: Event) {
        when (it) {
            is Event.OnReceive -> {
                when (it.type) {
                    FoodType.DRINK -> {
                        if (drinkListPos < drinks.size) {
                            val image = createImage(it)

                            image.x = drinks[drinkListPos].x * binding.root.width - it.width / 2
                            image.y = drinks[drinkListPos].y * binding.root.height - it.height / 2
                            drinkListPos++
                            animation(image, it)
                        }
                    }
                    FoodType.BREAD -> {
                        if (breadListPos < breads.size) {
                            val image = createImage(it)
                            image.x = breads[breadListPos].x * binding.root.width - it.width / 2
                            image.y = breads[breadListPos].y * binding.root.height - it.height / 2
                            breadListPos++
                            animation(image, it)
                        }
                    }
                    FoodType.LIQUID -> {
                        if (liquidListPos < liquidFoods.size) {
                            val image = createImage(it)
                            image.x =
                                liquidFoods[liquidListPos].x * binding.root.width - it.width / 2
                            image.y =
                                liquidFoods[liquidListPos].y * binding.root.height - it.height / 2
                            liquidListPos++
                            animation(image, it)
                        }
                    }
                    FoodType.HEAVY -> {
                        if (heavyListPos < heavyFoods.size) {
                            val image = createImage(it)
                            image.x = heavyFoods[heavyListPos].x * binding.root.width - it.width / 2
                            image.y =
                                heavyFoods[heavyListPos].y * binding.root.height - it.height / 2
                            heavyListPos++
                            animation(image, it)
                        }
                    }
                }
            }
            is Event.RemoveLast -> {
                if (binding.frame.childCount > 0) {
                   val removeView = binding.frame.getChildAt(binding.root.childCount-1)
                    removePosition(removeView.tag as FoodType)
                    binding.frame.removeView(removeView)
                }

                binding.frame.animate().translationY(0f).translationX(0f).start()

                binding.root.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .start()
            }
            is Event.ZoomOut -> {
                binding.frame.animate().translationY(0f).translationX(0f).start()

                binding.root.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .start()
            }
        }
    }

    private fun createImage(event: Event.OnReceive): ImageView {
        val image = ImageView(requireContext())
        Glide.with(this).load(event.resId).into(image)
        image.layoutParams = FrameLayout.LayoutParams(event.width,event.height)
        image.tag = event.type
        return image
    }

    private fun animation(image: ImageView, it: Event.OnReceive) {
        lifecycleScope.launch {
            delay(390)
            binding.frame.addView(image)
        }

        val y = (binding.root.height / 2 - it.height / 2) - image.y
        val x = (binding.root.width / 2 - it.width / 2) - image.x
        binding.frame.animate().translationX(x).translationY(y).start()

        binding.root.animate()
            .scaleX(2f)
            .scaleY(2f)
            .start()
    }

    private fun removePosition(type: FoodType){
        when(type){
            FoodType.DRINK -> drinkListPos--
            FoodType.BREAD -> breadListPos--
            FoodType.HEAVY -> heavyListPos--
            FoodType.LIQUID -> liquidListPos--
        }
    }
}

/**
 *
 *
 *
 */
