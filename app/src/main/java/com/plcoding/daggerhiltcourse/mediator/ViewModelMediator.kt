package com.plcoding.daggerhiltcourse.mediator

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.plcoding.daggerhiltcourse.data.datamodel.Meme

interface ViewModelMediator {

    fun getDaggerResponse(responseList: MutableState<List<Meme>>, vm: ViewModel)
}