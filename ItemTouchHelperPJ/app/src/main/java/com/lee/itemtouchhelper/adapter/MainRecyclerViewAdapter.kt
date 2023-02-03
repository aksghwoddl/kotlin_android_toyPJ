package com.lee.itemtouchhelper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lee.itemtouchhelper.data.RecyclerItem
import com.lee.itemtouchhelper.databinding.RecyclerItemBinding

class MainRecyclerViewAdapter
    : ListAdapter<RecyclerItem , MainRecyclerViewAdapter.MainRecyclerViewHolder>(DiffUtilCallBack())
    , ItemTouchHelperCallBack.ItemTouchHelperListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MainRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onItemMove(from: Int, to: Int): Boolean {
        val updateList = ArrayList(currentList)
        val item = updateList[from]
        updateList.removeAt(from)
        updateList.add(to , item)
        submitList(updateList)
        return true
    }

    override fun onItemSwipe(position: Int) {
        val updateList = ArrayList(currentList)
        updateList.removeAt(position)
        submitList(updateList)
    }

    class MainRecyclerViewHolder(private val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : RecyclerItem){
            with(binding){
                itemTitle.text = item.title
                itemImage.setImageResource(item.image)
            }
        }
    }

    private class DiffUtilCallBack : DiffUtil.ItemCallback<RecyclerItem>() {
        override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
            return oldItem == newItem
        }
    }
}