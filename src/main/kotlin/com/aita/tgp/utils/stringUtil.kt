package com.aita.tgp.utils

private const val PACKAGE: String = "package"

fun String.replaceMultiple(vararg replacements: Pair<String, String>): String =
    replaceMultiple(replacements.toMap())

fun String.replaceMultiple(replacements: Map<String, String>): String {
    var result = this
    replacements.forEach { (from, to) ->
        result = result.replace(from, to)
    }
    return result
}

fun String.toPackage(): String =
    if (this.isEmpty()) {
        ""
    } else {
        "$PACKAGE $this\n"
    }
