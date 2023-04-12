package com.example.session5try1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session5try1.databinding.FriendMessageBinding
import com.example.session5try1.databinding.MyMessageBinding

open class MessageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(renderMessage: RenderMessage) {}
}
class MyMessageViewHolder(private val binding: MyMessageBinding) : MessageVH(binding.root) {
    override fun bind(renderMessage: RenderMessage) {
        binding.myMessage.text = renderMessage.message
        binding.myTime.text = renderMessage.datetime
    }
}
class FriendMessageViewHolder(private val binding: FriendMessageBinding) : MessageVH(binding.root) {
    override fun bind(renderMessage: RenderMessage) {
        binding.friendMessage.text = renderMessage.message
        binding.friendTime.text = renderMessage.datetime
    }
}
class ChatAdapter (val messages: List<RenderMessage>) : RecyclerView.Adapter<MessageVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageVH {
        if (viewType == 0) {
            return MyMessageViewHolder(MyMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{
            return FriendMessageViewHolder(FriendMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: MessageVH, position: Int) {}

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val model = messages[position]
        return if (model.isYou) 0 else 1
    }
}