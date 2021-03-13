package com.ozgursakizli.simplechat.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursakizli.simplechat.models.ApiMessagesResponse
import com.ozgursakizli.simplechat.repositories.ChatRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    private var _messages = MutableLiveData<ApiMessagesResponse>()
    val messages: LiveData<ApiMessagesResponse> = _messages

    fun getMessages() {
        viewModelScope.launch {
            chatRepository.getMessages().collect {
                if (it.isSuccessful) {
                    _messages.postValue(it.body())
                }
            }
        }
    }

}