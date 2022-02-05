package me.thekusch.ormantakipmobil.data

import me.thekusch.ormantakipmobil.data.response.GetFireResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseService {

    @GET("mobil_orman_takip")
    suspend fun getFireData(
        @Query("cityValue") city: String?,
        @Query("districtValue") district: String?,
        @Query("startDateValue") startDate: String?,
        @Query("endDateValue") endDate: String?,
        @Query("confidenceValue") confidence: String?
    ): GetFireResponse?

}