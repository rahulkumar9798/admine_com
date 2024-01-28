package com.example.adminecom

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.adminecom.databinding.FragmentAddCategoryBinding

class AddCategoryFragment : Fragment() {
    lateinit var binding: FragmentAddCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCategoryBinding.inflate(layoutInflater, container, false)





        var arrCatList =ArrayList<CategoryModal>().apply {
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
        }

        binding.recyclerCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerCategory.adapter = RecyclerCategoryAdapter(requireContext(), arrCatList)





        binding.fabAddCat.setOnClickListener {
            startActivity(Intent(context, CategoryAddActivity::class.java))

        }























        // Inflate the layout for this fragment
        return binding.root
    }

}