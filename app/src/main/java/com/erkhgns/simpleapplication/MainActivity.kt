package com.erkhgns.simpleapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.erkhgns.simpleapplication.databinding.ActivityMainBinding
import com.erkhgns.simpleapplication.home.HomeFragment
import com.erkhgns.simpleapplication.login.LogInFragment
import com.erkhgns.simpleapplication.network.User
import com.erkhgns.simpleapplication.userdetail.UserDetailFragment
import com.hendraanggrian.appcompat.countrypicker.CountryPickerDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        gotoLogInFragment()
    }

    private fun gotoLogInFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameContent.id, LogInFragment.newInstance {
                goToHomeFragment(it)
            })
            commit()
        }
    }

    private fun goToHomeFragment(user: com.erkhgns.simpleapplication.database.entities.User) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameContent.id, HomeFragment.newInstance(user) {
                goToUserDetailFragment(it)
            })
            commit()
        }
    }

    private fun goToUserDetailFragment(user: User) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameContent.id, UserDetailFragment.newInstance(user)).addToBackStack(
                null
            )
            commit()
        }
    }
     fun checkMaps(latitude: String, longitude: String) {
         val uriString = "geo:$latitude,$longitude"
         val gmmIntentUri = Uri.parse(uriString)
         val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
         mapIntent.setPackage("com.google.android.apps.maps")
         mapIntent.resolveActivity(packageManager)?.let {
             startActivity(mapIntent)
         }
     }
}
