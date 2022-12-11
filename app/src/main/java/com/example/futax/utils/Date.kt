package com.example.futax.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Date {
    @JvmStatic
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM, dd, yyyy | H : mm"))
}