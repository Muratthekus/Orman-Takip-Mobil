package me.thekusch.ormantakipmobil.data.response

data class GetFireResponse(
    val fireData: List<FireData>,
    val message: String
)