package com.example.viewpagerdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.viewpagerdemo.databinding.ScreenTableFourBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TableFourScreen : TableFragment(R.layout.screen_table_four) {

    private val binding by viewBinding(ScreenTableFourBinding::bind)
    private var drinkListPos = 0
    private var breadListPos = 0
    private var heavyListPos = 0
    private var liquidListPos = 0
    private var saladPos = 0
    private val liquidFoods = listOf(
        Point(0.248f, 0.212f),
        Point(0.495f, 0.149f),
        Point(0.417f, 0.379f),
        Point(0.702f, 0.302f)
    )
    private val salads = listOf(
        Point(0.7f, 0.237f),
        Point(0.181f, 0.391f),
        Point(0.35f, 0.23f),
        Point(0.56f, 0.384f)
    )

    private val breads = listOf(Point(0.441f, 0.294f))


    private val drinks = listOf(
        Point(0.228f, 0.345f),
        Point(0.728f, 0.216f),
        Point(0.382f, 0.524f),
        Point(0.934f, 0.353f)
    )

    private val heavy = listOf(
        Point(0.585f, 0.260f),
        Point(0.325f, 0.34f),
    )




    @SuppressLint("ClickableViewAccessibility")
    override fun setData(event: Event) {
        when (event) {
            is Event.OnReceive -> {
                when (event.type) {
                    FoodType.DRINK -> {
                        if (drinkListPos < drinks.size) {
                            val image = createImage(event)
                            if (drinkListPos > 1)
                                image.elevation = 3f
                            image.x = drinks[drinkListPos].x * binding.root.width - event.width
                            image.y = drinks[drinkListPos].y * binding.root.height - event.height
                            drinkListPos++
                            animation(image, event)
                            moveView(image)
                        }
                    }
                    FoodType.BREAD -> {
                        if (breadListPos < breads.size) {
                            val image = createImage(event)
                            image.x = breads[breadListPos].x * binding.root.width - event.width/2
                            image.y =
                                breads[breadListPos].y * binding.root.height - event.height/2
                            breadListPos++
                            animation(image, event)
                            moveView(image)
                        }
                    }
                    FoodType.LIQUID -> {
                        if (liquidListPos < liquidFoods.size) {
                            val image = createImage(event)
                            image.elevation = 2f
                            image.x =
                                (liquidFoods[liquidListPos].x * binding.root.width - event.width/2)
                            image.y =
                                (liquidFoods[liquidListPos].y *  binding.root.height - event.height/2)
                            liquidListPos++
                            animation(image, event)
                            moveView(image)
                        }
                    }
                    FoodType.SALAD -> {
                        if (saladPos < salads.size) {
                            val image = createImage(event)
                            image.elevation = 1f
                            image.x =
                                salads[saladPos].x * binding.root.width - event.width/2
                            image.y =
                                salads[saladPos].y * binding.root.height - event.height/2
                            saladPos++
                            animation(image, event)
                            moveView(image)
                        }
                    }

                    FoodType.HEAVY -> {
                        if (heavyListPos < heavy.size) {
                            val image = createImage(event)
                            image.elevation = 1f
                            image.x =
                                heavy[heavyListPos].x * binding.root.width - event.width/2
                            image.y =
                                heavy[heavyListPos].y *binding.root.height - event.height/2
                            heavyListPos++
                            animation(image, event)
                            moveView(image)
                        }
                    }
                }
            }
            is Event.RemoveLast -> {
                if (binding.frame.childCount > 0) {
                    val removeView = binding.frame.getChildAt(binding.root.childCount - 1)
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
        image.layoutParams = FrameLayout.LayoutParams(event.width, event.height)
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

    private fun removePosition(type: FoodType) {
        when (type) {
            FoodType.DRINK -> drinkListPos--
            FoodType.BREAD -> breadListPos--
            FoodType.SALAD -> saladPos--
            FoodType.LIQUID -> liquidListPos--
            FoodType.HEAVY -> heavyListPos--
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun moveView(view: View) {
        var x = 0.0
        var y = 0.0

        view.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    x = view.x.toDouble() - event.rawX
                    y = view.y.toDouble() - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    view.y = event.rawY + y.toFloat()
                    view.x = event.rawX + x.toFloat()
                }
            }


//            Log.d(
//                "IMAGE_X_P", "drink ${(view.x + view.width) / binding.root.width} " +
//                        "" + "${(view.y + view.height) / binding.root.height}"
//            )

            Log.d(
                "IMAGE_X_P", "other ${(view.x + (view.width/2)) / binding.root.width} " +
                        "" + "${(view.y + (view.height/2)) / binding.root.height}"
            )
            true
        }
    }
}