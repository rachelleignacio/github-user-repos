package com.rachelleignacio.githubuserrepos.model

import com.google.gson.annotations.SerializedName

/**
 * Created by rachelleignacio on 9/30/17.
 */
class User(@SerializedName("id") val id: Int,
           @SerializedName("login") val username: String,
           @SerializedName("avatar_url") val avatarUrlString: String,
           @SerializedName("bio") val bio: String) {
}