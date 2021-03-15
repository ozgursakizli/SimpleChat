package com.ozgursakizli.simplechat.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozgursakizli.simplechat.databinding.ActivityChatBinding
import com.ozgursakizli.simplechat.managers.SessionManager
import com.ozgursakizli.simplechat.models.ApiUser
import com.ozgursakizli.simplechat.utilities.ConversationEvent
import com.ozgursakizli.simplechat.utilities.EventObserver
import com.ozgursakizli.simplechat.utilities.EventType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var currentUser: ApiUser
    private lateinit var chatListAdapter: ChatListAdapter
    private val chatViewModel: ChatViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentUser = SessionManager.user!!
        initUi()
        initClicks()
        observeViewModel()
    }

    private fun initUi() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.let {
            it.title = currentUser.nickname
            it.setDisplayHomeAsUpEnabled(true)
        }
        chatListAdapter = ChatListAdapter()
        initChatList()
    }

    private fun initClicks() {
        binding.imgSend.setOnClickListener { sendMessage() }
    }

    private fun initChatList() {
        binding.chatList.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            setHasFixedSize(true)
            setKeyboardAutoHide(true)
            setSupportsChangeAnimations(false)
            setVerticalAutoScrollOnResize(true)
            adapter = chatListAdapter
        }
    }

    private fun observeViewModel() {
        with(chatViewModel) {
            messages.observe(this@ChatActivity, { apiResponse ->
                val initialMessages = apiResponse.messages
                initialMessages.sortBy { it.timestamp }
                chatListAdapter.setData(initialMessages)
                scrollToBottom()
            })
            event.observe(this@ChatActivity, EventObserver(::eventHandler))
            getMessages()
        }
    }

    private fun scrollToBottom() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            binding.chatList.scrollToBottom()
        }
    }

    private fun sendMessage() {
        if (binding.edtMessage.text.toString().isNotEmpty()) {
            val text = binding.edtMessage.text.toString().trim()
            chatViewModel.sendTextMessage(text)
            binding.edtMessage.text = null
        }
    }

    private fun eventHandler(eventType: EventType) {
        when (eventType) {
            is ConversationEvent.MessageSentEvent -> {
                chatListAdapter.addData(eventType.message)
                scrollToBottom()
            }
            else -> Unit
        }
    }

}