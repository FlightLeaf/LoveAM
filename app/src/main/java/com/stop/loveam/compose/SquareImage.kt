package com.stop.loveam.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stop.loveam.R

@Composable
fun SquareImage() {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            // 设置宽高比为1:1，实现正方形
            .aspectRatio(1f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                // 使用Coil库的logo作为示例图片地址
                .data("https://tse1-mm.cn.bing.net/th/id/OIP-C.xLdG5RUetK3jZCBy9k-V-AHaIA?rs=1&pid=ImgDetMain")
                // 启用交叉淡入，使加载后的图片与背景渐变融合
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.book),
            contentDescription = null,
            modifier = Modifier.matchParentSize(), // 保证Image和Box大小一致
            contentScale = ContentScale.Crop
        )
    }
}