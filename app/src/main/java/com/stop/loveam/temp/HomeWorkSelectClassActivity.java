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

public class HomeWorkSelectClassActivity extends AppCompatActivity {

    Bundle bundle;
    TextView tv_time;
    RecyclerView classRecyclerView;
    Class_Adapter classAdapter;
    List<ClassInfo> classList = new ArrayList<>();
    String account = "未选择";

    Intent resultIntent = new Intent();

    ArrayList<String> selectClassList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_work_select_class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        classList.add(new ClassInfo("CST202403", "移动互联网实践", "必修","2","32"));
        classList.add(new ClassInfo("OSI202404", "信息论与编码", "必修","3","48"));
        classList.add(new ClassInfo("OSI202405", "数字信号处理", "必修","3","48"));
        classList.add(new ClassInfo("OSI202408", "移动通信", "限选","2.5","40"));
        classList.add(new ClassInfo("OSI202421", "人工智能", "限选","3","48"));
        classList.add(new ClassInfo("OSI202423", "微波技术与天线", "限选","2.5","40"));
        classList.add(new ClassInfo("OSI202401", "嵌入式系统应用", "限选","2","32"));
        classList.add(new ClassInfo("OSI202499", "现代通信网", "必修","2","32"));

        classRecyclerView = findViewById(R.id.class_list_recycler);
        classAdapter = new Class_Adapter();
        classRecyclerView.setAdapter(classAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeWorkSelectClassActivity.this);
        classRecyclerView.setLayoutManager(layoutManager);

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

        Button ok = findViewById(R.id.button_ok);
        ok.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("json", selectClassList);
            setResult(RESULT_OK, intent);
            finish();
        });

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> {
            finish();
        });

    }


    class Class_Adapter extends RecyclerView.Adapter<ClassViewHolder> {

        @NonNull
        @Override
        public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(HomeWorkSelectClassActivity.this, R.layout.select_class_item_list, null);
            return new ClassViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
            ClassInfo classInfo = classList.get(position);
            holder.class_id_select.setText(classInfo.getClass_id_select());
            holder.class_name_select.setText(classInfo.getClass_name_select());
            holder.class_type_select.setText(classInfo.getClass_type_select());
            holder.class_hour_select.setText(classInfo.getClass_hour_select());
            holder.class_credit_select.setText(classInfo.getClass_credit_select());
            //监听class_id_select
            holder.class_id_select.setOnClickListener(v -> {
                selectClassList.add(classInfo.toJson());
            });
        }

        @Override
        public int getItemCount() {
            return classList.size();
        }
    }

    static class ClassViewHolder extends RecyclerView.ViewHolder {

        RadioButton class_id_select;
        TextView class_name_select;
        TextView class_type_select;
        TextView class_credit_select;
        TextView class_hour_select;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            class_id_select = itemView.findViewById(R.id.class_id_select);
            class_name_select = itemView.findViewById(R.id.class_name_select);
            class_type_select = itemView.findViewById(R.id.class_type_select);
            class_credit_select = itemView.findViewById(R.id.class_credit_select);
            class_hour_select = itemView.findViewById(R.id.class_hour_select);
        }
    }
}