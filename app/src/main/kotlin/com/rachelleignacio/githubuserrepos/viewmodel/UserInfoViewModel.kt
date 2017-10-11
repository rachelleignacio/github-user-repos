package com.rachelleignacio.githubuserrepos.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.rachelleignacio.githubuserrepos.model.Repository
import com.rachelleignacio.githubuserrepos.model.User
import com.rachelleignacio.githubuserrepos.network.RemoteDataRepository

/**
 * Created by rachelleignacio on 10/6/17.
 */
class UserInfoViewModel : ViewModel() {
    private var observableUser: LiveData<User>
    private var observableRepoList: LiveData<MutableList<Repository>>

    private var mutableUsername: MutableLiveData<String>

    init {
        val remoteDataRepository = RemoteDataRepository.getInstance()

        mutableUsername = MutableLiveData()
        mutableUsername.value = "rachelleignacio"

        observableUser = Transformations.switchMap(mutableUsername, {
            remoteDataRepository.getUserInfo(mutableUsername.value!!)
        })

        observableRepoList = Transformations.switchMap(mutableUsername, {
            remoteDataRepository.getUserRepos(mutableUsername.value!!)
        })
    }

    fun getUserInfo(): LiveData<User> {
        return observableUser
    }

    fun getUserRepos(): LiveData<MutableList<Repository>> {
        return observableRepoList
    }

    fun setUsername(username: String) {
        mutableUsername.value = username
    }
}