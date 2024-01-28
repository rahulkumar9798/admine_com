package com.example.adminecom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminecom.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    lateinit var binding: FragmentUserBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserBinding.inflate(layoutInflater, container, false)


        var arrData = ArrayList<UserModal>().apply {
            add(UserModal(R.drawable.person, "rohan", "9709123458"))
            add(UserModal(R.drawable.person, "rajit", "9709123458"))
            add(UserModal(R.drawable.person, "suman", "9709123458"))
            add(UserModal(R.drawable.person, "Sachin", "9709123458"))
        }

        binding.recyclerViewUser.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUser.adapter = RecyclerUserAdpter(requireContext(), arrData)














        // Inflate the layout for this fragment
        return binding.root
    }


}