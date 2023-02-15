package com.example.fragmentart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, Fragment_List(), "FragmentOne").commit()
    }

    fun moveToDetails(id: Int) {

        val bundle = Bundle()
        bundle.putInt("id", id)
        val fragInfo = Fragment_Details()
        fragInfo.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragInfo)
            .commitNow()
    }

}