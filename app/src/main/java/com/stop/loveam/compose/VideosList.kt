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
import androidx.compose.ui.tooling.preview.Preview
import com.stop.loveam.compose.widget.VideoCard
import com.stop.loveam.dao.Impl.VideoDaoImpl
import com.stop.loveam.entity.videos.Videos
import com.stop.loveam.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun VideosList() {
    val listState = rememberLazyListState()
    val videosList: MutableList<Videos> = remember { mutableStateListOf() }
    var isLoading by remember { mutableStateOf(false) }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 显示list
        items(videosList) {
            Row {
                VideoCard(videos = it) // 假设这是一个显示新闻项的Composable函数
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
                LogUtils.d("LoadMoreList", "index: $index")
                if (index >= (listState.layoutInfo.totalItemsCount - 4)) {
                    if (isLoading) return@collect
                    isLoading = true
                    Log.d("LoadMoreList", "到达底部")
                    try {
                        if(videosList.size >= 50){
                            videosList.clear()
                        }
                        val initialNewsList = withContext(Dispatchers.IO) {
                            VideoDaoImpl()._video_list
                        }
                        videosList.addAll(initialNewsList)
                    } catch (e: Exception) {
                        Log.d("LoadMoreList", "初始化失败 $e")
                    } finally {
                        isLoading = false
                    }
                }
            }
    }
}

@Preview(name = "VideosList")
@Composable
private fun PreviewVideosList() {
    VideosList()
}