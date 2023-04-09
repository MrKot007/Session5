package com.example.application09042023


import android.view.Display.Mode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

object Connection {
    const val url = "ws://95.31.130.149:8085/chat"
    val callbacks: MutableList<Callback> = mutableListOf()
    val client = object: WebSocketClient(URI("$url/chat"), mapOf("userId" to "6")) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            callbacks.forEach {
                it.onOpen()
            }
        }

        override fun onMessage(message: String) {
            if ("\"type\": \"person\"" in message) {
                val modelMessage = Gson().fromJson<ModelResponse<ModelMessage>>(message, object:
                    TypeToken<ModelResponse<ModelMessage>>(){}.type).body
                callbacks.forEach {
                    it.onMessage(modelMessage)
                }
            }
            if ("\"type\": \"chats\"" in message) {
                val modelChats = Gson().fromJson<ModelResponse<List<ModelChat>>>(message, object: TypeToken<ModelResponse<List<ModelChat>>>(){}.type).body
                callbacks.forEach {
                    it.onChats(modelChats)
                }
            }
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {}

        override fun onError(ex: Exception?) {}

    }
}
interface Callback {
    fun onOpen()
    fun onMessage(message: ModelMessage)
    fun onChat(chat: ModelDataChat)
    fun onChats(chats: List<ModelChat>)
    fun onPerson(person: User)
}