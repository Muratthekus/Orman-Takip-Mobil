package me.thekusch.ormantakipmobil.domain.repository

import me.thekusch.ormantakipmobil.core.Resource
import me.thekusch.ormantakipmobil.data.response.GetFireResponse

interface BaseRepository {

    suspend fun getFireData(
        city: String?,
        district: String?,
        startDate: String?,
        endDate: String?,
        confidence: String?
    ): Resource<GetFireResponse?>
}