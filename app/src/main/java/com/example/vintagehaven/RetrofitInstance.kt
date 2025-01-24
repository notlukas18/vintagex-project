package com.example.vintagehaven

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("get_products") // Replace with your actual endpoint
    suspend fun getProducts(): List<ProductResponse>
    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse
    @POST("login") // Replace with your actual endpoint
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
    @GET("get_profile") // Replace with your actual endpoint
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse
}

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://zarifjon.pythonanywhere.com/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}