package com.rachelleignacio.githubuserrepos.view

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rachelleignacio.githubuserrepos.R
import com.rachelleignacio.githubuserrepos.model.User
import com.rachelleignacio.githubuserrepos.viewmodel.UserInfoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleIntent(intent)

        userViewModel = ViewModelProviders.of(this)
                .get(UserInfoViewModel::class.java)

        observeUserInfo()
        observeUserRepos()
    }

    override fun onNewIntent(intent: Intent?) {
        handleIntent(intent!!)
    }

    private fun observeUserInfo() {
        userViewModel.getUserInfo().observe(this, Observer<User> { user ->
            if (user != null) {
                (findViewById<TextView>(R.id.username)).text = user.username
                (findViewById<TextView>(R.id.bio)).text = user.bio
                (this.findViewById<ImageView>(R.id.avatar)).clipToOutline = true
                Glide.with(this).load(user.avatarUrlString).into(findViewById(R.id.avatar))
            }
        })
    }

    private fun observeUserRepos() {
        userViewModel.getUserRepos().observe(this, Observer { repos ->
            if (repos != null) {
                val recyclerView = findViewById<RecyclerView>(R.id.user_repo_list)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = RepoListAdapter(repos)
            }
        })
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH.equals(intent.action)) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            userViewModel.setUsername(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflate app bar menu
        menuInflater.inflate(R.menu.app_bar_menu, menu)

        //associate searchable config with the SearchView
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu!!.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return true
    }
}
