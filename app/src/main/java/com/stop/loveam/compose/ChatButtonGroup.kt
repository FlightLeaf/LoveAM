package com.stop.loveam.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stop.loveam.activity.SettingsActivity

@Composable
fun ChatButtonGroup() {
    val buttonColor = Color(0xffffffff) // 自定义颜色
    val buttonTextColor = Color.Black
    val buttonShape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)

    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp , horizontal = 26.dp)
        ){
            AIButton(
                imageVector = Icons.Rounded.Edit,
                name = "随机风景",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(16.dp))
            AIButton(
                imageVector = Icons.Rounded.DateRange,
                name = "摸鱼日历",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )

        }
        Spacer(Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp , horizontal = 26.dp)
        ){
            AIButton(
                imageVector = Icons.Rounded.Email,
                name = "读懂世界",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(16.dp))
            AIButton(
                imageVector = Icons.Rounded.Favorite,
                name = "星座数据",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp , horizontal = 26.dp)
        ){
            AIButton(
                imageVector = Icons.Rounded.Send,
                name = "随机英语",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(16.dp))
            AIButton(
                imageVector = Icons.Rounded.AccountBox,
                name = "随机头像",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp , horizontal = 26.dp)
        ){
            AIButton(
                imageVector = Icons.Rounded.Build,
                name = "文章纠错",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(16.dp))
            AIButton(
                imageVector = Icons.Rounded.Star,
                name = "文章润色",
                clazz = SettingsActivity::class.java,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = buttonTextColor
                ),
                shape = buttonShape,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Preview
@Composable
private fun PreviewChatButtonGroup() {
    ChatButtonGroup()
}