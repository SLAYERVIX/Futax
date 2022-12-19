package com.example.futax.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Date {
    @JvmStatic
    val dateTime: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy | HH:mm"))
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
    val time: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
}