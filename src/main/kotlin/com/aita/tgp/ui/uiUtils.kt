package com.aita.tgp.ui

import java.awt.EventQueue

fun delayForUi(millis: Long, action: () -> Unit) =
    Thread {
        Thread.sleep(millis)
        EventQueue.invokeLater(action)
    }.start()
