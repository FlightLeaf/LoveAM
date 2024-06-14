package com.stop.loveam.compose


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import com.stop.loveam.compose.widget.CardNewsSmall
import com.stop.loveam.dao.Impl.NewsDaoImpl
import com.stop.loveam.entity.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun LoadMoreList() {
    val listState = rememberLazyListState()
    val newsList: MutableList<News> = remember { mutableStateListOf() }
    var isLoading by remember { mutableStateOf(false) }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 显示list
        items(newsList) {
            Row {
                CardNewsSmall(news = it) // 假设这是一个显示新闻项的Composable函数
            }
        }

        // 添加加载动画
        item {
            if (isLoading) {
                Loading()
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (index >= (listState.layoutInfo.totalItemsCount - 8)) {
                    if (isLoading) return@collect
                    isLoading = true
                    Log.d("LoadMoreList", "到达底部")
                    try {
                        if(newsList.size >= 100){
                            newsList.clear()
                        }
                        val initialNewsList = withContext(Dispatchers.IO) {
                            NewsDaoImpl()._news_list
                        }
                        newsList.addAll(initialNewsList)
                    } catch (e: Exception) {
                        Log.d("LoadMoreList", "初始化失败 $e")
                    } finally {
                        isLoading = false
                    }
                }
            }
    }
}





