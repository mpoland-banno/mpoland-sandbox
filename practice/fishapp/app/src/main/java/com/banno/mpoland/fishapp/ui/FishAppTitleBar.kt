package com.banno.mpoland.fishapp.ui

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun FishAppTitleBar() {
    TopAppBar(
        title={ Text("Fish App") }
    )
}