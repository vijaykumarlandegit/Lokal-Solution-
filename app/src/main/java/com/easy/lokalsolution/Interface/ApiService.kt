package com.easy.lokalsolution.Interface

import com.easy.lokalsolution.Model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users") // Replace with your endpoint
    //fun getPosts(): Call<List<Post>>
    suspend fun getUsers(): List<User>
}