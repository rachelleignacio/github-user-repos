package com.rachelleignacio.githubuserrepos.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.rachelleignacio.githubuserrepos.model.Repository
import com.rachelleignacio.githubuserrepos.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rachelleignacio on 9/30/17.
 */
class RemoteDataRepository private constructor() {
    private lateinit var githubApiService: GithubApiService

    companion object {
        private var _instance: RemoteDataRepository? = null
        fun getInstance(): RemoteDataRepository {
            if (_instance == null) {
                _instance = RemoteDataRepository()
            }
            return _instance!!
        }
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(GithubApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        githubApiService = retrofit.create(GithubApiService::class.java)
    }

    fun getUserInfo(): LiveData<User> {
        val data: MutableLiveData<User> = MutableLiveData()
        githubApiService.getUserInfo().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                data.value = response!!.body()
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                t!!.printStackTrace()
            }

        })
        return data
    }

    fun getUserRepos() : LiveData<MutableList<Repository>> {
        val data: MutableLiveData<MutableList<Repository>> = MutableLiveData()
        githubApiService.getRepos().enqueue(object : Callback<MutableList<Repository>> {
            override fun onResponse(call: Call<MutableList<Repository>>?, response: Response<MutableList<Repository>>?) {
                Log.d("userrepos", "RemoteDataRepo getUserRepos() onResponse(): " + response!!.body())
                data.value = response!!.body()
            }

            override fun onFailure(call: Call<MutableList<Repository>>?, t: Throwable?) {
                t!!.printStackTrace()
            }

        })
        return data
    }
}