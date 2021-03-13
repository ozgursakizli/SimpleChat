package com.ozgursakizli.simplechat.models

import com.google.gson.annotations.SerializedName

data class ApiMessageItem(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
    @SerializedName("timestamp") var timestamp: Long,
    @SerializedName("user") var user: ApiUser
)
