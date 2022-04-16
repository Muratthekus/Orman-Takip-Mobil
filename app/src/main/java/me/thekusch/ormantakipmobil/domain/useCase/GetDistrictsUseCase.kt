package me.thekusch.ormantakipmobil.domain.useCase

import kotlinx.coroutines.flow.flow
import me.thekusch.ormantakipmobil.core.Resource
import me.thekusch.ormantakipmobil.data.response.GetDistrictsResponse
import me.thekusch.ormantakipmobil.domain.repository.BaseRepository
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDistrictsUseCase @Inject constructor(
    private val baseRepository: BaseRepository
) {

    operator fun invoke(): Flow<Resource<GetDistrictsResponse?>> = flow {
        try {
            emit(Resource.Loading<GetDistrictsResponse?>())
            when (val data =
                baseRepository.getDistricts()) {
                is Resource.Success -> {
                    emit(Resource.Success<GetDistrictsResponse?>(data.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error<GetDistrictsResponse?>(data.message))
                }
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    emit(
                        Resource.Error<GetDistrictsResponse?>(
                            e.localizedMessage ?: "Bir hata olmuş gibi duruyor"
                        )
                    )
                }
                is IOException -> {
                    emit(Resource.Error<GetDistrictsResponse?>("Lütfen internet bağlantınızı kontrol edin"))
                }
                else -> {
                    emit(Resource.Error<GetDistrictsResponse?>(e.localizedMessage))
                }
            }
        }
    }
}