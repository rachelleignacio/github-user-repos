package com.rachelleignacio.githubuserrepos.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rachelleignacio.githubuserrepos.R
import com.rachelleignacio.githubuserrepos.model.Repository

/**
 * Created by rachelleignacio on 10/6/17.
 */
class RepoListAdapter(private val repos: MutableList<Repository>)
        : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.repo_list_item_layout,
                parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val repo = repos[position]
        holder!!.repoNameTv.text = repo.name
        holder.repoDescription.text = repo.description
        holder.repoLanguageTv.text = repo.language
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var repoNameTv: TextView = itemView.findViewById(R.id.name)
        var repoLanguageTv: TextView = itemView.findViewById(R.id.language)
        var repoDescription: TextView = itemView.findViewById(R.id.description)
    }
}