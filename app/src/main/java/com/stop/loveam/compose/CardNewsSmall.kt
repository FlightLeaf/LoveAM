package com.stop.loveam.compose

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stop.loveam.R
import com.stop.loveam.activity.SettingsActivity

@Composable
fun CardNewsSmall() {
    val context = LocalContext.current
    val intent = remember { Intent(context, SettingsActivity::class.java) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(
                indication = null, // 禁用点击效果
                interactionSource = remember { MutableInteractionSource() } // 用于控制点击状态
            ) {
                context.startActivity(intent)
            },
        elevation = 1.dp,
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    // 使用Coil库的logo作为示例图片地址
                    .data("https://tse1-mm.cn.bing.net/th/id/OIP-C.xLdG5RUetK3jZCBy9k-V-AHaIA?rs=1&pid=ImgDetMain")
                    // 启用交叉淡入，使加载后的图片与背景渐变融合
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.book),
                contentDescription = null,
                modifier = Modifier.size(99.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 10.dp).height(99.dp)) {
                Text(
                    text = "Jetpack Compose 博物馆",
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8))
                )
                Text(
                    text = "本文档的部分内容可能已经过时，你可以参阅最新官方文档查看最新更新。",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "2024.5.16",
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                    //点赞数
                    val likeCount = 100
                    if (likeCount > 0) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xffff4141),
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append("$likeCount")
                                }
                                append("人喜欢")
                            },
                        )
                    } else {
                        Text(
                            text = "",
                            style = TextStyle(fontWeight = FontWeight.Light)
                        )
                    }
                    Text(
                        text = "水果日报",
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewCard_user() {
    CardNewsSmall()
}