package com.aita.tgp.ui.delegates

import javax.swing.JTextField
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun textField(textFiled: JTextField): ReadWriteProperty<Any, String?> = TextFieldDelegate(textFiled)

private class TextFieldDelegate(private val textFiled: JTextField) : ReadWriteProperty<Any, String?> {

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        textFiled.text = value
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String? = textFiled.text
}
