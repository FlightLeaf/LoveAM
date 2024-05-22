package com.stop.loveam.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CardList() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CardNewsBig()
        CardNewsSmall()
    }

}

@Preview(name = "CardList")
@Composable
private fun PreviewCardList() {
    CardList()
}