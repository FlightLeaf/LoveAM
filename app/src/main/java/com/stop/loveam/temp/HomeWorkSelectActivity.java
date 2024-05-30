package com.stop.loveam.temp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stop.loveam.R;

import java.util.ArrayList;
import java.util.List;

public class HomeWorkSelectActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Class_Adapter mClassAdapter;
    List<SelectClass> classList = new ArrayList<>();
    String account = "未选择";

    private static final int REQUEST_CODE_TIME = 1;

    private static final int REQUEST_CODE_CLASS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_work_select);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        classList.add(new SelectClass("课程名称", "学分", "学时"));

        mRecyclerView = findViewById(R.id.classView);
        mClassAdapter = new Class_Adapter();
        mRecyclerView.setAdapter(mClassAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeWorkSelectActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);

        Button buttonTime = findViewById(R.id.buttonTime);
        buttonTime.setOnClickListener(v -> {
            Intent intent = new Intent(HomeWorkSelectActivity.this, HomeWorkSelectTimeActivity.class);
            startActivityForResult(intent, REQUEST_CODE_TIME);
        });

        Button buttonClass = findViewById(R.id.buttonClass);
        buttonClass.setOnClickListener(v -> {
            if(account.equals("未选择")){
                Toast.makeText(HomeWorkSelectActivity.this, "请先选择时间", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(HomeWorkSelectActivity.this, HomeWorkSelectClassActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CLASS);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TIME && resultCode == RESULT_OK) {
            account = data.getStringExtra("time");
            TextView date = findViewById(R.id.textView_date);
            date.setText(account);
        }else if (requestCode == REQUEST_CODE_CLASS && resultCode == RESULT_OK){

            ArrayList<String> selectClassList = data.getStringArrayListExtra("json");
            assert selectClassList != null;
            int credit = 0;
            int hour = 0;
            for (String jsonString : selectClassList) {
                ClassInfo jsonClass = ClassInfo.fromJson(jsonString);
                credit = credit + Integer.parseInt(jsonClass.getClass_credit_select());
                hour = hour + Integer.parseInt(jsonClass.getClass_hour_select());
                classList.add(new SelectClass(jsonClass.getClass_name_select(), jsonClass.getClass_credit_select(), jsonClass.getClass_hour_select()));
            }
            mRecyclerView = findViewById(R.id.classView);
            mClassAdapter = new Class_Adapter();
            mRecyclerView.setAdapter(mClassAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(HomeWorkSelectActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);

            TextView textView = findViewById(R.id.textView_res);
            textView.setText("您一共选了"+(classList.size()-1)+"门课程，\n学分为"+credit+"，学时为"+hour+"。");
        }
    }

    class Class_Adapter extends RecyclerView.Adapter<ClassViewHolder> {

        @NonNull
        @Override
        public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(HomeWorkSelectActivity.this, R.layout.item_list_class, null);
            return new ClassViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
            SelectClass news = classList.get(position);
            holder.class_name.setText(news.getClass_name());
            holder.class_hour.setText(news.getClass_hour());
            holder.class_credit.setText(news.getClass_credit());
        }

        @Override
        public int getItemCount() {
            return classList.size();
        }
    }

    static class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView class_name;
        TextView class_credit;
        TextView class_hour;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            class_name = itemView.findViewById(R.id.class_name);
            class_credit = itemView.findViewById(R.id.class_time);
            class_hour = itemView.findViewById(R.id.class_hour);
        }
    }

}