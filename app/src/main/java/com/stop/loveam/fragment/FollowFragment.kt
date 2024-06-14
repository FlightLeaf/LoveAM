package com.stop.loveam.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.stop.loveam.R
import com.stop.loveam.compose.CardFollowUserList

class FollowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_follow, container, false)
        val composeSmall: ComposeView? = view.findViewById(R.id.compose_view_follow)
        composeSmall?.let {
            composeSmall.setContent {
                CardFollowUserList()
            }
        }
        return view
    }
}
