package com.aita.tgp.ui

import com.aita.tgp.model.BaseFragmentsInfo
import com.aita.tgp.model.FluxFragmentConfig
import com.aita.tgp.ui.delegates.comboBox
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import java.awt.Window
import java.awt.event.WindowEvent
import java.awt.event.WindowFocusListener
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JTextField

class FluxFragmentConfigDialog(
    project: Project,
    titleText: String,
) : DialogWrapper(project) {

    private companion object {
        const val INSET = 5
        const val FOCUS_REQUEST_DELAY = 10L
        const val COMBO_BOX_WIDTH = 400
        const val INITIAL_LAYOUT_ID_TEXT = "R.layout."
    }

    private val baseFragmentsInfo = BaseFragmentsInfo()

    private var onCreateListener: ((FluxFragmentConfig) -> Unit)? = null
    private var onErrorListener: ((String) -> Unit)? = null

    private val itemNameTextField = JTextField()
    private val baseFragmentComboBox: JComboBox<String> = ComboBox(baseFragmentsInfo.fragments.toTypedArray())
    private val layoutIdTextField = JTextField()

    private var baseFragment: String by comboBox(baseFragmentComboBox)


    init {
        title = titleText
        setOKButtonText("Create")
        init()
    }

    fun onCreate(listener: (FluxFragmentConfig) -> Unit) {
        onCreateListener = listener
    }

    fun onError(listener: (String) -> Unit) {
        onErrorListener = listener
    }

    override fun createCenterPanel(): JComponent? {
        itemNameTextField.requestFocusDelayed(window)
        baseFragmentComboBox.setSize(COMBO_BOX_WIDTH, baseFragmentComboBox.height)
        layoutIdTextField.text = INITIAL_LAYOUT_ID_TEXT

        val formBuilder = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Screen name: "), itemNameTextField)
            .addLabeledComponent(JBLabel("Base fragment: "), baseFragmentComboBox, INSET)
            .addLabeledComponent(JBLabel("Layout id: "), layoutIdTextField, INSET)

        return formBuilder.panel
    }

    override fun doOKAction() {
        super.doOKAction()

        val screenName = itemNameTextField.text

        if (screenName.isNullOrEmpty()) {
            onErrorListener?.invoke("Enter screen name")
            return
        }

        onCreateListener?.invoke(
            FluxFragmentConfig(
                name = screenName,
                baseFragment = baseFragment,
                baseFragmentImport = baseFragmentsInfo.getImportOfFragment(baseFragment),
                layoutId = layoutIdTextField.text.orEmpty()
            )
        )
    }

    private fun JComponent.requestFocusDelayed(window: Window) =
        window.addWindowFocusListener(
            object : WindowFocusListener {
                override fun windowLostFocus(e: WindowEvent?) = Unit

                override fun windowGainedFocus(e: WindowEvent?) =
                    delayForUi(FOCUS_REQUEST_DELAY) {
                        window.removeWindowFocusListener(this)
                        this@requestFocusDelayed.requestFocusInWindow()
                    }
            }
        )
}
