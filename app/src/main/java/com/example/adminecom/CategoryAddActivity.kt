package com.example.adminecom

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminecom.databinding.ActivityCategoryAddBinding
import com.example.adminecom.databinding.ImgUploadRowBinding
import java.io.ByteArrayOutputStream
import java.util.Calendar

class CategoryAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mAuth = Firebase.auth
        val iCam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val iGall = Intent(Intent.ACTION_GET_CONTENT)
        iGall.type = "image/*"

        val camLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val imgBitmap = it.data!!.extras!!.get("data") as Bitmap
                    binding.catimg.setImageBitmap(imgBitmap)

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

        val galLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val imgBitmap = MediaStore.Images.Media.getBitmap(
                        applicationContext.contentResolver,
                        it.data!!.data
                    )
                    binding.catimg.setImageBitmap(imgBitmap)

                    val baos = ByteArrayOutputStream()

                    imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                    val imgBytes = baos.toByteArray()

                    //val storageRef = Firebase.storage
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
//
//                            }.addOnFailureListener {
//
//                            }
//                        }.addOnFailureListener {
//                            Log.d("Failure", "${it.message}")
//                            it.printStackTrace()
//                        }

                }
            }










        binding.btncatimg.setOnClickListener {
            val dialogAdd= Dialog(this)
            val dialogBinding = ImgUploadRowBinding.inflate(layoutInflater)
            dialogAdd.setContentView(dialogBinding.root)

            dialogBinding.camraUpload.setOnClickListener {
                camLauncher.launch(iCam)
                dialogAdd.dismiss()
            }

            dialogBinding.galleryupload.setOnClickListener {
                galLauncher.launch(iGall)
                dialogAdd.dismiss()
            }
            dialogAdd.show()
        }
    }
}