package me.thekusch.ormantakipmobil.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import me.thekusch.ormantakipmobil.presentation.components.FireDataItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val listData = viewModel.state.value
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val listState = rememberLazyListState()
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Anasayfa",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

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
                        onItemClick = {},
                        onFavoriteClick = {}
                    )
                }
            }

        }
        if (listData.isLoading) {
            CircularProgressIndicator()
        }
    }
}
