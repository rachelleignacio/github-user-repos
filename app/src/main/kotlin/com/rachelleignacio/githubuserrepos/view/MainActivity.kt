package com.rachelleignacio.githubuserrepos.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

        observeUserInfo(userViewModel)
        observeUserRepos(userViewModel)
    }

    private fun observeUserInfo(viewmodel: UserInfoViewModel) {
        viewmodel.getUserInfo().observe(this, Observer<User> { user ->
            if (user != null) {
                (findViewById<TextView>(R.id.username)).text = user.username
                (findViewById<TextView>(R.id.bio)).text = user.bio
                Glide.with(this).load(user.avatarUrlString).into(findViewById(R.id.avatar))
            }
        })
    }

    private fun observeUserRepos(viewmodel: UserInfoViewModel) {
        viewmodel.getUserRepos().observe(this, Observer { repos ->
            if (repos != null) {
                val recyclerView = findViewById<RecyclerView>(R.id.user_repo_list)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = RepoListAdapter(repos)
            }
        })
    }
}
