package com.ozgursakizli.simplechat.managers

import com.ozgursakizli.simplechat.models.ApiUser

object SessionManager {
    var user: ApiUser? = null
        get() {
            if (field == null) field = SharedPreferencesManager.getUser()
            return field
        }

    fun logInUser(user: ApiUser) {
        this.user = user
        SharedPreferencesManager.saveUser(user)
    }
}