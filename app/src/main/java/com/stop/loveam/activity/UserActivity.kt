package com.stop.loveam.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.stop.loveam.R
import com.stop.loveam.compose.LoadMoreList
import com.stop.loveam.style.ColorTools

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        ColorTools.setStatusBarColor(this, R.color.white)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val composeView : ComposeView = findViewById(R.id.compose_view)
        Thread{
            composeView.setContent {
                LoadMoreList()
            }
        }.start()
    }
}
