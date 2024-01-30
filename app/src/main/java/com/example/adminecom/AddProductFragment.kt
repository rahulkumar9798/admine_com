package com.example.adminecom


import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.adminecom.databinding.FragmentAddProductBinding
import com.example.adminecom.databinding.ImgUploadRowBinding
import java.io.ByteArrayOutputStream

class AddProductFragment : Fragment() {
   lateinit var binding: FragmentAddProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddProductBinding.inflate(layoutInflater, container, false)


        // for units dropdown
        val UnitDropDownItems = arrayOf("kg", "gm", "ml", "Ltr", "packets")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, UnitDropDownItems)
        binding.UnitDropDown.setAdapter(adapter)


        //for Product Category
        val pCategoryDropDownItems = arrayOf("Vagetable & Fruits", "Dairy & BreakFast", "Cold Drinks", "Munchies", "Biscuits")
        val pCategoryadapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, pCategoryDropDownItems)
        binding.ProductCategoryDropDown.setAdapter(pCategoryadapter)


        //for Product Sub Cat
        val pSubCatDropDownItems = arrayOf("salt & suger", "Noodles", "Cooking oil", "Eggs", "Chips & Crisps")
        val subCateadapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, pSubCatDropDownItems)
        binding.ProductTypeDropDown.setAdapter(subCateadapter)


        // for units dropdown
        val productColor = arrayOf("Red", "Green", "Yellow", "Pink", "Black")
        val Coloradapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, productColor)
        binding.productColor.setAdapter(Coloradapter)



        binding.btnproductsimg.setOnClickListener {
            val dialogAdd= Dialog(requireContext() )
            val dialogBinding = ImgUploadRowBinding.inflate(layoutInflater)
            dialogAdd.setContentView(dialogBinding.root)

            dialogBinding.camraUpload.setOnClickListener {
               // camLauncher.launch(iCam)
                dialogAdd.dismiss()
            }

            dialogBinding.galleryupload.setOnClickListener {
               //galLauncher.launch(iGall)
                dialogAdd.dismiss()
            }
            dialogAdd.show()
        }







        return binding.root
    }


}