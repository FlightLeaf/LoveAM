package com.stop.loveam.compose.widget

import android.content.Intent
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.stop.loveam.activity.SettingsActivity
import com.stop.loveam.entity.News

@Composable
fun CardNewsBig(news: News) {
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
        elevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            SquareImage(news.imageurl)
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = news.title,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8))
                )
                Divider()
                Text(
                    text = news.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    //点赞数
                    val likeCount = news.likes
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
                        text = news.email,
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                }
            }
        }
    }
}
