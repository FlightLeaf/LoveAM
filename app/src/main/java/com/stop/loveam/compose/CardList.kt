package com.stop.loveam.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stop.loveam.compose.widget.CardNewsBig
import com.stop.loveam.compose.widget.CardNewsSmall
import com.stop.loveam.entity.News

@Composable
fun CardList(newList: MutableList<News> = mutableListOf(News(), News())) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (newList.isEmpty()) {
            item {
                CardNewsBig(news = newList.get(0))
                CardNewsSmall(news = newList.get(1))
            }
        }
    }

}

@Preview(name = "CardList")
@Composable
private fun PreviewCardList() {
    CardList()
}