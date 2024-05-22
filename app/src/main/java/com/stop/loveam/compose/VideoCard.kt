package com.stop.loveam.compose

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stop.loveam.R
import com.stop.loveam.activity.SettingsActivity

@Composable
fun VideoCard(
    id: String = "0001",
    title: String = "5:20AM",
    time: String = "2024.5.19",
    likeCount: String = "0",
    source: String = "水果日报",
    imgUrl: String = "https://tse1-mm.cn.bing.net/th/id/OIP-C.xLdG5RUetK3jZCBy9k-V-AHaIA?rs=1&pid=ImgDetMain"
) {
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
                intent.putExtra("id", id)
                context.startActivity(intent)
            },
        elevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = title,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8))
                )
                Divider()
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = time,
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                    //点赞数
                    val like: Int = likeCount.toInt()
                    if (like > 0) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xffff4141),
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(likeCount)
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
                        text = source,
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
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