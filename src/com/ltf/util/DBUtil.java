package com.ltf.util;
import java.sql.*;

public class DBUtil {
    public static Connection getMysqlConn(){
        Connection conn = null;

        try {
            //引入
            Class.forName("com.mysql.jdbc.Driver");
            //提供连接信息
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/lt?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
            String dbUserName = "root";
            String dbPassword = "123456";
            //连接
            conn=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        }catch (ClassNotFoundException e){
            System.out.print("类找不到");
        } catch (SQLException e) {
            System.out.print("连接失败!");
        }
        return conn;
    }
}
