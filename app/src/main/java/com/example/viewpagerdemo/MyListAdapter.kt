package com.example.viewpagerdemo

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewpagerdemo.databinding.ItemViewBinding

class MyListAdapter : ListAdapter<Int, MyListAdapter.Holder>(Diff) {

    private var listener : ((View, Int,Int) -> Unit)? = null


    inner class Holder(private val binding : ItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.image.setOnClickListener { listener?.invoke(it,getItem(adapterPosition),adapterPosition) }
        }

        fun bind() {
            val data = getItem(adapterPosition)
            Glide.with(binding.image.context).load(data).into(binding.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemViewBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    object Diff : DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnClick(block : (View,Int,Int) -> Unit){
        listener = block
    }
}