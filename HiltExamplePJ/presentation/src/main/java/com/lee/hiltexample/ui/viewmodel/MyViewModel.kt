package com.lee.hiltexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lee.domain.model.BeachCongestionList
import com.lee.domain.usecase.GetBeachCongestionList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getBeachCongestionList: GetBeachCongestionList
) : ViewModel(){
    private val _beachCongestionList = MutableLiveData<BeachCongestionList>()
    val beachCongestionList : LiveData<BeachCongestionList>
    get() = _beachCongestionList

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String>
    get() = _toastMessage

    fun getBeachInfo(){
        val exceptionHandler = CoroutineExceptionHandler{ _ , exception ->
            when(exception){
                is SocketTimeoutException -> {onError("통신시간 초과!")}
            }
        }
        viewModelScope.launch(exceptionHandler) {
            _beachCongestionList.value = getBeachCongestionList.invoke()
        }
    }

    private fun onError(message : String) {
        _toastMessage.value = message
    }
}