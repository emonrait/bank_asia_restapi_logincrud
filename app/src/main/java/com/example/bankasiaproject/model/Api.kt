package com.example.bankasiaproject.model

import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @POST("/addUser")
    fun signinInformation(
        @Body params: RequestBody?
    ): Call<ApiResponse>


    @POST("/login")
    fun logininInformation(
        @Body params: RequestBody
    ): Call<UserResponse>

    @GET("/users")
    fun getuser(): Call<List<ApiResponse>>

    @GET("/users/{userId}")
    fun getuserbyid(
        @Field("userId") userId: String?
    ): Call<ApiResponse>

    @PUT("/update}")
    fun userUpdate(
        @Body params: RequestBody?
    ): Call<ApiResponse>

    @PUT("/delete/{id}}")
    fun userDelete(
        @Field("id") id: Int?
    ): Call<ApiResponse>


}