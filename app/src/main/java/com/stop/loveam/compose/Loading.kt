package com.stop.loveam.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Loading(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(all = 30.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(all = 8.dp).size(36.dp),
            color = Color.Red
        )
        Text(
            text = "正在加载...",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(name = "Loading")
@Composable
private fun PreviewLoading() {
    Loading()
}
