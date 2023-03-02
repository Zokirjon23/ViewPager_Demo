package com.example.viewpagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
    private lateinit var heavyAdapter: MyListAdapter
    private lateinit var breadAdapter: MyListAdapter
    private lateinit var drinksAdapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PagerAdapter(lifecycle, supportFragmentManager)
        binding.pager.adapter = adapter
        adapterList = MyListAdapter()
        heavyAdapter = MyListAdapter()
        breadAdapter = MyListAdapter()
        drinksAdapter = MyListAdapter()

        binding.recycleView.adapter = adapterList
        binding.heavyFoods.adapter = heavyAdapter
        binding.breadListView.adapter = breadAdapter
        binding.drinksListView.adapter = drinksAdapter

        adapterList.submitList(
            listOf(
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
            )
        )
        breadAdapter.submitList(
            listOf(
                R.drawable.bread,
                R.drawable.bread,
                R.drawable.bread,
                R.drawable.bread,
                R.drawable.bread,
                R.drawable.bread
            )
        )
        drinksAdapter.submitList(
            listOf(
                R.drawable.cola,
                R.drawable.fanta,
                R.drawable.cola,
                R.drawable.fanta,
                R.drawable.cola,
                R.drawable.fanta,
                R.drawable.cola,
                R.drawable.fanta,
            )
        )
        heavyAdapter.submitList(
            listOf(
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2,
                R.drawable.food_2
            )
        )

        adapterList.setOnClick { it, s, pos ->
            animation(it, s, pos,FoodType.LIQUID,binding.recycleView.y)
        }

        heavyAdapter.setOnClick { it, s, pos ->
            animation(it, s, pos,FoodType.HEAVY,binding.heavyFoods.y)
        }

        breadAdapter.setOnClick { it, s, pos ->
            animation(it, s, pos,FoodType.BREAD,binding.breadListView.y)
        }

        drinksAdapter.setOnClick { it, s, pos ->
            animation(it, s, pos,FoodType.DRINK,binding.drinksListView.y)
        }


        binding.backFood.setOnClickListener {
            adapter.clickEvent(binding.pager.currentItem, Event.RemoveLast)
        }

        binding.zoomOut.setOnClickListener {
            lifecycleScope.launch {
                adapter.clickEvent(binding.pager.currentItem, Event.ZoomOut)
            }
        }
    }

    private fun animation(it: View, s: Int, pos: Int,type: FoodType,y : Float) {
        val imageView = ImageView(this)
        imageView.layoutParams = ViewGroup.LayoutParams(it.width, it.height)

        imageView.x = (it.width * pos).toFloat()
        imageView.y = y
        Glide.with(this).load(s).into(imageView)
        binding.root.addView(imageView)



        imageView.animate().scaleY(2f).scaleX(2f)
            .x((binding.pager.width / 2 - it.width / 2).toFloat())
            .y((binding.pager.height / 2 - it.height / 2).toFloat()).setDuration(400).start()

        lifecycleScope.launch {
            adapter.clickEvent(
                binding.pager.currentItem,
                Event.OnReceive(s, it.width, it.height,type)
            )
            delay(420)
            binding.root.removeView(imageView)
        }
    }


}