package com.stop.loveam.temp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stop.loveam.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeWorkSQLTestActivity extends AppCompatActivity {

    List<SQLTemp> sqlTemps = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_sql);

        sqlTemps.add(new SQLTemp("苹果", "10", "0"));
        sqlTemps.add(new SQLTemp("香蕉", "15", "0"));
        sqlTemps.add(new SQLTemp("葡萄", "20", "0"));
        sqlTemps.add(new SQLTemp("西瓜", "25", "0"));
        sqlTemps.add(new SQLTemp("橘子", "30", "0"));
        sqlTemps.add(new SQLTemp("橙子", "35", "0"));

        recyclerView = findViewById(R.id.recyclerViewSQL);

        SQLTempAdapter adapter = new SQLTempAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeWorkSQLTestActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.button).setOnClickListener(v -> {

            Intent intent = new Intent(HomeWorkSQLTestActivity.this, HomeWorkSQLResActivity.class);
            startActivity(intent);

        });

    }

    class SQLTempAdapter extends RecyclerView.Adapter<SQLTempAdapter.SQLTempViewHolder> {

        @NonNull
        @Override
        public SQLTempViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.class_number_item, null);
            return new SQLTempViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SQLTempViewHolder holder, int position) {
            SQLTemp sqlTemp = sqlTemps.get(position);
            holder.class_name.setText(sqlTemp.getClass_name());
            holder.class_money.setText(sqlTemp.getClass_money());
            holder.numberView.setMCurrentValue(Integer.parseInt(sqlTemp.getClass_num()));
            holder.numberView.setOnValueChangeListener(value -> {
                sqlTemp.setClass_num(String.valueOf(value));
                try{
                    SQLiteHelper sqliteHelper = new SQLiteHelper(HomeWorkSQLTestActivity.this);
                    SQLiteDatabase db = sqliteHelper.getReadableDatabase();
                    Cursor cursor = db.query("classes", null, "class_name = ?", new String[]{sqlTemp.getClass_name()}, null, null, null);
                    if (cursor.moveToFirst()) {
                        //存在，更新
                        db.execSQL("update classes set class_num = ? where class_name = ?", new Object[]{sqlTemp.getClass_num(), sqlTemp.getClass_name()});
                    }else {
                        //不存在，插入
                        db.execSQL("insert into classes(class_name, class_money, class_num) values(?, ?, ?)", new Object[]{sqlTemp.getClass_name(), sqlTemp.getClass_money(), sqlTemp.getClass_num()});
                    }

                }catch (Exception e){
                    Log.d("HomeWorkSQLResActivity", Objects.requireNonNull(e.getMessage()));
                }
            });
            Log.d("sqlTemps", String.valueOf(recyclerView.getWidth()));
        }

        @Override
        public int getItemCount() {
            return sqlTemps.size();
        }

        public static class SQLTempViewHolder extends RecyclerView.ViewHolder {
            TextView class_name; // 新闻图片
            TextView class_money; // 新闻标题
            NumberView numberView;
            /**
             * 构造函数
             * @param itemView 单个新闻项的视图
             */
            public SQLTempViewHolder(View itemView) {
                super(itemView);
                // 通过id找到新闻项布局中的视图组件
                class_name = itemView.findViewById(R.id.class_name);
                class_money = itemView.findViewById(R.id.class_money);
                numberView = itemView.findViewById(R.id.class_num);
            }
        }
    }

}