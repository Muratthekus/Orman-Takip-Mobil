package me.thekusch.ormantakipmobil.data.response

data class GetDistrictsResponse(
    val message: String,
    val districts: List<List<String>>
)