package me.thekusch.ormantakipmobil.data.response

data class FireData(
    val alertDate: String,
    val city: String,
    val confidence: String,
    val district: String,
    val latitude: Double,
    val longitude: Double
)