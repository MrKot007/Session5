package com.example.session5try1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.session5try1.databinding.ActivityChatBinding
import com.example.session5try1.databinding.ChatBinding

class ChatActivity : AppCompatActivity(), Callback {
    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Connection.callbacks.add(this)
    }

    override fun onOpen() {
        Connection.client.send("/chat ${intent.getIntExtra("chatId", 0)}")
    }

    override fun onMessage(message: ModelMessage) {
        TODO("Not yet implemented")
    }

    override fun onChat(chat: ModelDataChat) {
        TODO("Not yet implemented")
    }

    override fun onChats(chats: List<ModelChat>) {}

    override fun onPerson(person: User) {}

    override fun onDestroy() {
        super.onDestroy()
        Connection.callbacks.remove(this)
    }
}