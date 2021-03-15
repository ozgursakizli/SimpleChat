package com.ozgursakizli.simplechat.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import androidx.appcompat.app.AppCompatActivity
import com.ozgursakizli.simplechat.R
import com.ozgursakizli.simplechat.databinding.ActivityMainBinding
import com.ozgursakizli.simplechat.managers.SessionManager
import com.ozgursakizli.simplechat.models.ApiUser
import com.ozgursakizli.simplechat.ui.chat.ChatActivity
import com.ozgursakizli.simplechat.utilities.AppDataConstants
import com.ozgursakizli.simplechat.utilities.Utils
import com.ozgursakizli.simplechat.utilities.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (SessionManager.user != null) {
            navigateToChat()
            return
        }

        initUi()
        initClicks()
        observeViewModel()
    }

    /**
     * set initial ui values here
     */
    private fun initUi() {
        binding.edtNickname.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(AppDataConstants.MAX_NICKNAME_CHARS))
    }

    private fun initClicks() {
        binding.btnStartChat.setOnClickListener {
            mainViewModel.checkNickname(binding.edtNickname.text.toString())
        }
    }

    private fun observeViewModel() {
        mainViewModel.data.observe(this, {
            if (it.not()) {
                shortToast(R.string.nickname_error)
                return@observe
            }

            openChat(binding.edtNickname.text.toString())
        })
    }

    private fun openChat(nickname: String) {
        val user = ApiUser(Utils.generateUserId(), null, nickname)
        SessionManager.logInUser(user)
        navigateToChat()
        binding.edtNickname.text?.clear()
    }

    private fun navigateToChat() {
        Intent(this, ChatActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }
}