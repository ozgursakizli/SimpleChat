package com.ozgursakizli.simplechat.repositories

import com.ozgursakizli.simplechat.models.ApiMessagesResponse
import com.ozgursakizli.simplechat.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class ChatRepository(private val apiService: ApiService) {

    private val dispatcher = Dispatchers.Default

    suspend fun getMessages(): Flow<Response<ApiMessagesResponse>> {
        return flow {
            val response = apiService.getMessages()
            emit(response)
        }.flowOn(dispatcher)
    }

}