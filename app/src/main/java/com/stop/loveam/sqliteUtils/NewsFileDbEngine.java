package com.stop.loveam.sqliteUtils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.stop.loveam.entity.News;

import java.util.ArrayList;
import java.util.List;

public class NewsFileDbEngine extends SQLiteOpenHelper {
    private static NewsFileDbEngine sHelper;
    private static final String DB_NAME = "news_file.sqlite";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public NewsFileDbEngine(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static NewsFileDbEngine getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new NewsFileDbEngine(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user表
        //create table news
        //(
        //    id          int8           default nextval('news_id_seq'::regclass) not null
        //        constraint news_pkey
        //            primary key,
        //    title       varchar(255),
        //    description text,
        //    imageurl    varchar(255),
        //    created_at  timestamptz(6) default CURRENT_TIMESTAMP,
        //    email       varchar(100)                                            not null
        //        constraint news_source_fk
        //            references "user",
        //    url         varchar(255)
        //);
        db.execSQL("""
                CREATE TABLE news (
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    title       VARCHAR(255) NOT NULL,
                    description TEXT NOT NULL,
                    imageurl    VARCHAR(255),
                    created_at  VARCHAR(100),
                    url         TEXT
                );
                """);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNews(News news) {
        SQLiteDatabase db = getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("title", news.getTitle());
            values.put("description", news.getDescription());
            values.put("imageurl", news.getImageurl());
            values.put("created_at", news.getCreatedAt());
            values.put("url", news.getUrl());
            db.insert("news", null, values);
            db.close();
        }
    }

    public List<News> getNewsList() {
        SQLiteDatabase db = getReadableDatabase();
        List<News> newsList = new ArrayList<>();
        if (db.isOpen()) {
            String sql = "select * from news";
            @SuppressLint("Recycle")
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                    @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                    @SuppressLint("Range") String imageurl = cursor.getString(cursor.getColumnIndex("imageurl"));
                    @SuppressLint("Range") String createdAt = cursor.getString(cursor.getColumnIndex("created_at"));
                    @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("url"));
                    newsList.add(new News(id, title, description, imageurl, createdAt, url));
                }while (cursor.moveToNext());
                db.close();
            }
        }
        return newsList;
    }

    public void deleteNews(long id){
        SQLiteDatabase db = getWritableDatabase();
        if (db.isOpen()) {
            db.delete("news", "id = ?", new String[]{String.valueOf(id)});
            db.close();
        }
    }
}
