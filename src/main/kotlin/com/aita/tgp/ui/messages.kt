package com.aita.tgp.ui

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.ui.Messages
import javax.swing.Icon

private const val GROUP_ID = "tgp_main_group"

/**
 * Отображение нотификейшена - всплывающая плашка справа внизу экрана и вывод в лог событий
 */
fun showNotification(title: String, message: String, type: NotificationType = NotificationType.INFORMATION) =
    Notifications.Bus.notify(
        Notification(GROUP_ID, title, message, type)
    )

/**
 * Отображение информационного сообщения - небольшое окно по центру экрана
 */
fun showInfoMessage(title: String, message: String) =
    showMessage(title, message, Messages.getInformationIcon())

/**
 * Отображение сообщения об ошибке - небольшое окно по центру экрана
 */
fun showErrorMessage(title: String, message: String) =
    showMessage(title, message, Messages.getErrorIcon())

private fun showMessage(title: String, message: String, icon: Icon) =
    Messages.showMessageDialog(message, title, icon)
