package com.example.gitnuts.ui.userdetail.model

import android.net.Uri
import com.example.gitnuts.data.model.UserType

data class UserDetailUIModel(
    val id: Long,
    val username: String,
    val type: UserType,
    val gitUrl: String,
    val avatarUrl: Uri?,
    val fullName: String,
    val company: String,
    val blog:String,
    val location: String,
    val email: String,
    val repos: Long,
    val gists: Long,
    val followers: Long,
    val following: Long
)
