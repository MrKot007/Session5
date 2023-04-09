package com.example.application09042023

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
)
data class ModelMessage(
    val id: Int,
    val message: String,
    val idChat: Int,
    val idUser: Int,
    val isYou: Boolean,
    val datetime: String,
    val isAudio: Boolean
)
data class ModelChat(
    val id: Int,
    val first: User,
    val second: User
)
data class ModelDataChat(
    val chat: ModelChat,
    val messages: List<ModelArchivedMessage>
)
data class ModelArchivedMessage(
    val id: Int,
    val message: String,
    val datetime: String,
    val idUser: Int,
    val idChat: Int,
    val isAudio: Boolean
)