package com.stop.loveam.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Card_user(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Card_user")
    }
}

@Preview(name = "Card_user")
@Composable
private fun PreviewCard_user() {
    Card_user()
}