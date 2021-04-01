package com.suy.bts.model.data

data class ListChecklistModel(
    val id: Int? = null,
    val name: String? = null,
    val items: ItemChecklistModel? = null,
    val checklistCompletionStatus: Boolean? = null
)
