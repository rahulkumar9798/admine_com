package com.example.adminecom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.adminecom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {item->


            when (item.itemId) {
                R.id.nav_home -> {

                    replaceFragment(HomeFragment())
                    true
                }
                R.id.addCat -> {

                    replaceFragment(AddCategoryFragment())
                    true
                }
                R.id.addProduct -> {
                    replaceFragment(AddProductFragment())
                    true
                }
                R.id.oder -> {
                    replaceFragment(oderFragment())
                    true
                }
                R.id.Profile -> {
                    replaceFragment(UserFragment())
                    true
                }
                else-> false
            }


        }
        replaceFragment(HomeFragment())

    }

    // replace fragment start
    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
    // replace fragment close


}