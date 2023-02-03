package com.lee.itemtouchhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lee.itemtouchhelper.data.RecyclerItem

class MainActivityViewModel : ViewModel() {
    private val _list = MutableLiveData<ArrayList<RecyclerItem>>()
    val list : LiveData<ArrayList<RecyclerItem>>
    get() = _list
    fun setList(list : ArrayList<RecyclerItem>){
        _list.value = list
    }
}