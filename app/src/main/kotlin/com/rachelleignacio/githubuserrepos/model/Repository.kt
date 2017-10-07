package com.rachelleignacio.githubuserrepos.model

import com.google.gson.annotations.SerializedName

/**
 * Created by rachelleignacio on 9/30/17.
 */
class Repository(@SerializedName("id") val id: Int,
                 @SerializedName("name") val name: String,
                 @SerializedName("description") val description: String?,
                 @SerializedName("language") val language: String) {

}