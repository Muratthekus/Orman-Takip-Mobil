package me.thekusch.ormantakipmobil.util

sealed class Screen(val route: String) {

    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
}