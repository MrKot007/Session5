package com.example.application09042023

import android.graphics.Color

data class ModelResponse<T>(
    val type: String,
    val body: T
)
data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val avatar: String
) {
    fun getUserColor() : Int {
        val r = 123
        val g = 123
        val b = 123
        return Color.rgb(r, g, b)
    }
}
data class ModelMessage(
    val id: Int,
    val message: String,
    val idChat: Int,
    val idUser: Int,
    val isYou: Boolean,
    val datetime: String,
    val isAudio: Boolean
) {
    fun toRenderMessage(user: User) : RenderMessage {
        return RenderMessage(id, message, user, datetime, isYou, isAudio)
    }
}
data class ModelChat(
    val id: Int,
    val first: User,
    val second: User
)
data class ModelDataChat(
    val chat: ModelChat,
    val messages: List<ModelArchivedMessage>
) {
    fun getUser(idUser: Int) : User{
        if (idUser == chat.first.id) {
            return chat.first
        }else{
            return chat.second
        }
    }
}
data class ModelArchivedMessage(
    val id: Int,
    val message: String,
    val datetime: String,
    val idUser: Int,
    val idChat: Int,
    val isAudio: Boolean
) {
    fun toRenderMessage(isYou: Boolean, user: User) : RenderMessage {
        return RenderMessage(id, message, user, datetime, isYou, isAudio)
    }
}
data class RenderMessage(
    val id: Int,
    val message: String,
    val user: User,
    val datetime: String,
    val isYou: Boolean,
    val isAudio: Boolean
)