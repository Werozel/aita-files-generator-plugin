package com.aita.tgp

import com.aita.tgp.ui.showInputDialog

class NewRecyclerviewItemAction : ActionBase(
    text = "Recyclerview Item",
    description = "Add new DelegateDiffable Recyclerview item",
) {

    private companion object {
        const val RV_TEMPLATE_PATH: String = "rv_item_template.txt"
        const val ITEM_FILENAME_SUFFIX: String = "Item.kt"
    }

    override fun onStart() {
        showInputDialog(project, "Enter Item Name") {
            onInput { inputText: String ->
                writeFile(
                    resourcesFilePath = RV_TEMPLATE_PATH,
                    outputFileNameSuffix = ITEM_FILENAME_SUFFIX,
                    itemName = inputText,
                )
            }
        }
    }
}
