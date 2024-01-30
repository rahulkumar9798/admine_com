package com.example.adminecom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminecom.databinding.FragmentUserBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class UserFragment : Fragment() {
    lateinit var binding: FragmentUserBinding
    lateinit var  firestore : FirebaseFirestore



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserBinding.inflate(layoutInflater, container, false)

        firestore = FirebaseFirestore.getInstance()

        firestore
            .collection("User")
            .orderBy("timeStamp", Query.Direction.DESCENDING)//include for descending oder
            .get()
            .addOnSuccessListener {
                val userData = ArrayList<UserModal>()

                for(eachDoc in it.documents){
                    val mUser = eachDoc.toObject(UserModal::class.java)
                    mUser!!.userId = eachDoc.id
                    userData.add(mUser)


                }

                binding.recyclerViewUser.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerViewUser.adapter = RecyclerUserAdpter(requireContext(), userData)

            }
            .addOnFailureListener{
                Log.d("Failed: ", "Error Fetching notes: ${it.message}")
                it.printStackTrace()
            }









        // Inflate the layout for this fragment
        return binding.root
    }

    fun deleteNotes(){
        val getDeleteItem = firestore.collection("User").document("timeStamp").delete()

        getDeleteItem.addOnSuccessListener {
            Toast.makeText(requireContext(), "Itemdelete", Toast.LENGTH_LONG).show()
        }
    }


}