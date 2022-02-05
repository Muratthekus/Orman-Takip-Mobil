package me.thekusch.ormantakipmobil.presentation.home

import me.thekusch.ormantakipmobil.data.response.FireData

data class HomeScreenState(
    val data: List<FireData> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)