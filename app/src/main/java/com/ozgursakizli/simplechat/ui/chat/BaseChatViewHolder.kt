package com.ozgursakizli.simplechat.ui.chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozgursakizli.simplechat.models.ApiMessageItem

open class BaseChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun bind(messageItem: ApiMessageItem) {
    }

    open fun handleMessage(messageItem: ApiMessageItem) {
        bind(messageItem)
    }

}