package com.example.application09042023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.application09042023.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity(), Callback {
    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Connection.callbacks.add(this)
        binding.nameChat.text = intent.getStringExtra("username")
    }

    override fun onDestroy() {
        super.onDestroy()
        Connection.callbacks.remove(this)
    }

    override fun onOpen() {
        Connection.client.send("/chat ${intent.getIntExtra("chatId", 0)}")
    }

    override fun onMessage(message: ModelMessage) {
        TODO("Not yet implemented")
    }

    override fun onChat(chat: ModelDataChat) {
        binding.chat.adapter = MessagesAdapter(chat.messages.map { it.toRenderMessage(it.idUser == Info.userId, chat.getUser(it.idUser)) })
        binding.chat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
    }

    override fun onChats(chats: List<ModelChat>) {
        TODO("Not yet implemented")
    }

    override fun onPerson(person: User) {
        TODO("Not yet implemented")
    }
}