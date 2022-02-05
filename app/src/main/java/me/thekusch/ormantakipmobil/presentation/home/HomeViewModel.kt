package me.thekusch.ormantakipmobil.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.thekusch.ormantakipmobil.core.Resource
import me.thekusch.ormantakipmobil.domain.useCase.GetFireDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFireDataUseCase: GetFireDataUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())

    val state: State<HomeScreenState>
        get() = _state

    init {
        getFireData()
    }


    fun getFireData(
        city: String? = null,
        district: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        confidence: String? = null
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

}