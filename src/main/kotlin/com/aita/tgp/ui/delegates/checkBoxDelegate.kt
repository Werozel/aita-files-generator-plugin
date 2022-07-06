package com.aita.tgp.ui.delegates

import javax.swing.JCheckBox
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun checkBox(checkbox: JCheckBox): ReadWriteProperty<Any, Boolean> = CheckBoxDelegate(checkbox)

private class CheckBoxDelegate(private val checkbox: JCheckBox) : ReadWriteProperty<Any, Boolean> {

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        checkbox.isSelected = value
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean = checkbox.isSelected
}
