package com.lee.itemtouchhelper.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lee.itemtouchhelper.viewmodel.MainActivityViewModel

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel() as T
        } else {
            throw java.lang.IllegalArgumentException("해당 ViewModel을 찾을수 없습니다.")
        }
    }
}