package com.android.drawertestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.drawertestapp.model.NavigationItem
import com.android.drawertestapp.callback.NavigationDrawerCallback
import com.android.drawertestapp.databinding.NavigationDrawerItemLayoutBinding

class NavigationDrawerAdapter(private val callback: NavigationDrawerCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList = mutableListOf<NavigationItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            NavigationDrawerItemLayoutBinding.inflate
                ( LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBind()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(private val binding: NavigationDrawerItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(){
            binding.tvNavigationName.text = itemList[adapterPosition].name
            binding.root.setOnClickListener{
                callback.onDrawerClick(adapterPosition)
            }
        }
    }

    fun addData(list: MutableList<NavigationItem>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    fun setSelected(position: Int) {
        for (i in 0 until itemList.size) {
            itemList[i].isSelected = (i == position)
        }
        notifyDataSetChanged()
    }
}