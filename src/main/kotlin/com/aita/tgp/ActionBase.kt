package com.aita.tgp

import com.aita.tgp.ui.showNotification
import com.aita.tgp.utils.readStringFromRes
import com.aita.tgp.utils.replaceMultiple
import com.aita.tgp.utils.toPackage
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKey
import com.intellij.openapi.application.Application
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsActions.ActionDescription
import com.intellij.openapi.util.NlsActions.ActionText
import com.intellij.psi.JavaDirectoryService
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiPackage
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.idea.KotlinLanguage

abstract class ActionBase(
    @ActionText text: String,
    @ActionDescription description: String,
) : AnAction(
    text,
    description,
    KotlinFileType.INSTANCE.icon,
) {

    protected companion object {
        const val PSI_ELEMENT: String = "psi.Element"

        const val KEY_NAME: String = "%name"
        const val KEY_PACKAGE: String = "%package"
    }

    protected lateinit var project: Project
        private set

    private lateinit var currentDirectory: PsiDirectory

    private lateinit var packageName: String

    private val application: Application = ApplicationManager.getApplication()

    final override fun actionPerformed(event: AnActionEvent) {
        val project: Project? = event.project
        if (project == null) {
            showNotification("TGP plugin error", "project is null", NotificationType.ERROR)
            return
        }
        this.project = project

        val currentDirectory: PsiDirectory? = event.getData(DataKey.create(PSI_ELEMENT))
        if (currentDirectory == null) {
            showNotification("TGP plugin error", "currentDirectory is null", NotificationType.ERROR)
            return
        }
        this.currentDirectory = currentDirectory

        val psiPackage: PsiPackage? = JavaDirectoryService.getInstance().getPackage(currentDirectory)
        packageName = psiPackage?.qualifiedName.orEmpty()

        onStart()
    }

    abstract fun onStart()

    protected fun writeFile(
        resourcesFilePath: String,
        outputFileNameSuffix: String,
        itemName: String,
        additionalReplacements: Map<String, String> = emptyMap(),
    ) {
        val capitalizedItemName = itemName.capitalize()
        val template = readStringFromRes(this, resourcesFilePath)

        val fileText = template
            .replaceMultiple(
                KEY_NAME to capitalizedItemName,
                KEY_PACKAGE to packageName.toPackage(),
            )
            .replaceMultiple(additionalReplacements)
            .trimStart()

        val fileFactory = PsiFileFactory.getInstance(project);
        val file = fileFactory.createFileFromText(KotlinLanguage.INSTANCE, fileText)
        val fileName = "$capitalizedItemName$outputFileNameSuffix"

        // TODO check file for existence

        application.runWriteAction {
            currentDirectory.copyFileFrom(fileName, file)
        }
    }
}
