package com.rachelleignacio.githubuserrepos.network

import com.rachelleignacio.githubuserrepos.model.Repository
import com.rachelleignacio.githubuserrepos.model.User
import retrofit2.Call;
import retrofit2.http.GET

/**
 * Created by rachelleignacio on 9/30/17.
 */
interface GithubApiService {
    companion object {
        val baseUrl: String = "https://api.github.com"
    }

    @GET("/users/rachelleignacio")
    fun getUserInfo(): Call<User>

    @GET("/users/rachelleignacio/repos")
    fun getRepos(): Call<MutableList<Repository>>
}