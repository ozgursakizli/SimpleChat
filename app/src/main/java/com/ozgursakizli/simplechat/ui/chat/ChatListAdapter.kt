package com.ozgursakizli.simplechat.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgursakizli.simplechat.R
import com.ozgursakizli.simplechat.managers.SessionManager
import com.ozgursakizli.simplechat.models.ApiMessageItem

class ChatListAdapter : RecyclerView.Adapter<BaseChatViewHolder>() {

    private var messageList = arrayListOf<ApiMessageItem>()

    companion object {
        const val VIEW_TYPE_TEXT_INCOMING = 1
        const val VIEW_TYPE_TEXT_OUTGOING = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseChatViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_TEXT_INCOMING -> TextMessageViewHolder(inflater.inflate(R.layout.message_item_text_incoming, parent, false))
            else -> TextMessageViewHolder(inflater.inflate(R.layout.message_item_text_outgoing, parent, false))
        }
    }

    override fun onBindViewHolder(holderConversation: BaseChatViewHolder, position: Int) {
        holderConversation.handleMessage(messageList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        val isOutgoing = message.user.id == SessionManager.user!!.id

        return if (isOutgoing) {
            VIEW_TYPE_TEXT_OUTGOING
        } else {
            VIEW_TYPE_TEXT_INCOMING
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun setData(data: ArrayList<ApiMessageItem>) {
        this.messageList.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(message: ApiMessageItem) {
        this.messageList.add(message)
        notifyItemRangeInserted(this.messageList.size, 1)
    }

}