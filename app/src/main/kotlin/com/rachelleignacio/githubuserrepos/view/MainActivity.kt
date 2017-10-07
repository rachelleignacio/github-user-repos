package com.rachelleignacio.githubuserrepos.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rachelleignacio.githubuserrepos.R
import com.rachelleignacio.githubuserrepos.model.User
import com.rachelleignacio.githubuserrepos.viewmodel.UserInfoViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userViewModel: UserInfoViewModel = ViewModelProviders.of(this)
                .get(UserInfoViewModel::class.java)
        observeUserViewModel(userViewModel)
    }

    private fun observeUserViewModel(viewmodel: UserInfoViewModel) {
        viewmodel.getUserInfo().observe(this, Observer<User> { user ->
            if (user != null) {
                updateUserInfo(user)
            }
        })

    }

    private fun updateUserInfo(user: User) {
        (findViewById<TextView>(R.id.username)).text = user.username
        (findViewById<TextView>(R.id.bio)).text = user.bio
        Glide.with(this).load(user.avatarUrlString).into(findViewById(R.id.avatar))
    }

}
