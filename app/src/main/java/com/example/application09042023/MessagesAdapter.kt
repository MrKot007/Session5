package com.example.application09042023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.application09042023.databinding.FirendMessageBinding
import com.example.application09042023.databinding.MyMessageBinding

open class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(renderMessage: RenderMessage){}
}
class MyMessageViewHolder(val binding: MyMessageBinding) : VH(binding.root) {
    override fun bind(renderMessage: RenderMessage) {
        binding.message.text = renderMessage.message
        binding.time.text = renderMessage.datetime
    }
}
class FriendMessageViewHolder(val binding: FirendMessageBinding) : VH(binding.root) {
    override fun bind(renderMessage: RenderMessage) {
        binding.message.text = renderMessage.message
        binding.time.text = renderMessage.datetime
    }
}
class MessagesAdapter(val list: List<RenderMessage>) : RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        if (viewType == 0) {
            return MyMessageViewHolder(MyMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else {
            return FriendMessageViewHolder(FirendMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {}

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        val model = list[position]
        return if (model.isYou) 0 else 1
    }
}
