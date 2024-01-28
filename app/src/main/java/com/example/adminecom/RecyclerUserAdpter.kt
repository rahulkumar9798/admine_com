package com.example.adminecom

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminecom.databinding.UserRowBinding


class   RecyclerUserAdpter(val context: Context, val arrUserList : ArrayList<UserModal>) : RecyclerView.Adapter<RecyclerUserAdpter.ViewHolder>() {
    class ViewHolder (val binding : UserRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UserRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrUserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.userImg.setImageResource(arrUserList[position].imgPath)
        holder.binding.txtUserName.text = arrUserList[position].Name
        holder.binding.txtUserMob.text = arrUserList[position].Mobile

        holder.binding.userDetails.setOnClickListener {
            val intent = Intent(context, UserDetailsActivity::class.java)
            context.startActivity(intent)

        }
    }
}