package com.plcoding.daggerhiltcourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.daggerhiltcourse.data.datamodel.ImgFlipResponse
import com.plcoding.daggerhiltcourse.domain.repository.MyRepository
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Lazy<MyRepository>
): ViewModel() {
    private var imgFlipResponse = MutableLiveData<ImgFlipResponse>()


//    init {
//        repository.get()
//    }

    internal fun getDaggerRequest(): LiveData<ImgFlipResponse> {
        viewModelScope.launch (Dispatchers.IO){
            val value = async { repository.get().doNetworkCall() }
            imgFlipResponse.postValue(value.await())
        }
        return imgFlipResponse
    }
}