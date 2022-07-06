package com.aita.tgp.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments.of as argOf

internal class StringUtilTest {

    @Test
    fun `toPackage() with empty string - returns empty string`() {
        assertEquals("", "".toPackage())
    }

    @Test
    fun `toPackage() with not empty string - returns package string`() {
        assertEquals("package com.test.project\n", "com.test.project".toPackage())
    }

    @ParameterizedTest
    @MethodSource("replaceMultipleParams")
    fun `replaceMultiple() rest`(inputText: String, params: Map<String, String>, expected: String) =
        assertEquals(expected, inputText.replaceMultiple(params))

    companion object PostfixNumberSource {

        @JvmStatic
        fun replaceMultipleParams(): Stream<Arguments> =
            Stream.of(
                argOf("hello my friend", emptyMap<String, String>(), "hello my friend"),
                argOf("hello my friend", mapOf("my" to "our"), "hello our friend"),
                argOf(
                    "hello my friend",
                    mapOf("my" to "our", "friend" to "friends"),
                    "hello our friends",
                ),
                argOf(
                    "hello my friend",
                    mapOf("my" to "our", "friend" to "friends", "hello" to "welcome"),
                    "welcome our friends",
                ),
            )
    }
}
