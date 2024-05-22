package com.stop.loveam.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.stop.loveam.R
import com.stop.loveam.compose.CardNewsBig
import com.stop.loveam.compose.CardNewsSmall

class RecoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reco, container, false)
        val composeBig: ComposeView? = view.findViewById(R.id.compose_view_big)
        val composeSmall: ComposeView? = view.findViewById(R.id.compose_view_small)
        composeSmall?.let {
            composeSmall.setContent {
                CardNewsSmall()
            }
        }
        // 如果找到组件，你可以进行进一步的操作
        composeBig?.let {
            composeBig.setContent {
                CardNewsBig()
            }
        }
        return view
    }
}
