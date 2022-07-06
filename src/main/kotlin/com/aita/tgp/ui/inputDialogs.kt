package com.aita.tgp.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

fun showInputDialog(
    project: Project,
    message: String,
    title: String = "Input required",
    init: InputDialogHelper.() -> Unit
) = InputDialogHelper(project, message, title).apply {
    init()
    show()
}

class InputDialogHelper(
    private val project: Project,
    private val message: String,
    private val title: String = "Input required"
) {

    private lateinit var onInputListener: (String) -> Unit
    private lateinit var onEmptyListener: () -> Unit

    fun onInput(listener: (String) -> Unit) {
        onInputListener = listener
    }

    fun onEmpty(listener: () -> Unit) {
        onEmptyListener = listener
    }

    fun show() {
        val inputText = Messages.showInputDialog(project, message, title, Messages.getQuestionIcon()) ?: ""

        if (inputText.isBlank()) {
            if (::onEmptyListener.isInitialized) {
                onEmptyListener()
            }
            return
        }

        if (::onInputListener.isInitialized) {
            onInputListener(inputText)
        }
    }
}
