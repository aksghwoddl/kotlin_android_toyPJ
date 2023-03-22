package com.lee.hiltexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lee.domain.common.NetworkResult
import com.lee.domain.model.BeachCongestionList
import com.lee.domain.usecase.GetBeachCongestionList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getBeachCongestionList: GetBeachCongestionList
) : ViewModel(){
    private val _networkResult = MutableStateFlow<NetworkResult<BeachCongestionList>>(NetworkResult.Success(BeachCongestionList(arrayListOf())))
    val networkResult : StateFlow<NetworkResult<BeachCongestionList>>
    get() = _networkResult

    private val _beachCongestionList =  MutableLiveData<BeachCongestionList>()
    val beachCongestionList : LiveData<BeachCongestionList>
    get() = _beachCongestionList
    fun setBeachCongestionList(beachCongestionList: BeachCongestionList){
        _beachCongestionList.value = beachCongestionList
    }

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String>
    get() = _toastMessage

    fun getBeachInfo(){
        viewModelScope.launch {
            getBeachCongestionList.invoke().collect{ result ->
                _networkResult.value = result
            }
        }
    }

    fun onError(message : String) {
        _toastMessage.value = message
    }
}
