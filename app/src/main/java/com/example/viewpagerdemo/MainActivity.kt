package com.example.viewpagerdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.viewpagerdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PagerAdapter
    private lateinit var adapterList: MyListAdapter
    private lateinit var saladAdapter: MyListAdapter
    private lateinit var breadAdapter: MyListAdapter
    private lateinit var drinksAdapter: MyListAdapter
    private lateinit var heavyAdapter: MyListAdapter

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PagerAdapter(this)

        binding.pager.adapter = adapter
        adapterList = MyListAdapter()
        saladAdapter = MyListAdapter()
        breadAdapter = MyListAdapter()
        drinksAdapter = MyListAdapter()
        heavyAdapter = MyListAdapter()

        binding.recycleView.adapter = adapterList
        binding.salad.adapter = saladAdapter
        binding.heavyFoods.adapter = saladAdapter
        binding.breadListView.adapter = breadAdapter
        binding.drinksListView.adapter = drinksAdapter
        binding.heavyFoods.adapter = heavyAdapter

        adapterList.submitList(
            listOf(
                R.drawable.food_2,
                R.drawable.suyuq,
                R.drawable.suyuq,
                R.drawable.suyuq,
                R.drawable.suyuq,
                R.drawable.suyuq,
            )
        )
        breadAdapter.submitList(
            listOf(
                R.drawable.bread,
                R.drawable.bread_2,
                R.drawable.bread,
                R.drawable.bread,
                R.drawable.bread_2,
                R.drawable.bread
            )
        )
        drinksAdapter.submitList(
            listOf(
                R.drawable.cola,
                R.drawable.coffe,
                R.drawable.coffe,
                R.drawable.cola,
                R.drawable.cola,
                R.drawable.coffe,
                R.drawable.cola,
                R.drawable.coffe,
            )
        )
        saladAdapter.submitList(
            listOf(
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2
            )
        )

        heavyAdapter.submitList(
            listOf(
                R.drawable.heavy_food,
                R.drawable.heavy_food,
                R.drawable.heavy_food,
                R.drawable.heavy_food,
                R.drawable.heavy_food,
            )
        )

        adapterList.setOnClick { it, s, pos ->
            val location = IntArray(2)
            val itemView = binding.recycleView.getChildAt(pos)
            itemView.getLocationInWindow(location)
            animation(it, s, FoodType.LIQUID, location)
        }

        heavyAdapter.setOnClick { it, s, pos ->
            val location = IntArray(2)
            val itemView = binding.heavyFoods.getChildAt(pos)
            itemView.getLocationInWindow(location)
            animation(it, s, FoodType.HEAVY, location)
        }

        saladAdapter.setOnClick { it, s, pos ->
            val location = IntArray(2)
            val itemView = binding.heavyFoods.getChildAt(pos)
            itemView.getLocationInWindow(location)
            animation(it, s, FoodType.SALAD, location)
        }

        breadAdapter.setOnClick { it, s, pos ->
            val location = IntArray(2)
            val itemView = binding.breadListView.getChildAt(pos)
            itemView.getLocationInWindow(location)
            animation(it, s, FoodType.BREAD, location)
        }

        drinksAdapter.setOnClick { it, s, pos ->
            val location = IntArray(2)
            val itemView = binding.drinksListView.getChildAt(pos)
            itemView.getLocationInWindow(location)
            animation(it, s, FoodType.DRINK, location)
        }


        binding.backFood.setOnClickListener {
            adapter.clickEvent(binding.pager.currentItem, Event.RemoveLast)
        }

        binding.hand.setOnClickListener {
            adapter.clickEvent(
                binding.pager.currentItem,
                Event.UserMoveFood(!binding.hand.isActivated)
            )

            binding.hand.isActivated = !binding.hand.isActivated
            binding.hand.setImageResource(R.drawable.ic_touch)


            if (binding.hand.isActivated) {
                binding.hand.setBackgroundResource(R.drawable.hand_border)
            } else {
                binding.hand.setBackgroundColor(Color.TRANSPARENT)
            }
            binding.pager.isUserInputEnabled = !binding.hand.isActivated
            binding.backFood.isVisible = !binding.hand.isActivated
            binding.zoomOut.isVisible = !binding.hand.isActivated
        }

        binding.zoomOut.setOnClickListener {
            lifecycleScope.launch {
                adapter.clickEvent(binding.pager.currentItem, Event.ZoomOut)
            }
        }
    }

    private fun animation(it: View, s: Int, type: FoodType, location: IntArray) {
        val imageView = ImageView(this)
        imageView.layoutParams = ViewGroup.LayoutParams(it.width, it.height)

        imageView.x = location[0].toFloat()
        imageView.y = location[1].toFloat()
        Glide.with(this).load(s).into(imageView)
        binding.root.addView(imageView)



        imageView.animate().scaleY(2f).scaleX(2f)
            .x((binding.pager.width / 2 - it.width / 2).toFloat())
            .y((binding.pager.height / 2 - it.height / 2).toFloat()).setDuration(400).start()

        lifecycleScope.launch {
            adapter.clickEvent(
                binding.pager.currentItem,
                Event.OnReceive(s, it.width, it.height, type)
            )
            delay(420)
            binding.root.removeView(imageView)
        }
    }


}