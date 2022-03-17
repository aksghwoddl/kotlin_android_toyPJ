package com.example.tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    val TAG : String = "로그"
    
    //액티비티가 생성되었을
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG , "MainActivity - onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG , "MainActivity - onStart")
    }

    override fun onResume() {
        super.onResume()
        
        Log.d(TAG , "MainActivity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG , "MainActivity - onPause")
        text_view.visibility = View.VISIBLE
        text_view.setText("onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG , "MainActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG , "MainActivity - onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG , "MainActivity - onRestart")
    }
}