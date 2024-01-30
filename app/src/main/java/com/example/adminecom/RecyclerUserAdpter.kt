package com.example.adminecom

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

        //fetch image Profile Pic
        Glide.with(context).load(arrUserList[position].profilePic).into(holder.binding.userImg)

        holder.binding.txtUserName.text = arrUserList[position].name
        holder.binding.txtUserMob.text = arrUserList[position].phnNo

        /* holder.binding.btnUpdate.setOnClickListener {
           context.startActivity(Intent(context, snjvnsfdv::class.java).putExtra("userId", arrUserList[position].userId))
       }*/



//        holder.binding.btnDelete.setOnClickListener {
//            (context as UserFragment).deleteNotes()

//        }



        holder.binding.userDetails.setOnClickListener {
            val intent = Intent(context, UserDetailsActivity::class.java)
            context.startActivity(intent)

        }
    }
}