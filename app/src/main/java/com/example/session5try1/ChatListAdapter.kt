package com.example.session5try1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session5try1.databinding.ChatBinding

class ChatViewHolder(val binding: ChatBinding) : RecyclerView.ViewHolder(binding.root)
class ChatListAdapter(val chats: List<ModelChat>, val onClickChat: OnClickChat) : RecyclerView.Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(ChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        if (Info.idUser != chats[position].first.id) {
            holder.binding.name.text = "${chats[position].first.firstname} ${chats[position].first.lastname}"
        }else{
            holder.binding.name.text = "${chats[position].second.firstname} ${chats[position].second.lastname}"
        }
        holder.binding.body.setOnClickListener {
            onClickChat.onClick(chats[position])
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}
interface OnClickChat{
    fun onClick(chat: ModelChat)
}