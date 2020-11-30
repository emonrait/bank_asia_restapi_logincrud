package com.example.bankasiaproject.model

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path


class ApiService {
    private val BASE_URL = "http://10.11.200.4:8080/"


    var gson = GsonBuilder().setLenient().create()
    var client = OkHttpClient()


    val apiinstance: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api::class.java)
    }

    fun createUser(params: RequestBody): Call<ApiResponse> {
        return apiinstance.signinInformation(params)
    }

    fun login(params: RequestBody): Call<UserResponse> {
        return apiinstance.logininInformation(params)
    }

    fun getUser(): Call<List<ApiResponse>> {
        return apiinstance.getuser()
    }

    fun updateUser(params: RequestBody): Call<ApiResponse> {
        return apiinstance.userUpdate(params)
    }

    fun userDelete(@Path("id") id: Int?): Call<String> {
        return apiinstance.userDelete(id)
    }


}