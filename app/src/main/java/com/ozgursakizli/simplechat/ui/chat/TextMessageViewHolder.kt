package com.ozgursakizli.simplechat.ui.chat

import android.view.View
import android.widget.TextView
import com.ozgursakizli.simplechat.R
import com.ozgursakizli.simplechat.models.ApiMessageItem

class TextMessageViewHolder(itemView: View) : BaseChatViewHolder(itemView) {

    private val tvNickname: TextView = itemView.findViewById(R.id.tvNickname)
    private val messageText: TextView = itemView.findViewById(R.id.tvMessage)

    override fun bind(messageItem: ApiMessageItem) {
        tvNickname.text = messageItem.user.nickname
        messageText.text = messageItem.text
    }

}