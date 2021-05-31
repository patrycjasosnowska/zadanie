package com.example.zadanie.utils.extensions

fun String.removeUrl(): String {
    val regex = Regex("http.*")
    return regex.replace(this, "")
}

fun String.getUrlFromDescription(): String {
    return this.substringAfter("\t")
}
