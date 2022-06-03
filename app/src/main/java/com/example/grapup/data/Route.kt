package com.example.grapup.data


data class Route(
    val id: String,
    val description: String,
    val difficulty: Difficulty,
    val rockType: String,
    val heightMeters: Float,
    val belaying: Belaying,
    val rating: Float,
    val type: Type,
    val covered: Boolean,
    val author: User,
    val creationDate: String,
    val surrounding: List<String>,
    val photoUrl: String
)

data class User(
    val id: String,
    val name: String
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

enum class Belaying {
    SHRINGS, SPITS, HOOKS, NONE
}

enum class Type {
    INDOOR, OUTDOOR
}
