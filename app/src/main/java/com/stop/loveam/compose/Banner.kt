package com.stop.loveam.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Banner")
    }
}

@Preview(name = "Banner")
@Composable
private fun PreviewBanner() {
    Banner()
}