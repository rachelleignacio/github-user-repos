package com.rachelleignacio.githubuserrepos.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.rachelleignacio.githubuserrepos.model.Repository
import com.rachelleignacio.githubuserrepos.model.User
import com.rachelleignacio.githubuserrepos.network.RemoteDataRepository

/**
 * Created by rachelleignacio on 10/6/17.
 */
class UserInfoViewModel : ViewModel() {
    private var user: LiveData<User>? = null
    private var repos: LiveData<MutableList<Repository>>? = null

    fun getUserInfo(): LiveData<User> {
        if (user == null) {
            user = RemoteDataRepository.getInstance().getUserInfo()
        }
        return user!!
    }

    fun getUserRepos(): LiveData<MutableList<Repository>> {
        if (repos == null) {
            repos = RemoteDataRepository.getInstance().getUserRepos()
        }
        return repos!!
    }
}