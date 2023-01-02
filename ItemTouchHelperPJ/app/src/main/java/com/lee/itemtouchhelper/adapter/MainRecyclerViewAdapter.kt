package com.lee.itemtouchhelper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lee.itemtouchhelper.data.RecyclerItem
import com.lee.itemtouchhelper.databinding.RecyclerItemBinding

class MainRecyclerViewAdapter
    : RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>()
    , ItemTouchHelperCallBack.ItemTouchHelperListener {
    private var items = mutableListOf<RecyclerItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MainRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(list : MutableList<RecyclerItem>) {
        items = list
    }

    override fun onItemMove(from: Int, to: Int): Boolean {
        val item = items[from]
        items.removeAt(from)
        items.add(to , item)
        notifyItemMoved(from , to)
        return true
    }

    override fun onItemSwipe(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class MainRecyclerViewHolder(private val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : RecyclerItem){
            with(binding){
                itemTitle.text = item.title
                itemImage.setImageResource(item.image)
            }
        }
    }
}