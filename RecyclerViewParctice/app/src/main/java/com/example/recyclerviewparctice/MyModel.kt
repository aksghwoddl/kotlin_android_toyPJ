package com.example.recyclerviewparctice

import android.util.Log

class MyModel(var name: String? = null , var profileImage : String? = null) {
    val TAG : String = "로그"
    init {
        Log.d(TAG , "MyModel - init() called")
    }
}