package me.thekusch.ormantakipmobil.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.thekusch.ormantakipmobil.R
import me.thekusch.ormantakipmobil.ui.theme.OrmanTakipMobilTheme

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    cityList: List<String> = listOf(),
    districtList: List<String> = listOf(),
    confidenceList: List<String> = listOf(),
    onFilterItemSelected: ((key: FilterType, value: String) -> Unit)? = null
) {

    Column(
        modifier
            .fillMaxHeight()
            .width(200.dp)
            .padding(start = 24.dp, top = 48.dp)
    ) {

        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "App Icon",
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)
        )

        OTDropDownMenu(
            mList = cityList,
            label = "Şehir",
            onItemSelected = {
                onFilterItemSelected?.invoke(FilterType.CITY,it)
            })

        OTDropDownMenu(
            mList = districtList,
            label = "İlçe",
            onItemSelected = {
                onFilterItemSelected?.invoke(FilterType.DISTRICT,it)
            })

        OTDropDownMenu(
            mList = confidenceList,
            label = "Güvenilirlik",
            onItemSelected = {
                onFilterItemSelected?.invoke(FilterType.CONFIDENCE,it)
            })

    }
}

@Preview
@Composable
fun DrawerPreview() {
    OrmanTakipMobilTheme {
        Drawer()
    }
}