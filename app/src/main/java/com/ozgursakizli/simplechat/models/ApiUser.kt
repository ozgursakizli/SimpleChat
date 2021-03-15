package com.ozgursakizli.simplechat.models

import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("id") val id: String,
    @SerializedName("avatarURL") val avatar: String?,
    @SerializedName("nickname") val nickname: String
)
