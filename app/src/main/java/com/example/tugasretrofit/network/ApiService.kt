package com.example.tugasretrofit.network

import com.example.tugasretrofit.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2") // Endpoint spesifik untuk mendapatkan data di halaman kedua
    suspend fun getAllUsers(): Response<UserResponse>
}
