package com.stop.loveam.temp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stop.loveam.R;
import com.stop.loveam.entity.ClassInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择课程的活动界面类
 */
public class HomeWorkSelectClassActivity extends AppCompatActivity {

    // 用于存储意图中传递的数据
    Bundle bundle;
    // 页面顶部时间显示文本
    TextView tv_time;
    // 课程列表的RecyclerView
    RecyclerView classRecyclerView;
    // 课程列表适配器
    Class_Adapter classAdapter;
    // 课程信息列表
    List<ClassInfo> classList = new ArrayList<>();
    // 账户选择状态，默认为未选择
    String account = "未选择";

    // 用于返回结果的Intent
    Intent resultIntent = new Intent();

    // 用户选择的课程列表
    ArrayList<String> selectClassList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置界面为全屏并启用状态栏和导航栏沉浸式体验
        EdgeToEdge.enable(this);
        // 加载布局文件
        setContentView(R.layout.activity_home_work_select_class);
        // 设置状态栏和导航栏的填充
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 初始化课程信息列表
        initClassList();

        // 初始化RecyclerView和LayoutManager
        initRecyclerView();

        // 从意图中获取并设置学期信息
        initTermInfo();

        // 设置确定和返回按钮的点击事件
        setupButtons();
    }

    /**
     * 初始化课程信息列表
     */
    private void initClassList() {
        classList.add(new ClassInfo("CST202403", "移动互联网实践", "必修","2","32"));
        classList.add(new ClassInfo("OSI202404", "信息论与编码", "必修","3","48"));
        classList.add(new ClassInfo("OSI202405", "数字信号处理", "必修","3","48"));
        classList.add(new ClassInfo("OSI202408", "移动通信", "限选","2.5","40"));
        classList.add(new ClassInfo("OSI202421", "人工智能", "限选","3","48"));
        classList.add(new ClassInfo("OSI202423", "微波技术与天线", "限选","2.5","40"));
        classList.add(new ClassInfo("OSI202401", "嵌入式系统应用", "限选","2","32"));
        classList.add(new ClassInfo("OSI202499", "现代通信网", "必修","2","32"));
    }

    /**
     * 初始化RecyclerView和设置LayoutManager
     */
    private void initRecyclerView() {
        classRecyclerView = findViewById(R.id.class_list_recycler);
        classAdapter = new Class_Adapter();
        classRecyclerView.setAdapter(classAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeWorkSelectClassActivity.this);
        classRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 从意图中获取学期信息并设置到页面上
     */
    private void initTermInfo() {
        new Thread(() -> {
            tv_time = findViewById(R.id.class_title);
            bundle = getIntent().getExtras();
            if (bundle != null) {
                String time = bundle.getString("time");
                runOnUiThread(() -> {
                    tv_time.setText("请选择"+time+"学期课程");
                });
            }
        }).start();
    }

    /**
     * 设置确定和返回按钮的点击事件
     */
    private void setupButtons() {
        Button ok = findViewById(R.id.button_ok);
        ok.setOnClickListener(v -> {
            // 设置选择的课程列表并结束当前活动
            Intent intent = new Intent();
            intent.putStringArrayListExtra("json", selectClassList);
            setResult(RESULT_OK, intent);
            finish();
        });

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> {
            // 直接结束当前活动
            finish();
        });
    }


    /**
     * 课程列表适配器类
     */
    class Class_Adapter extends RecyclerView.Adapter<ClassViewHolder> {

        @NonNull
        @Override
        public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 创建并返回一个ViewHolder对象
            View view = View.inflate(HomeWorkSelectClassActivity.this, R.layout.select_class_item_list, null);
            return new ClassViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
            // 绑定数据到ViewHolder的视图上
            ClassInfo classInfo = classList.get(position);
            holder.class_id_select.setText(classInfo.getClass_id_select());
            holder.class_name_select.setText(classInfo.getClass_name_select());
            holder.class_type_select.setText(classInfo.getClass_type_select());
            holder.class_hour_select.setText(classInfo.getClass_hour_select());
            holder.class_credit_select.setText(classInfo.getClass_credit_select());
            // 设置课程ID的点击事件，选择课程
            holder.class_id_select.setOnClickListener(v -> {
                selectClassList.add(classInfo.toJson());
            });
        }

        @Override
        public int getItemCount() {
            // 返回课程列表的大小
            return classList.size();
        }
    }

    /**
     * RecyclerView.ViewHolder类，用于缓存视图组件
     */
    static class ClassViewHolder extends RecyclerView.ViewHolder {

        RadioButton class_id_select;
        TextView class_name_select;
        TextView class_type_select;
        TextView class_credit_select;
        TextView class_hour_select;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            // 初始化视图组件
            class_id_select = itemView.findViewById(R.id.class_id_select);
            class_name_select = itemView.findViewById(R.id.class_name_select);
            class_type_select = itemView.findViewById(R.id.class_type_select);
            class_credit_select = itemView.findViewById(R.id.class_credit_select);
            class_hour_select = itemView.findViewById(R.id.class_hour_select);
        }
    }
}
