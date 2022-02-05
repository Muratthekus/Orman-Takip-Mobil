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
            .height(100.dp)
            .clickable { onItemClick(firedata) },
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.weight(4f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Yer")
                Text(
                    text = "${firedata.city}/${firedata.district}",
                    modifier = Modifier
                        .padding(top = 5.dp),
                    fontSize = 12.sp
                )

                Text(text = "Tarih", modifier = Modifier.padding(top = 10.dp))
                Text(text = firedata.alertDate, fontSize = 12.sp)


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
                    Modifier.clickable { onFavoriteClick(firedata) }
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