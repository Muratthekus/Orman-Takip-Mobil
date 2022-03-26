package me.thekusch.ormantakipmobil.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Filter3
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import me.thekusch.ormantakipmobil.presentation.components.Drawer
import me.thekusch.ormantakipmobil.presentation.components.FilterType
import me.thekusch.ormantakipmobil.presentation.components.FireDataItem

@Composable
fun HomeScreen(
    navController: NavController?,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val listData = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val drawerstate = rememberDrawerState(DrawerValue.Closed)
    val listState = rememberLazyListState()
    val openDrawer = {
        scope.launch {
            drawerstate.open()
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
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
                actions = {
                    IconButton(
                        onClick = {
                            openDrawer()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Filtre"
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        listData.data
                    ) { fireData ->
                        FireDataItem(
                            firedata = fireData,
                            isFavorite = false,
                            onItemClick = {

                            },
                            onFavoriteClick = {

                            }
                        )
                    }
                }

            }
            if (listData.isLoading) {
                CircularProgressIndicator()
            }
            ModalDrawer(
                content = {

                },
                drawerState = drawerstate,
                gesturesEnabled = drawerstate.isOpen,
                drawerContent = {
                    Drawer(
                        cityList = viewModel.getCityList() ,
                        districtList = viewModel.getCityList(),
                        confidenceList = viewModel.getCityList(),
                        onFilterItemSelected = {key: FilterType, value: String ->
                            when(key) {
                                FilterType.CITY -> {
                                    viewModel.selectedCity = value
                                }
                                FilterType.DISTRICT -> {
                                    viewModel.selectedDistrict = value
                                }
                                FilterType.CONFIDENCE -> {
                                    viewModel.selectedConfidence = value
                                }
                                FilterType.START_DATE -> {
                                    viewModel.selectedStartDate = value
                                }
                                FilterType.END_DATE -> {
                                    viewModel.selectedEndDate = value
                                }
                            }
                        }
                    )
                }
            )
        }
    }

}
