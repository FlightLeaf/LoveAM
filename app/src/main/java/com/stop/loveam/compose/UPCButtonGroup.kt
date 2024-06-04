package com.stop.loveam.compose

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stop.loveam.R
import com.stop.loveam.activity.ExploreActivity
import com.stop.loveam.compose.widget.SquareImage

@Composable
fun UPCButtonGroup() {
    val context = LocalContext.current
    val clazz = remember { ExploreActivity::class.java }
    val intent = remember { Intent(context, clazz) }
    val images = rememberSaveable { // 使用 rememberSaveable 以保持状态
        listOf(
            R.drawable.upc_l,
            R.drawable.upc_up,
            R.drawable.upc_book,
            R.drawable.upc_t,
            R.drawable.upc_sun,
            R.drawable.upc_flower,
            R.drawable.upc_lab,
            R.drawable.upc_night,
            R.drawable.upc_road,
        )
    }

    val text = remember {
        listOf(
            "随机风景",
            "摸鱼日历",
            "读懂世界",
            "星座数据",
            "素材文件",
            "随机英语",
            "随机头像",
            "文章纠错",
            "文章润色",
        )
    }
    Column {
        for (i in images.indices step 3) {
            Row {
                for (j in 0 until 3) {
                    val index = i + j
                    if (index < images.size) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .clickable {
                                    //case语句
                                    when (index) {
                                        0 -> {
                                            intent.putExtra("key", "scenery")
                                            context.startActivity(intent)
                                        }

                                        1 -> {
                                            intent.putExtra("key", "calendar")
                                            context.startActivity(intent)
                                        }

                                        2 -> {
                                            intent.putExtra("key", "world")
                                            context.startActivity(intent)
                                        }

                                        3 -> {
                                            intent.putExtra("key", "star")
                                            context.startActivity(intent)
                                        }

                                        4 -> {
                                            intent.putExtra("key", "file")
                                            context.startActivity(intent)
                                        }

                                        5 -> {
                                            intent.putExtra("key", "english")
                                            context.startActivity(intent)
                                        }

                                        6 -> {
                                            intent.putExtra("key", "head")
                                            context.startActivity(intent)
                                        }

                                        7 -> {
                                            intent.putExtra("key", "amend")
                                            context.startActivity(intent)
                                        }

                                        8 -> {
                                            intent.putExtra("key", "optimization")
                                            context.startActivity(intent)
                                        }

                                        else -> {
                                            intent.putExtra("key", "render")
                                            context.startActivity(intent)
                                        }
                                    }
                                },
                        ){
                            AsyncImage( // 假设使用 AsyncImage
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(images[index])
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                                        append(text[index])
                                    }
                                },
                                fontSize = 18.sp,
                                color = Color(0xffffffff),
                                maxLines = 1,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 30.dp)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .size(120.dp)
                                .padding(4.dp)
                        ) {
                            // 可以在这里放置占位符或空白内容
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewUPCButtonGroup() {
    UPCButtonGroup(

    )
}