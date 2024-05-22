package com.stop.loveam.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.stop.loveam.R
import com.stop.loveam.activity.AddNewsActivity
import com.stop.loveam.compose.UPCButtonGroup

class ChatAIFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ai, container, false)
        val composeAI: ComposeView? = view.findViewById(R.id.compose_view_ai)
        // 如果找到组件，你可以进行进一步的操作
        composeAI?.let {
            composeAI.setContent {
                UPCButtonGroup()
            }
        }

        val addNews: ImageView? = view.findViewById(R.id.addNews)
        addNews?.setOnClickListener {
            // 创建AlertDialog.Builder实例
            val builder = AlertDialog.Builder(requireContext())
            // 设置标题
            builder.setTitle("选择操作")
            // 设置选项，并添加点击事件
            builder.setItems(R.array.news_options) { dialog, which ->
                // 根据用户的选择进行操作
                when (which) {
                    0 -> {
                        //跳转
                        val intent = Intent(requireContext(), AddNewsActivity::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        // 用户选择了第二个选项
                    }
                }
            }
            builder.create().show()
        }
        return view
    }
}

