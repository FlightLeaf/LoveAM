package com.stop.loveam.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.stop.loveam.R
import com.stop.loveam.compose.VideosList


class VideoFragment: Fragment() {
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        val composeSmall: ComposeView? = view.findViewById(R.id.compose_view_videos)
        composeSmall?.let {
            composeSmall.setContent {
                VideosList()
            }
        }
        return view
    }
}