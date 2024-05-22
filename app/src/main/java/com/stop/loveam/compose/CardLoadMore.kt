package com.stop.loveam.compose
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

@Composable
fun LoadMoreList() {
    val listState = rememberLazyListState()
    val list: MutableList<String> = remember { mutableStateListOf() }
    //初始化页面
    SideEffect {
        for (i in 1..10) {
            list.add("Item $i")
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
                Log.d("LoadMoreList", "firstVisibleItemIndex: $index")
                if (index >= (listState.layoutInfo.totalItemsCount - 7)) {
                    Log.d("LoadMoreList", "到达底部")
                    for (i in list.size..list.size + 10) {
                        list.add("Item $i")
                    }
                }
            }
    }
}




