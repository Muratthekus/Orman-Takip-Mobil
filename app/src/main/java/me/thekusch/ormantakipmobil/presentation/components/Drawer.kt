package me.thekusch.ormantakipmobil.presentation.components

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.thekusch.ormantakipmobil.R

@Composable
fun Drawer(
    activity: AppCompatActivity,
    modifier: Modifier = Modifier,
    cityList: List<String> = listOf(),
    districtList: List<String>? = listOf(),
    confidenceList: List<String> = listOf(),
    onFilterItemSelected: ((key: FilterType, value: String) -> Unit)? = null,
    onItemSelected: (key: FilterType, date: String?) -> Unit,
    onApplyClicked: () -> Unit
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
                .align(Alignment.CenterHorizontally)
        )

        OTDropDownMenu(
            mList = cityList,
            label = "Şehir",
            onItemSelected = {
                onFilterItemSelected?.invoke(FilterType.CITY, it)
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
                onFilterItemSelected?.invoke(FilterType.CONFIDENCE, it)
            })

        OTDatePicker(
            activity = activity,
            label = "Başlangıç Tarihi",
            onItemSelected = {
                onItemSelected.invoke(FilterType.START_DATE, it)
            }
        )

        OTDatePicker(
            activity = activity,
            label = "Bitiş Tarihi",
            onItemSelected = {
                onItemSelected.invoke(FilterType.END_DATE, it)
            }
        )

        Button(
            onClick = { onApplyClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Text(text = "Uygula")
        }

    }
}

