package me.thekusch.ormantakipmobil.presentation.home

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeUiState(
    val activity: AppCompatActivity,
    val scaffoldState: ScaffoldState,
    val scope: CoroutineScope,
    val drawerstate: DrawerState,
    val listState: LazyListState,
) {
    fun openDrawer() = scope.launch {
        drawerstate.open()
    }

    fun isOpen() = drawerstate.isOpen

    fun showSnackbar(message: String) = scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(message)
    }

}

@Composable
fun rememberHomeUiState(
    activity: AppCompatActivity = LocalContext.current as AppCompatActivity,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerstate: DrawerState = rememberDrawerState(DrawerValue.Closed),
    listState: LazyListState = rememberLazyListState(),
) = remember {
    HomeUiState(
        activity,
        scaffoldState,
        scope,
        drawerstate,
        listState,
    )
}