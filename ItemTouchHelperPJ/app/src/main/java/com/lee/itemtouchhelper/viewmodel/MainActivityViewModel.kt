package com.lee.itemtouchhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lee.itemtouchhelper.data.RecyclerItem

class MainActivityViewModel : ViewModel() {
    val items = MutableLiveData<MutableList<RecyclerItem>>()
}