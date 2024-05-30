package com.stop.loveam.temp;

import android.annotation.SuppressLint;
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

public class HomeWorkSQLResActivity extends AppCompatActivity {

    List<SQLTemp> sqlTemps = new ArrayList<>();

    RecyclerView recyclerView;

    int all = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_sqlres);
        sqlTemps.add(new SQLTemp("语文", "100", "10"));

        try{
            SQLiteHelper sqliteHelper = new SQLiteHelper(this);
            SQLiteDatabase db = sqliteHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from classes", null);
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String class_name = cursor.getString(cursor.getColumnIndex("class_name"));
                @SuppressLint("Range")
                String class_money = cursor.getString(cursor.getColumnIndex("class_money"));
                @SuppressLint("Range")
                String class_num = cursor.getString(cursor.getColumnIndex("class_num"));
                SQLTemp sqlTemp = new SQLTemp(class_name, class_money, class_num);
                sqlTemps.add(sqlTemp);
            }
        }catch (Exception e){
            Log.d("HomeWorkSQLResActivity", e.getMessage());
        }

        recyclerView = findViewById(R.id.recyclerViewRes);
        SQLTempResAdapter adapter = new SQLTempResAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeWorkSQLResActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    class SQLTempResAdapter extends RecyclerView.Adapter<SQLTempResAdapter.SQLTempResViewHolder> {

        @NonNull
        @Override
        public SQLTempResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.class_number_item_res, null);
            return new SQLTempResViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SQLTempResViewHolder holder, int position) {
            SQLTemp sqlTemp = sqlTemps.get(position);

            if(position == 0){
                holder.class_name_res.setText("商品");
                holder.class_money_res.setText("价格");
                holder.class_num_res.setText("数量");
                holder.class_num_all.setText("总价");
            }else {
                holder.class_name_res.setText(sqlTemp.getClass_name());
                holder.class_money_res.setText(sqlTemp.getClass_money());
                holder.class_num_res.setText(sqlTemp.getClass_num());
                holder.class_num_all.setText(
                        String.valueOf(Integer.parseInt(sqlTemp.getClass_num()) * Integer.parseInt(sqlTemp.getClass_money())));
                all += Integer.parseInt(sqlTemp.getClass_num()) * Integer.parseInt(sqlTemp.getClass_money());
            }

            runOnUiThread(() -> {
                TextView class_num_all = findViewById(R.id.textViewAllM);
                class_num_all.setText(String.valueOf(all));
            });
        }

        @Override
        public int getItemCount() {
            return sqlTemps.size();
        }

        public static class SQLTempResViewHolder extends RecyclerView.ViewHolder {
            TextView class_name_res;
            TextView class_money_res;
            TextView class_num_res;

            TextView class_num_all;
            /**
             * 构造函数
             * @param itemView 单个新闻项的视图
             */
            public SQLTempResViewHolder(View itemView) {
                super(itemView);
                // 通过id找到新闻项布局中的视图组件
                class_name_res = itemView.findViewById(R.id.class_name_res);
                class_money_res = itemView.findViewById(R.id.class_money_res);
                class_num_res = itemView.findViewById(R.id.class_num_res);
                class_num_all = itemView.findViewById(R.id.class_num_all);
            }
        }
    }
}