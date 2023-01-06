package com.lee.hiltexample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lee.hiltexample.data.remote.model.Beach
import com.lee.hiltexample.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel(){
    val beachInfo  = MutableLiveData<Beach>()
    val toastMessage  = MutableLiveData<String>()

    fun getBeachInfo(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.doNetworkCall()
            if(response.isSuccessful){
                CoroutineScope(Dispatchers.Main).launch {
                    beachInfo.value = response.body()?.beach0
                }
            } else {
                onError()
            }
        }
    }

    fun onError() {
        toastMessage.postValue("에러발생!")
    }
}