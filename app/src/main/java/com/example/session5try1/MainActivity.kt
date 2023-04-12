package com.example.session5try1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session5try1.databinding.ActivityMainBinding

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

    override fun onMessage(message: ModelMessage) {}

    override fun onChat(chat: ModelDataChat) {}

    override fun onChats(chats: List<ModelChat>) {
        Log.d("Chats", chats.toString())
        runOnUiThread{
            binding.chatsRecycler.adapter = ChatListAdapter(chats, object: OnClickChat{
                override fun onClick(chat: ModelChat) {
                    val chatIntent = Intent(this@MainActivity, ChatActivity::class.java)
                    chatIntent.putExtra("name", if (chat.first.id != Info.idUser) "${chat.first.firstname} ${chat.first.lastname}"
                    else "${chat.second.firstname} ${chat.second.lastname}")
                    chatIntent.putExtra("chatId", chat.id)
                    startActivity(chatIntent)
                }
            })
            binding.chatsRecycler.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onPerson(person: User) {
        runOnUiThread {
            Toast.makeText(this@MainActivity, person.firstname, Toast.LENGTH_SHORT).show()
        }
    }
}