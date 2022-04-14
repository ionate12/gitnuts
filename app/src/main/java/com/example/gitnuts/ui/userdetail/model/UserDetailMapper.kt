package com.example.gitnuts.ui.userdetail.model

import android.net.Uri
import com.example.gitnuts.data.model.UserDetail
import com.example.gitnuts.data.model.UserType
import com.example.gitnuts.utils.Mapper

class UserDetailMapper(): Mapper<UserDetail, UserDetailUIModel>() {
    override fun map(input: UserDetail): UserDetailUIModel = with(input) {
        UserDetailUIModel(
            id ?: -1,
            this.login ?: "",
            this.type ?: UserType.User,
            this.htmlURL ?: "",
            this.avatarURL?.let { Uri.parse(it)} ?: Uri.EMPTY,
            this.name ?: "",
            this.company ?: "",
            this.blog ?: "",
            this.location ?: "",
            this.email ?: "",
            this.publicRepos ?: 0,
            this.publicGists ?: 0,
            this.followers ?: 0,
            this.following ?: 0
        )
    }
}