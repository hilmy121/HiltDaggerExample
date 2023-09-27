package com.plcoding.daggerhiltcourse.di

import android.app.Application
import com.plcoding.daggerhiltcourse.data.remote.MyApi
import com.plcoding.daggerhiltcourse.data.repository.MyRepositoryImpl
import com.plcoding.daggerhiltcourse.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule:Application() {
    private const val BASE_HTTP_URL2="https://api.imgflip.com/"
//    level = if (com.example.coroutinesexample.BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    private val RESTApiClient = OkHttpClient().newBuilder().addInterceptor(
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY})
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val RetrofitClient = Retrofit.Builder()
        .baseUrl(BASE_HTTP_URL2)
        .client(RESTApiClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        return RetrofitClient.create(MyApi::class.java)
    }


//    @Provides
//    @Singleton
//    @Named("hello1")
//    fun provideString1() = "Hello 1"
//
//    @Provides
//    @Singleton
//    @Named("hello2")
//    fun provideString2() = "Hello 2"
}