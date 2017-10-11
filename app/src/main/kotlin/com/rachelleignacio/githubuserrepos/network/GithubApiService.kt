package com.rachelleignacio.githubuserrepos.network

import com.rachelleignacio.githubuserrepos.model.Repository
import com.rachelleignacio.githubuserrepos.model.User
import retrofit2.Call;
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by rachelleignacio on 9/30/17.
 */
interface GithubApiService {
    companion object {
        val baseUrl: String = "https://api.github.com"
    }

    @GET("/users/{username}")
    fun getUserInfo(@Path("username") username: String): Call<User>

    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Call<MutableList<Repository>>
}