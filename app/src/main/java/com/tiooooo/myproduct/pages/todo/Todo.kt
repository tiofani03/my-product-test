package com.tiooooo.myproduct.pages.todo

import kotlin.random.Random

data class Todo(
    val id: String = generateRandomId(),
    var text: String = ""
)

fun generateRandomId(): String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..8)
        .map { Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}
