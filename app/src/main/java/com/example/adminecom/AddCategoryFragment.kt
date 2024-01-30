package com.example.adminecom

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminecom.databinding.FragmentAddCategoryBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AddCategoryFragment : Fragment() {
    lateinit var binding: FragmentAddCategoryBinding
    lateinit var  firestore : FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCategoryBinding.inflate(layoutInflater, container, false)

        firestore = FirebaseFirestore.getInstance()



        firestore
            .collection("category")
            .orderBy("createdAt", Query.Direction.DESCENDING)//include for descending oder
            .get()
            .addOnSuccessListener {
                val productCat = ArrayList<CategoryModal>()

                for(eachDoc in it.documents){
                    val mCat = eachDoc.toObject(CategoryModal::class.java)
                    productCat.add(mCat!!)

                    Log.d("prodd: ", "Fetching product: $mCat")

                }

                binding.recyclerCategory.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerCategory.adapter = RecyclerCategoryAdapter(requireContext(), productCat)

            }
            .addOnFailureListener{
                Log.d("Failed: ", "Error Fetching notes: ${it.message}")
                it.printStackTrace()
            }








        binding.fabAddCat.setOnClickListener {
            startActivity(Intent(context, CategoryAddActivity::class.java))

        }























        // Inflate the layout for this fragment
        return binding.root
    }

}