package com.ozgursakizli.simplechat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ozgursakizli.simplechat.databinding.ActivityChatBinding
import com.ozgursakizli.simplechat.utilities.AppDataConstants

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var nickname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadIntent()
        initUi()
    }

    private fun loadIntent() {
        nickname = intent.getStringExtra(AppDataConstants.KEY_NICKNAME).toString()
    }

    private fun initUi() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.let {
            it.title = nickname
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

}