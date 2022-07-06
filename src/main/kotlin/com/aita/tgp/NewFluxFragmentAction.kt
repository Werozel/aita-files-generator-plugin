package com.aita.tgp

import com.aita.tgp.model.FluxFragmentConfig
import com.aita.tgp.ui.FluxFragmentConfigDialog
import com.aita.tgp.ui.showErrorMessage

class NewFluxFragmentAction : ActionBase(
    text = "Flux Fragment",
    description = "Add files of flux fragment"
) {

    private companion object {
        const val KEY_BASE_FRAGMENT: String = "%base_fragment"
        const val KEY_BASE_FRAGMENT_IMPORT: String = "%base_fragment_import"
        const val KEY_LAYOUT_ID: String = "%layout_id"
    }

    private val pathToFilenameSuffix =
        mapOf(
            "fragment/action_template.txt" to "Action.kt",
            "fragment/analytics_template.txt" to "Analytics.kt",
            "fragment/fragment_template.txt" to "Fragment.kt",
            "fragment/reducer_template.txt" to "Reducer.kt",
            "fragment/state_template.txt" to "State.kt",
            "fragment/view_model_template.txt" to "ViewModel.kt",
        )

    override fun onStart() {
        FluxFragmentConfigDialog(project, "Flux Screen Settings").apply {
            onCreate(::writeFiles)
            onError { errorText ->
                showErrorMessage("Error", errorText)
            }
            show()
        }
    }

    private fun writeFiles(config: FluxFragmentConfig) {
        val replacements =
            mapOf(
                KEY_BASE_FRAGMENT_IMPORT to config.baseFragmentImport,
                KEY_BASE_FRAGMENT to config.baseFragment,
                KEY_LAYOUT_ID to config.layoutId,
            )

        pathToFilenameSuffix.forEach { (filePath, filenameSuffix) ->
            writeFile(
                resourcesFilePath = filePath,
                outputFileNameSuffix = filenameSuffix,
                itemName = config.name,
                additionalReplacements = replacements,
            )
        }
    }
}
