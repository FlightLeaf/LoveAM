package com.stop.loveam.compose.widget

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stop.loveam.R
import com.stop.loveam.activity.SettingsActivity
import com.stop.loveam.entity.FollowUser

@Composable
fun CardFollowUser(followUser: FollowUser = FollowUser(
    "user@qq.com","image","user"
)) {
    val context = LocalContext.current
    val intent = remember { Intent(context, SettingsActivity::class.java) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clickable(
                indication = null, // 禁用点击效果
                interactionSource = remember { MutableInteractionSource() } // 用于控制点击状态
            ) {
                intent.putExtra("key", "news")
                context.startActivity(intent)
            },
        elevation = 0.dp
    ) {

        Box(){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(start = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        // 使用Coil库的logo作为示例图片地址
                        .data(followUser.image)
                        // 启用交叉淡入，使加载后的图片与背景渐变融合
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.book),
                    contentDescription = null,
                    modifier = Modifier.size(39.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 6.6.dp, top = 2.dp, bottom = 2.dp)
                        .height(39.dp)) {
                    Text(
                        text = followUser.name,
                        style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8)),
                        fontSize = 13.6.sp,
                    )
                    Text(
                        text = followUser.followUserEmail,
                        style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xffcbcbcb))
                    )
                }
            }
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfff5f5f5)),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 5.dp)
            ) {
                Text(
                    text = "取消关注",
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8)),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(name = "CardFollowUser")
@Composable
private fun PreviewCardFollowUser() {
    CardFollowUser()
}