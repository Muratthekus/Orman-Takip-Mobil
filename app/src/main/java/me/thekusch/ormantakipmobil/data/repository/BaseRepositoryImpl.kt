package me.thekusch.ormantakipmobil.data.repository

import me.thekusch.ormantakipmobil.core.Repository
import me.thekusch.ormantakipmobil.core.Resource
import me.thekusch.ormantakipmobil.core.invokeApi
import me.thekusch.ormantakipmobil.data.BaseService
import me.thekusch.ormantakipmobil.data.response.GetFireResponse
import me.thekusch.ormantakipmobil.domain.repository.BaseRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(
    private val baseService: BaseService
) : Repository(), BaseRepository {

    override suspend fun getFireData(
        city: String?,
        district: String?,
        startDate: String?,
        endDate: String?,
        confidence: String?
    ): Resource<GetFireResponse?> {
        return invokeApi {
            baseService.getFireData(city, district, startDate, endDate, confidence)
        }
    }
}