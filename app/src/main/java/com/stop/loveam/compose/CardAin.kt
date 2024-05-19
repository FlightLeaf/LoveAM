package com.stop.loveam.compose
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

@Composable
fun LoadMoreList() {
    val listState = rememberLazyListState()
    // 使用mutableStateListOf来构建可变的String列表
    val list = remember {
        mutableStateListOf<String>().apply {
            for (i in 0..20) {
                add("Item $i")
            }
        }
    }

    LazyColumn(
        state = listState,
    ) {
        // 显示list
        items(list) {
            Row {
                CardNewsSmall() // 假设这是一个显示新闻项的Composable函数
            }
        }
    }

    // 当LazyListState变化时，检查是否到达底部
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                // 检查是否接近列表的底部
                if (index >= (listState.layoutInfo.totalItemsCount - 6)) {
                    Log.d("LoadMoreList", "到达底部")
                    // 在列表末尾添加更多项目
                    for (i in list.size..list.size + 10) {
                        list.add("Item $i")
                    }
                }
            }
    }
}




