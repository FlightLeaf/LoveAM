package com.stop.loveam.compose

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.stop.loveam.compose.widget.CardFollowUser
import com.stop.loveam.dao.Impl.NewsDaoImpl
import com.stop.loveam.entity.FollowUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CardFollowUserList() {
    val listState = rememberLazyListState()
    val followUserList: MutableList<FollowUser> = remember { mutableStateListOf() }

    LaunchedEffect(UInt) {
        try {
            val initialNewsList = withContext(Dispatchers.IO) {
                NewsDaoImpl()._follow_list
            }
            followUserList.addAll(initialNewsList)
        } catch (e: Exception) {
            Log.d("LoadMoreList", "初始化失败 $e")
        }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 显示list
        items(followUserList) {
            Row {
                CardFollowUser(
                    followUser = it
                )
            }
        }
    }
}