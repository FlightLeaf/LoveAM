package com.stop.loveam.sqliteUtils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.stop.loveam.entity.User;

public class UserDbEngine extends SQLiteOpenHelper {
    private static UserDbEngine sHelper;
    private static final String DB_NAME = "user.sqlite";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public UserDbEngine(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static UserDbEngine getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new UserDbEngine(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user表
        db.execSQL("""
            create table user (
                email      varchar(100) not null,
                name       varchar(255) not null,
                password   varchar(255) not null,
                image      text         not null,
                label      text,
                created_at text
            );
                """);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(User user) {
        deleteUser();
        SQLiteDatabase db = getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("email", user.getEmail());
            values.put("name", user.getName());
            values.put("password", "null");
            values.put("image", user.getImage());
            values.put("label", user.getLabel());
            values.put("created_at", user.getCreatedAt());
            db.insert("user", null, values);
            db.close();
        }
    }

    public User getUser() {
        SQLiteDatabase db = getReadableDatabase();
        User user = null;
        if (db.isOpen()) {
            String sql = "select * from user";
            @SuppressLint("Recycle")
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
                @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex("image"));
                @SuppressLint("Range") String label = cursor.getString(cursor.getColumnIndex("label"));
                @SuppressLint("Range") String createdAt = cursor.getString(cursor.getColumnIndex("created_at"));
                user = new User(email, name, password, image, label, createdAt);
            }
            db.close();
        }
        return user;
    }

    public void deleteUser() {
        SQLiteDatabase db = getWritableDatabase();
        if (db.isOpen()) {
            db.delete("user", null, null);
            db.close();
        }
    }
}