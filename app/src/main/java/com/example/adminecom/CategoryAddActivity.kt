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
import com.example.adminecom.databinding.LoadingBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream
import java.util.Calendar

class CategoryAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryAddBinding
    var imgUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

                    getCatImgUrl(imgBytes)


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

                    getCatImgUrl(imgBytes)

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

        binding.btnSave.setOnClickListener {

            val dialogAdd= Dialog(this)
            val dialogBinding = LoadingBinding.inflate(layoutInflater)
            dialogAdd.setContentView(dialogBinding.root)

            val currTimeStamp = Calendar.getInstance().timeInMillis
            val catName = binding.edtCatName.text.toString()
            //val catId = UUID.randomUUID().toString()  this is auto unique id generate
            val createdAt = currTimeStamp




            if(imgUrl!=null){
                val firestore = FirebaseFirestore.getInstance()

                val newCatItem = CategoryModal(createdAt, catName, imgUrl!!)
                firestore.collection("category")
                    .document("$currTimeStamp")
                    .set(newCatItem)
                    .addOnSuccessListener {
                        dialogAdd.dismiss()
                        finish()
                    Log.d("Success", "${it}")
                }.addOnFailureListener {
                    Log.d("Failure", "${it.message}")
                    it.printStackTrace()
                }
            }

            dialogAdd.show()

        }






    }


    fun getCatImgUrl(imgBytes: ByteArray) {
        val storageRef = Firebase.storage
        val timeStamp = Calendar.getInstance().timeInMillis
        val imgRef =
            storageRef.reference.child("cat/images/IMG_$timeStamp.png")


        imgRef.putBytes(imgBytes)
            .addOnSuccessListener {
                Log.d("Success", "${it.metadata}")

                imgRef.downloadUrl.addOnSuccessListener {
                    Log.d("ImgUrl", "$it")
                    imgUrl = it.toString()
                }.addOnFailureListener {
                    Log.d("Failure", "${it.message}")
                    it.printStackTrace()
                }

            }.addOnFailureListener {
                Log.d("Failure", "${it.message}")
                it.printStackTrace()
            }
    }






}