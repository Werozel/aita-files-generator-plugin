package com.aita.tgp.model

class BaseFragmentsInfo {

    private companion object {
        const val ABS_AITA_FRAGMENT = "AbsAitaFragment"
        const val ABS_AITA_CHILD_FRAGMENT = "AbsAitaChildFragment"
        const val ABS_AITA_ARCH_FRAGMENT = "AbsAitaArchFragment"
        const val ABS_AITA_FLOW_FRAGMENT = "AbsAitaFlowFragment"
        const val ABS_ARCH_BOTTOM_SHEET_DIALOG_FRAGMENT = "AbsArchBottomSheetDialogFragment"
    }

    val fragments =
        listOf(
            ABS_AITA_FRAGMENT,
            ABS_AITA_CHILD_FRAGMENT,
            ABS_AITA_ARCH_FRAGMENT,
            ABS_AITA_FLOW_FRAGMENT,
            ABS_ARCH_BOTTOM_SHEET_DIALOG_FRAGMENT,
        )

    private val fragmentsToImports =
        mapOf(
            ABS_AITA_FRAGMENT to "com.aita.app.fragment.$ABS_AITA_FRAGMENT",
            ABS_AITA_CHILD_FRAGMENT to "com.aita.arch.infra.$ABS_AITA_CHILD_FRAGMENT",
            ABS_AITA_ARCH_FRAGMENT to "com.aita.arch.infra.$ABS_AITA_ARCH_FRAGMENT",
            ABS_AITA_FLOW_FRAGMENT to "com.aita.arch.infra.$ABS_AITA_FLOW_FRAGMENT",
            ABS_ARCH_BOTTOM_SHEET_DIALOG_FRAGMENT to "com.aita.arch.infra.$ABS_ARCH_BOTTOM_SHEET_DIALOG_FRAGMENT",
        )

    fun getImportOfFragment(fragment: String): String = fragmentsToImports[fragment].orEmpty()
}
