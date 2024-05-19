package com.stop.loveam.compose

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AIButton(
    imageVector: ImageVector,
    name: String,
    clazz: Class<*>,
    colors: ButtonColors,
    shape: RoundedCornerShape,
    tint: Color = Color.Red,
    modifier: Modifier,
    render: String = "render"
) {
    val context = LocalContext.current
    val intent = remember { Intent(context, clazz) }

    Button(
        modifier = modifier,
        onClick = {
            intent.putExtra("key", render)
            context.startActivity(intent)
        },
        colors = colors,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 3.dp,
        ),
        shape = shape,
    ) {
        // 使用 Box 来确保图标居中
        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = name, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

