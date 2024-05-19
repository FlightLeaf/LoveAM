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

/**
 * RecoFragment类是一个Fragment，用于展示推荐内容。
 */
class RecoFragment : Fragment() {

    /**
     * onCreate函数在Fragment创建时被调用，用于执行Fragment的初始化逻辑。
     *
     * @param savedInstanceState 如果Fragment在重建（如横竖屏切换），则此处包含之前保存的状态信息。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 这里可以添加Fragment的初始化代码，例如数据的加载等。
    }

    /**
     * onCreateView函数用于创建Fragment的视图。
     *
     * @param inflater           用于加载布局文件的LayoutInflater。
     * @param container          承载Fragment的ViewGroup，如果Fragment不提供UI，则可以为null。
     * @param savedInstanceState 如果Fragment在重建（如横竖屏切换），则此处包含之前保存的状态信息。
     * @return 返回Fragment的根视图。
     */
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reco, container, false)

        // 在这里查找具有特定ID的组件
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
