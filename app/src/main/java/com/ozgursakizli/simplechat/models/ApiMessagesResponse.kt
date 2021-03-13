package com.ozgursakizli.simplechat.models

import com.google.gson.annotations.SerializedName

data class ApiMessagesResponse(
    @SerializedName("messages") val messages: ArrayList<ApiMessageItem>
)