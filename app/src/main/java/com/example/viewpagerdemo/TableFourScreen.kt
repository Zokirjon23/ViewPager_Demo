package com.example.viewpagerdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.viewpagerdemo.databinding.ScreenTableFourBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TableFourScreen(private val coordinate: List<Coordinate>, private val imageResId: Int) :
    TableFragment(R.layout.screen_table_four) {

    private val binding by viewBinding(ScreenTableFourBinding::bind)
    private val viewModel by viewModels<TableFourViewModel>()

    private var isMoveFood = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.table.setBackgroundResource(imageResId)


        viewModel.dataFlow.onEach { event ->
            when (event) {
                is Event.OnReceive -> {
                    when (event.type) {
                        FoodType.DRINK -> {
                            if (coordinate[4].currentPos < coordinate[4].list.size) {
                                val image = createImage(event)
//                                if (drinkListPos > 1)
//                                    image.elevation = 3f
                                image.x =
                                    coordinate[4].currentX() * binding.container.width - event.width / 2
                                image.y =
                                    coordinate[4].currentY() * binding.container.height - event.height
                                coordinate[4].currentPos++
                                animation(image, event)
                                moveView(image)
                            }
                        }
                        FoodType.BREAD -> {
                            createAction(2, event)
                        }
                        FoodType.LIQUID -> {
                            createAction(0, event)
                        }
                        FoodType.SALAD -> {
                            createAction(1, event)
                        }

                        FoodType.HEAVY -> {
                            createAction(0, event)
                        }
                    }
                }
                is Event.RemoveLast -> {
                    if (binding.table.childCount > 0) {
                        val removeView = binding.table.getChildAt(binding.table.childCount - 1)
                        removePosition(removeView.tag as FoodType)
                        binding.table.removeViewAt(binding.table.childCount - 1)
                    }

                    binding.table.animate().translationY(0f).translationX(0f).start()

                    binding.container.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .start()
                }
                is Event.ZoomOut -> {
                    binding.table.animate().translationY(0f).translationX(0f).start()

                    binding.container.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .start()
                }
                is Event.UserMoveFood -> {
                    isMoveFood = event.isTouchable
                }
            }

        }.launchIn(lifecycleScope)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setData(event: Event) {
        viewModel.setData(event)
    }

    @SuppressLint("MissingInflatedId")
    private fun createImage(event: Event.OnReceive): View {
//        val image = ImageView(requireContext())
//        Glide.with(this).load(event.resId).into(image)
//        image.layoutParams = FrameLayout.LayoutParams(event.width, event.height)
//        image.tag = event.type
//        image.setBackgroundColor(requireContext().getColor(R.color.image_color))
        val myView = LayoutInflater.from(requireContext()).inflate(R.layout.item, null, false)
        myView.layoutParams = FrameLayout.LayoutParams(event.width, event.height)
        myView.tag = event.type
//        val image = myView.findViewById<ImageView>(R.id.image_item)
//        Glide.with(this).load(event.resId).into(image)

        return myView
    }

    private fun animation(image: View, it: Event.OnReceive) {
        lifecycleScope.launch {
            delay(390)
            binding.table.addView(image)
        }

        val y = (binding.container.height / 2 - it.height / 2) - image.y
        val x = (binding.container.width / 2 - it.width / 2) - image.x
        binding.table.animate().translationX(x).translationY(y).start()

        binding.container.animate()
            .scaleX(2f)
            .scaleY(2f)
            .start()
    }

    private fun removePosition(type: FoodType) {
//        when (type) {
//            FoodType.DRINK -> drinkListPos--
//            FoodType.BREAD -> breadListPos--
//            FoodType.SALAD -> saladPos--
//            FoodType.LIQUID -> liquidListPos--
//            FoodType.HEAVY -> liquidListPos--
//        }
    }

    private fun createAction(pos: Int, event: Event.OnReceive) {
        if (coordinate[pos].currentPos < coordinate[pos].list.size) {
            val image = createImage(event)
            image.x = coordinate[pos].currentX() * binding.container.width - (event.width / 2)
            image.y = coordinate[pos].currentY() * binding.container.height - (event.height / 2)
            coordinate[pos].currentPos++
            animation(image, event)
            moveView(image)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun moveView(view: View) {
        var x = 0.0
        var y = 0.0

        view.setOnTouchListener { _, event ->
            if (isMoveFood) {
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
            }

//            Log.d(
//                "IMAGE_X_P", "drink ${(view.x + (view.width / 2)) / binding.container.width} " +
//                        "" + "${(view.y + view.height) / binding.container.height}"
//            )

                Log.d(
                    "IMAGE_X_P", "other ${(view.x + (view.width/2)) / binding.container.width} " +
                            "" + "${(view.y + (view.height/2)) / binding.container.height}"
                )
            true
        }
    }
}