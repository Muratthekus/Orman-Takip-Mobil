package me.thekusch.ormantakipmobil.presentation.home

import android.icu.text.SimpleDateFormat
import android.text.format.DateFormat
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.thekusch.ormantakipmobil.core.Resource
import me.thekusch.ormantakipmobil.domain.useCase.GetDistrictsUseCase
import me.thekusch.ormantakipmobil.domain.useCase.GetFireDataUseCase
import me.thekusch.ormantakipmobil.presentation.components.FilterType
import me.thekusch.ormantakipmobil.util.CityUtil
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFireDataUseCase: GetFireDataUseCase,
    private val getDistrictsUseCase: GetDistrictsUseCase
) : ViewModel() {

    var selectedCity: String? = CityUtil.getCityList()[0]
    var selectedDistrict: String? = null
    var selectedConfidence: String? = null
    var selectedStartDate: String? = null
    var selectedEndDate: String? = null
    var selectedStartTime: String? = null

    private val _state = mutableStateOf(HomeScreenState())

    private val _districtState = mutableStateOf(GetDistrictsState())

    val state: State<HomeScreenState>
        get() = _state

    val districtsState: State<GetDistrictsState>
        get() = _districtState

    init {
        getFireData()
        getDistricts()
    }

    fun getFireData(
        city: Int? = CityUtil.getCityList().indexOf(selectedCity),
        district: Int? = null,
        startDate: String? = selectedStartDate,
        endDate: String? = selectedEndDate,
        confidence: String? = selectedConfidence
    ) {
        getFireDataUseCase(
            city,
            district,
            startDate,
            endDate,
            confidence
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeScreenState(data = it.data?.fireData ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(
                        error = it.message ?: "Beklenmedik bir hata oluştu lütfen tekrar deneyin"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDistricts() {
        getDistrictsUseCase()
            .onEach {
                when (it) {
                    is Resource.Success -> {
                        _districtState.value =
                            GetDistrictsState(data = it.data?.districts ?: emptyList())
                    }
                    is Resource.Error -> {
                        _districtState.value = GetDistrictsState(
                            error = it.message
                                ?: "Beklenmedik bir hata oluştu lütfen tekrar deneyin"
                        )
                    }
                    is Resource.Loading -> {
                        _districtState.value = GetDistrictsState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun setFilterValues(key: FilterType, value: String?) {
        when (key) {
            FilterType.CITY -> {
                selectedCity = value
            }
            FilterType.DISTRICT -> {
                selectedDistrict = value
            }
            FilterType.CONFIDENCE -> {
                selectedConfidence = value
            }
            FilterType.START_DATE -> {
                selectedStartDate = value
            }
            FilterType.END_DATE -> {
                selectedEndDate = value
            }
        }
    }

    fun getDistrictOfCity(
        cityName: String? = selectedCity
    ): List<String>? {
        val city = CityUtil.getCityList().find { it == cityName }
        val cityIndex = CityUtil.getCityList().indexOf(city)
        val districtIndex = if(cityIndex <= 0) 0 else cityIndex-1
        return districtsState.value.data.getOrNull(districtIndex)
    }

    fun getSelectedDistrictValue(
        districtList: List<String>?
    ): Int? {
        val index = districtList?.indexOf(selectedDistrict)
        return if(index ?: 0 <= 0) null else index?.plus(1)
    }


}