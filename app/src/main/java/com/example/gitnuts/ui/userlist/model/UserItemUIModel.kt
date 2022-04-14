package com.example.gitnuts.ui.userlist.model

import android.net.Uri
import androidx.annotation.ColorRes
import com.example.gitnuts.R
import com.example.gitnuts.data.model.UserType
import com.example.gitnuts.data.model.UserType.*


data class UserItemUIModel(
    val id: Long,
    val username: String,
    val type: UserType,
    val gitUrl: String,
    val avatarUrl: Uri?
) {
    @ColorRes
    val typeBkgColor: Int = when(type) {
        Organization ->  R.color.yellow
        User -> R.color.teal_700
    }
}