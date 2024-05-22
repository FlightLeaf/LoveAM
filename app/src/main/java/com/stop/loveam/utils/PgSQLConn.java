package com.stop.loveam.utils;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PgSQLConn {
    static String url = "jdbc:postgresql://139.196.89.94:5433/db3cc04a6054804552adb6870d9d428ddcmem_520am"; // 数据库连接url
    static String username = "root_am"; // 数据库用户名
    static String password = "Lvbaochun1228"; // 用户密码
    static Connection conn = null; // 初始化一个数据连接 conn
    static ResultSet rs = null; // 初始化一个结果集 rs
    static PreparedStatement ps =null; // 初始化一个PreparedStatement对象 ps
    public static void init(){
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,username,password);
            System.out.println("init [SQL驱动程序初始化成功！]");
        } catch (Exception e) {
            System.out.println("init [SQL驱动程序初始化失败！]");
            Log.d("init [SQL驱动程序初始化失败！]", e.toString());
        }
    }
    public static int addUpdDel(String sql){
        int i = 0;
        try {
            PreparedStatement ps =  conn.prepareStatement(sql);
            i =  ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            Log.d("sql数据库增删改异常", e.toString());
        }

        return i;
    }
    public static ResultSet selectSql(String sql){
        try {
            ps =  conn.prepareStatement(sql);
            rs =  ps.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("sql数据库查询异常");
            Log.d("sql数据库查询异常", e.toString());
        }
        return rs;
    }
    public static void closeConn(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql数据库关闭异常");
            Log.d("sql数据库关闭异常", e.toString());
        }
    }
}
