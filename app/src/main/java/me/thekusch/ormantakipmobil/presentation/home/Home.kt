package me.thekusch.ormantakipmobil.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import me.thekusch.ormantakipmobil.presentation.components.Drawer
import me.thekusch.ormantakipmobil.presentation.components.FilterType
import me.thekusch.ormantakipmobil.presentation.components.FireDataItem
import me.thekusch.ormantakipmobil.util.CityUtil
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(
    navController: NavController?,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val listData = viewModel.state.value
    val districtList: MutableState<List<String>?> = remember {
        mutableStateOf(emptyList())
    }
    val homeUiState = rememberHomeUiState()
    Scaffold(
        scaffoldState = homeUiState.scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Anasayfa",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                elevation = 2.dp,
            )
        },
        drawerContent = {
            Drawer(
                activity = homeUiState.activity,
                cityList = CityUtil.getCityList(),
                districtList = districtList.value,
                confidenceList = CityUtil.getCityList(),
                onFilterItemSelected = { key: FilterType, value: String ->
                    viewModel.setFilterValues(key, value)
                    if (key == FilterType.CITY) {
                        viewModel.selectedDistrict = null
                        districtList.value = viewModel.getDistrictOfCity()
                    }
                },
                onItemSelected = { key: FilterType, value: String? ->
                    viewModel.setFilterValues(key, value)
                },
                onApplyClicked = {
                    viewModel.getFireData(
                        district = viewModel.getSelectedDistrictValue(districtList.value)
                    )
                }
            )
        },
        drawerGesturesEnabled = true,
        drawerShape = object : Shape {
            override fun createOutline(
                size: Size,
                layoutDirection: LayoutDirection,
                density: Density
            ): Outline {
                return Outline.Rectangle(Rect(0f, 0f, size.width * 2 / 3, size.height))
            }

        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    state = homeUiState.listState,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    items(
                        listData.data
                    ) { fireData ->
                        FireDataItem(
                            firedata = fireData,
                            isFavorite = false,
                            onItemClick = {
                                homeUiState.openMap(it.longitude,it.latitude)
                            },
                            onFavoriteClick = {

                            }
                        )
                        Divider(
                            color = Color.Green,
                            thickness = 2.dp,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }

            }
            if (listData.isLoading) {
                CircularProgressIndicator()
            }
        }
    }

}
