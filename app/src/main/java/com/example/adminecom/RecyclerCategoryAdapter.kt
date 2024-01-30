package com.example.adminecom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminecom.databinding.CategoryRowBinding

class RecyclerCategoryAdapter(val context: Context, val arrCatList:ArrayList<CategoryModal>) : RecyclerView.Adapter<RecyclerCategoryAdapter.ViewHolder>() {
    class ViewHolder (val binding: CategoryRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount(): Int {
        return arrCatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(arrCatList[position].catImg).into(holder.binding.catImg)
        holder.binding.catName.text =arrCatList[position].catName
    }
}