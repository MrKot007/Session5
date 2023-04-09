package com.example.application09042023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.application09042023.databinding.ChatItemBinding

class ChatViewHolder(val binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root)
class ChatRecyclerAdapter(val list: List<ModelChat>) : RecyclerView.Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        if (list[position].first.id != Info.userId){
            holder.binding.textView.text = list[position].first.firstname
        }else{
            holder.binding.textView.text = list[position].second.firstname
        }

    }

}