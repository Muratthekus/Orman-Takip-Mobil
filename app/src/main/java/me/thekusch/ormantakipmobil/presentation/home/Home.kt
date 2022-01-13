package me.thekusch.ormantakipmobil.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import me.thekusch.ormantakipmobil.ui.theme.Brown

@Composable
fun HomeScreen() {
    Surface(
        color = Brown,
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}