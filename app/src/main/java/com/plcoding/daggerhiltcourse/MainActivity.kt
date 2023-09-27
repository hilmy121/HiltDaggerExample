package com.plcoding.daggerhiltcourse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.plcoding.daggerhiltcourse.data.datamodel.ImgFlipResponse
import com.plcoding.daggerhiltcourse.data.datamodel.Meme
import com.plcoding.daggerhiltcourse.mediator.ViewModelMediator
import com.plcoding.daggerhiltcourse.ui.theme.DaggerHiltCourseTheme
import com.plcoding.daggerhiltcourse.uicompose.CustomItem2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity(),ViewModelMediator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerHiltCourseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    val viewModel = hiltViewModel<MyViewModel>()

                    val imgFlipResponse = remember {
                        mutableStateOf(ImgFlipResponse())
                    }
                    val responseList = remember {
                        mutableStateOf(listOf<Meme>())
                    }

                    responseHandler(this,responseList,viewModel)
                    LazyColumn(
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(items = responseList.value) {
                            CustomItem2(item = it)
                        }

                    }
                }

            }
        }
    }

    override fun getDaggerResponse(responseList: MutableState<List<Meme>>, vm: ViewModel) {
        if (vm is MyViewModel){
            vm.getDaggerRequest().observe(this, Observer {
                it.let {
                    if (it != null) {
                        lifecycleScope.launch(Dispatchers.Main){
                            Log.i("Activity processing in ", Thread.currentThread().name)
                            responseList.value = it.data?.memes!!
                        }
                    }
                }
            })
        }
    }

    private fun responseHandler(
        viewModelMediator: ViewModelMediator,
        responseList: MutableState<List<Meme>>,
        vm: ViewModel)=viewModelMediator.getDaggerResponse(responseList, vm)
}