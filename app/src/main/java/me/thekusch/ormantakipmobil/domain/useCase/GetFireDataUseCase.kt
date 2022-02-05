package me.thekusch.ormantakipmobil.domain.useCase

import me.thekusch.ormantakipmobil.domain.repository.BaseRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.thekusch.ormantakipmobil.core.Resource
import me.thekusch.ormantakipmobil.data.response.GetFireResponse
import retrofit2.HttpException
import java.io.IOException

class GetFireDataUseCase @Inject constructor(
    private val baseRepository: BaseRepository
){
    operator fun invoke(
        city: String? = null,
        district: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        confidence: String? = null
    ): Flow<Resource<GetFireResponse?>> = flow {
        try {
            emit(Resource.Loading<GetFireResponse?>())
            when (val data =
                baseRepository.getFireData(city, district, startDate, endDate, confidence)) {
                is Resource.Success -> {
                    emit(Resource.Success<GetFireResponse?>(data.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error<GetFireResponse?>(data.message))
                }
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    emit(
                        Resource.Error<GetFireResponse?>(
                            e.localizedMessage ?: "Bir hata olmuş gibi duruyor"
                        )
                    )
                }
                is IOException -> {
                    emit(Resource.Error<GetFireResponse?>("Lütfen internet bağlantınızı kontrol edin"))
                }
                else -> {
                    emit(Resource.Error<GetFireResponse?>(e.localizedMessage))
                }
            }
        }
    }
}