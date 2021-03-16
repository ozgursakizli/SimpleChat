package com.ozgursakizli.simplechat.ui.chat

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ozgursakizli.simplechat.R
import com.ozgursakizli.simplechat.models.ApiMessageItem
import com.ozgursakizli.simplechat.utilities.Utils
import com.ozgursakizli.simplechat.utilities.displayCircularImage

class TextMessageViewHolder(itemView: View) : BaseChatViewHolder(itemView) {

    private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
    private val tvNickname: TextView = itemView.findViewById(R.id.tvNickname)
    private val messageText: TextView = itemView.findViewById(R.id.tvMessage)
    private val tvTime: TextView = itemView.findViewById(R.id.tvTime)

    override fun bind(messageItem: ApiMessageItem) {
        imgAvatar.displayCircularImage(messageItem.user.avatar, R.drawable.ic_person)
        tvNickname.text = messageItem.user.nickname
        messageText.text = messageItem.text
        tvTime.text = Utils.formatTime(messageItem.timestamp)
    }

}