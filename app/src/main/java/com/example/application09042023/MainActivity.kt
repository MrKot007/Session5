package com.example.application09042023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.application09042023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Callback {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Connection.callbacks.add(this)
        Connection.client.connect()
    }

    override fun onOpen() {
        Connection.client.send("/chats")
    }

    override fun onMessage(message: ModelMessage) {
        runOnUiThread {
            Toast.makeText(this@MainActivity, "message", Toast.LENGTH_LONG).show()
        }
    }

    override fun onChat(chat: ModelDataChat) {
        TODO("Not yet implemented")
    }

    override fun onChats(chats: List<ModelChat>) {
        runOnUiThread {
            binding.chatRecycler.adapter = ChatRecyclerAdapter(chats, object: OnClickChat{
                override fun onClick(chat: ModelChat) {
                    val chatIntent = Intent(this@MainActivity, ChatActivity::class.java)
                    chatIntent.putExtra("username", if (chat.first.id != Info.userId) "${chat.first.firstname} ${chat.first.lastname}"
                    else "${chat.second.firstname} ${chat.second.lastname}")
                    chatIntent.putExtra("chatId", chat.id)
                    startActivity(chatIntent)
                }
            })
            binding.chatRecycler.layoutManager = LinearLayoutManager(this)
        }

    }

    override fun onPerson(person: User) {
        binding.cardView.setCardBackgroundColor(person.getUserColor())
    }
}