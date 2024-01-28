package com.example.adminecom

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.adminecom.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //for user Status
        val userStatus = arrayOf("Active", "Pendding", "Block")
        val userstatusadapter = ArrayAdapter(this, R.layout.simple_expandable_list_item_1, userStatus)
        binding.userStatus.setAdapter(userstatusadapter)




    }
}