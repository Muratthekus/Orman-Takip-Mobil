package me.thekusch.ormantakipmobil.presentation.home


data class GetDistrictsState(
    val data: List<List<String>> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
