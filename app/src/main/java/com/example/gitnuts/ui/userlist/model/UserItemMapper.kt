package com.example.gitnuts.ui.userlist.model

import android.net.Uri
import com.example.gitnuts.data.model.User
import com.example.gitnuts.data.model.UserType
import com.example.gitnuts.utils.Mapper

class UserItemMapper(): Mapper<User, UserItemUIModel>() {
    override fun map(input: User): UserItemUIModel = with(input) {
        UserItemUIModel(id = id ?: -1,
            username = this.login ?: "",
            type = this.type ?: UserType.User,
            gitUrl = this.htmlURL ?: "",
            avatarUrl = this.avatarURL?.let { Uri.parse(it) })
    }
}