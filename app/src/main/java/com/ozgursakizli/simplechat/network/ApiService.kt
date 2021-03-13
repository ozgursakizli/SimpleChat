package com.ozgursakizli.simplechat.network

import com.ozgursakizli.simplechat.models.ApiMessagesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("jsonBlob/62455171-0fb1-11eb-9f83-ffcd873e5c3a")
    suspend fun getMessages(): Response<ApiMessagesResponse>

}