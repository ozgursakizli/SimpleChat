package com.ozgursakizli.simplechat.managers

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ozgursakizli.simplechat.application.SimpleChatApplication
import com.ozgursakizli.simplechat.models.ApiUser
import com.ozgursakizli.simplechat.utilities.AppDataConstants.KEY_APP_PREFERENCES
import com.ozgursakizli.simplechat.utilities.AppDataConstants.USER_PROFILE

object SharedPreferencesManager {

    private val sharedPreferences: SharedPreferences = SimpleChatApplication.getInstance().getSharedPreferences(KEY_APP_PREFERENCES, Context.MODE_PRIVATE)

    fun saveUser(user: ApiUser) {
        sharedPreferences.edit().putString(USER_PROFILE, Gson().toJson(user)).apply()
    }

    fun getUser(): ApiUser? {
        val userJson = sharedPreferences.getString(USER_PROFILE, "")
        if (userJson.isNullOrEmpty().not()) return Gson().fromJson(userJson, ApiUser::class.java)
        return null
    }

    fun removeUser() {
        sharedPreferences.edit().remove(USER_PROFILE).apply()
    }

}