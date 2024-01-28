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


        //for Product Type
        val pTypeDropDownItems = arrayOf("salt & suger", "Noodles", "Cooking oil", "Eggs", "Chips & Crisps")
        val pTypeadapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, pTypeDropDownItems)
        binding.ProductTypeDropDown.setAdapter(pTypeadapter)





        val iCam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val iGall = Intent(Intent.ACTION_GET_CONTENT)
        iGall.type = "image/*"

        val camLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val imgBitmap = it.data!!.extras!!.get("data") as Bitmap
                    binding.imageView.setImageBitmap(imgBitmap)

                    val baos = ByteArrayOutputStream()

                    imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                    val imgBytes = baos.toByteArray()

//                    val storageRef = Firebase.storage
//                    val timeStamp = Calendar.getInstance().timeInMillis
//                    val imgRef =
//                        storageRef.reference.child("app_images/profile_pic/IMG_$timeStamp.png")
//
//                    imgRef.putBytes(imgBytes)
//                        .addOnSuccessListener {
//                            Log.d("Success", "${it.metadata}")
//
//                            //image downloaded det url start----------
//                            imgRef.downloadUrl.addOnSuccessListener {
//                                Log.d("ImgUrl", "$it")
//
//                                profilepic = "$it"
//                            }.addOnFailureListener {
//
//                            }
//
//
//                        }.addOnFailureListener {
//                            Log.d("Failure", "${it.message}")
//                            it.printStackTrace()
//                        }

                }
            }


//        val galLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//                if (it.resultCode == RESULT_OK) {
//                    val imgBitmap = MediaStore.Images.Media.getBitmap(
//                        applicationContext.contentResolver,
//                        it.data!!.data
//                    )
//                    binding.btnproductsimg.setImageBitmap(imgBitmap)
//
//                    val baos = ByteArrayOutputStream()
//
//                    imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
//                    val imgBytes = baos.toByteArray()
//
//                    //val storageRef = Firebase.storage
////                    val timeStamp = Calendar.getInstance().timeInMillis
////                    val imgRef =
////                        storageRef.reference.child("app_images/profile_pic/IMG_$timeStamp.png")
////
////                    imgRef.putBytes(imgBytes)
////                        .addOnSuccessListener {
////                            Log.d("Success", "${it.metadata}")
////
////                            //image downloaded det url start----------
////                            imgRef.downloadUrl.addOnSuccessListener {
////                                Log.d("ImgUrl", "$it")
////
////                                profilepic = "$it"
////
////                            }.addOnFailureListener {
////
////                            }
////                        }.addOnFailureListener {
////                            Log.d("Failure", "${it.message}")
////                            it.printStackTrace()
////                        }
//
//                }
//            }

        binding.btnproductsimg.setOnClickListener {
            val dialogAdd= Dialog(requireContext() )
            val dialogBinding = ImgUploadRowBinding.inflate(layoutInflater)
            dialogAdd.setContentView(dialogBinding.root)

            dialogBinding.camraUpload.setOnClickListener {
                camLauncher.launch(iCam)
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