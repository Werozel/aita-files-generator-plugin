package com.aita.tgp.utils

import java.io.InputStream

fun readStringFromRes(source: Any, filePath: String): String {
    val stream: InputStream = source::class.java.classLoader.getResourceAsStream(filePath) ?: return ""
    return stream.bufferedReader().readText()
}
