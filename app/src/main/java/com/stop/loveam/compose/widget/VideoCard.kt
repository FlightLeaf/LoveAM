package com.stop.loveam.compose.widget

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alibaba.fastjson2.JSONObject
import com.stop.loveam.R
import com.stop.loveam.activity.VideosActivity
import com.stop.loveam.dao.Impl.VideoDaoImpl
import com.stop.loveam.entity.videos.Videos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun VideoCard(
    videos: Videos = Videos()
) {
    val context = LocalContext.current
    var initialVideosURL: String
    val scope = rememberCoroutineScope() // 创建一个协程作用域
    val showingDialog = remember { mutableStateOf(false) }
    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },

            title = {
                Text(text = "标题")
            },
            text = {
                Text(text = "副标题")
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        showingDialog.value = false
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text("按钮")
                }
            },
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 10.dp, end = 10.dp)
            .clickable(
                indication = null, // 禁用点击效果
                interactionSource = remember { MutableInteractionSource() } // 用于控制点击状态
            ) {
                scope.launch {
                    initialVideosURL = withContext(Dispatchers.IO) {
                        VideoDaoImpl().get_video_url(videos.id.toString())
                    }
                    val jsonObject = JSONObject()
                    jsonObject["id"] = videos.id.toString()
                    jsonObject["name"] = videos.name
                    jsonObject["artist"] = videos.artistname
                    jsonObject["cover"] = videos.cover
                    jsonObject["url"] = initialVideosURL

                    val intent = Intent(context, VideosActivity::class.java)
                    intent.putExtra("videos", jsonObject.toString())
                    context.startActivity(intent)
                }
            },
        elevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Text(
                    text = videos.name,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8))
                )
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = videos.publishtime,
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                    Text(
                        text = videos.artistname,
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(videos.cover)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.video_lay),
                contentDescription = null,
            )
        }
    }
}

@Preview(name = "VideoCard")
@Composable
private fun PreviewVideoCard() {
    VideoCard()
}