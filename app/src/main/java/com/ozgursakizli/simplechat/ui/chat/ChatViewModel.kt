package com.ozgursakizli.simplechat.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursakizli.simplechat.managers.SessionManager
import com.ozgursakizli.simplechat.models.ApiMessageItem
import com.ozgursakizli.simplechat.models.ApiMessagesResponse
import com.ozgursakizli.simplechat.repositories.ChatRepository
import com.ozgursakizli.simplechat.utilities.ConversationEvent
import com.ozgursakizli.simplechat.utilities.Event
import com.ozgursakizli.simplechat.utilities.EventType
import com.ozgursakizli.simplechat.utilities.Utils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    private var _messages = MutableLiveData<ApiMessagesResponse>()
    val messages: LiveData<ApiMessagesResponse> = _messages
    private val _event = MutableLiveData<Event<EventType>>()
    val event: LiveData<Event<EventType>> get() = _event

    fun getMessages() {
        viewModelScope.launch {
            chatRepository.getMessages().collect {
                if (it.isSuccessful) {
                    _messages.postValue(it.body())
                }
            }
        }
    }

    fun sendTextMessage(text: String) {
        viewModelScope.launch {
            val messageItem = ApiMessageItem(Utils.generateRandomId(), text, System.currentTimeMillis(), SessionManager.user!!)
            _event.postValue(Event(ConversationEvent.MessageSentEvent(messageItem)))
        }
    }

}