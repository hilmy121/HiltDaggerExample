package com.plcoding.daggerhiltcourse.data.remote

import com.plcoding.daggerhiltcourse.data.datamodel.ImgFlipResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("test")
    suspend fun doNetworkCall()

    //Alternate Way
    @GET("get_memes")
    fun getMemes(): Call<ImgFlipResponse>

    //Using  Await()
    @GET("get_memes")
    suspend fun getAwaitMemes(): Response<ImgFlipResponse>
}