package com.aita.tgp.ui.delegates

import javax.swing.JComboBox
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> comboBox(comboBox: JComboBox<T>): ReadWriteProperty<Any, T> = ComboBoxDelegate(comboBox)

private class ComboBoxDelegate<T>(private val comboBox: JComboBox<T>) : ReadWriteProperty<Any, T> {

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        comboBox.selectedItem = value
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any, property: KProperty<*>): T = comboBox.selectedItem as T
}
