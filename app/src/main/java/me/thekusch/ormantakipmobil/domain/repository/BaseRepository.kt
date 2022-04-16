package me.thekusch.ormantakipmobil.domain.repository

import me.thekusch.ormantakipmobil.core.Resource
import me.thekusch.ormantakipmobil.data.response.GetDistrictsResponse
import me.thekusch.ormantakipmobil.data.response.GetFireResponse

interface BaseRepository {

    suspend fun getFireData(
        city: Int?,
        district: Int?,
        startDate: String?,
        endDate: String?,
        confidence: String?
    ): Resource<GetFireResponse?>

    suspend fun getDistricts(): Resource<GetDistrictsResponse?>
}