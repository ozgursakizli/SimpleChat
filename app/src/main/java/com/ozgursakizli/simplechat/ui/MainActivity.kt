package com.ozgursakizli.simplechat.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import androidx.appcompat.app.AppCompatActivity
import com.ozgursakizli.simplechat.R
import com.ozgursakizli.simplechat.databinding.ActivityMainBinding
import com.ozgursakizli.simplechat.utilities.AppDataConstants
import com.ozgursakizli.simplechat.utilities.AppDataConstants.KEY_NICKNAME
import com.ozgursakizli.simplechat.utilities.shortToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        initClicks()
    }

    /**
     * set initial ui values here
     */
    private fun initUi() {
        binding.edtNickname.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(AppDataConstants.MAX_NICKNAME_CHARS))
    }

    private fun initClicks() {
        binding.btnStartChat.setOnClickListener {
            openChat(binding.edtNickname.text.toString())
        }
    }

    private fun openChat(nickname: String) {
        if (isNicknameValid(nickname).not()) {
            shortToast(R.string.nickname_error)
            return
        }

        Intent(this, ChatActivity::class.java).apply {
            putExtra(KEY_NICKNAME, nickname)
            startActivity(this)
        }
    }

    /**
     * @param nickname value should be at least 2 characters
     * @return Boolean
     */
    private fun isNicknameValid(nickname: String) = nickname.trim().length >= 2

}