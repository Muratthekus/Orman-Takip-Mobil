package me.thekusch.ormantakipmobil.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.thekusch.ormantakipmobil.R
import me.thekusch.ormantakipmobil.data.response.FireData


@Composable
fun FireDataItem(
    firedata: FireData,
    isFavorite: Boolean,
    onItemClick: (FireData) -> Unit,
    onFavoriteClick: (FireData) -> Unit
) {
    val favorite by remember {
        mutableStateOf(isFavorite)
    }
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
    ) {
        // wrapper
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            // place - location
            Column(
                modifier = Modifier.weight(4f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "\uD83D\uDCCC Konum: ${firedata.city}/${firedata.district}",
                )

                Text(
                    text = "\uD83D\uDCC6 Uyarı zamanı: ${firedata.alertDate}",
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = "\uD83E\uDDED Enlem: ${firedata.latitude}/${firedata.longitude}",
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = "\uD83D\uDFE2 Güvenilirlik: ${firedata.confidence}",
                    modifier = Modifier.padding(top = 10.dp)
                )

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {

                Image(
                    painter = painterResource(
                        id =
                        if (favorite) R.drawable.ic_favorited
                        else R.drawable.ic_no_favorited
                    ),
                    contentDescription = "isFavorite",
                    Modifier
                        .clickable { onFavoriteClick(firedata) }
                        .size(40.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_map_24
                    ),
                    contentDescription = "mapIcon",
                    Modifier
                        .size(40.dp)
                        .padding(top = 10.dp)
                        .clickable {
                            onItemClick(firedata)
                        }
                )

            }
        }
    }
}

@Preview
@Composable
fun previewFireDataItem() {
    FireDataItem(
        firedata = FireData(
            city = "Antalya",
            district = "Gökova",
            alertDate = "27/07/2009",
            confidence = "Yüksek",
            latitude = 35.05354,
            longitude = 32.0492
        ),
        onItemClick = {},
        onFavoriteClick = {},
        isFavorite = false
    )
}