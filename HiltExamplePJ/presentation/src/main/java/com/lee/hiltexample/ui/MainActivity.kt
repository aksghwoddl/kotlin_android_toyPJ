package com.lee.hiltexample.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lee.hiltexample.databinding.ActivityMainBinding
import com.lee.hiltexample.ui.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBeachInfo()
    }

    private fun observeData() {
        with(viewModel){
            beachCongestionList.observe(this@MainActivity){
                Log.d(TAG, "observeData: $it")
            }
            toastMessage.observe(this@MainActivity){
                Toast.makeText(this@MainActivity , it , Toast.LENGTH_SHORT).show()
            }
        }
    }
}