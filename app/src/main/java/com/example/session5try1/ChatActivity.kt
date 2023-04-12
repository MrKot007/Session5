package com.example.session5try1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session5try1.databinding.ActivityChatBinding
import com.example.session5try1.databinding.ChatBinding
import com.google.gson.Gson

class ChatActivity : AppCompatActivity(), Callback {
    private lateinit var binding: ActivityChatBinding
    private lateinit var currentChat: ModelDataChat
    private var messages: MutableList<RenderMessage> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Connection.callbacks.add(this)
        binding.chatName.text = intent.getStringExtra("name")
        Connection.client.send("/chat ${intent.getIntExtra("chatId", 0)}")
        binding.back.setOnClickListener {
            finish()
        }
        binding.send.setOnClickListener {
            if (binding.messageInput.text.toString() != "") {
                val sendMessage: ModelSendMessage = ModelSendMessage(binding.messageInput.text.toString(), intent.getIntExtra("chatId", 0), false)
                val sendMessageJson = Gson().toJson(sendMessage)
                Connection.client.send(sendMessageJson)
                binding.messageInput.text = null
            }else{
                Toast.makeText(this@ChatActivity, "Сообщение не должно быть пустым!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

        }


    }

    override fun onOpen() {}

    override fun onMessage(message: ModelMessage) {
        runOnUiThread {
            if (message.id == 0) {
                Toast.makeText(this@ChatActivity, message.message, Toast.LENGTH_LONG).show()
            }
            Log.d("ERRR", message.toString())
            val newMessage = message.toRenderMessage(currentChat.chat.first)
            messages.add(newMessage)
            binding.mainChat.adapter!!.notifyItemInserted(messages.lastIndex)
        }

    }

    override fun onChat(chat: ModelDataChat) {
        runOnUiThread {
            currentChat = chat
            messages = chat.messages.map {it.toRenderMessage(Info.idUser == it.idUser, chat.getUser(it.idUser))}
                .toMutableList()
            binding.mainChat.adapter = ChatAdapter(messages)
            binding.mainChat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true) }
    }

    override fun onChats(chats: List<ModelChat>) {}

    override fun onPerson(person: User) {}

    override fun onDestroy() {
        super.onDestroy()
        Connection.callbacks.remove(this)
    }
}