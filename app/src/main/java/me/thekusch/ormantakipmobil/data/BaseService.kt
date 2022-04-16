package me.thekusch.ormantakipmobil.data

import me.thekusch.ormantakipmobil.data.response.GetDistrictsResponse
import me.thekusch.ormantakipmobil.data.response.GetFireResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseService {

    @GET("mobil_orman_takip")
    suspend fun getFireData(
        @Query("cityValue") city: Int?,
        @Query("districtValue") district: Int?,
        @Query("startDateValue") startDate: String?,
        @Query("endDateValue") endDate: String?,
        @Query("confidenceValue") confidence: String?
    ): GetFireResponse?

    @GET("get_districts")
    suspend fun getDistricts(): GetDistrictsResponse?

}