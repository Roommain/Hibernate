package com.ltf.dao;
import com.ltf.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //插入数据
    public void insertUser(UserBean userBean){
        //1.获取连接
        Connection conn = DBUtil.getMysqlConn();
        //2.设置sql语句
        String sql = "INSERT INTO USER VALUES (?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userBean.getUid());
            ps.setString(2,userBean.getUname());
            ps.setString(3,userBean.getUpwd());
        //3.执行ps，执行sql
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.print("插入失败！");
        }
    }


    //删除数据
    public void deleteUserId(String uid){
        Connection conn = DBUtil.getMysqlConn();
        String sql = "delete from user where uid=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.print("删除失败！");
        }
    }

    //修改数据
    public void updateUser(String uName,String uPwd,String uid){
        Connection conn = DBUtil.getMysqlConn();
        String sql = "update user SET uName=?,uPwd=? WHERE uid=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,uName);
            ps.setString(2,uPwd);
            ps.setString(3,uid);
        }catch (SQLException e){
            System.out.print("修改失败！");
        }
    }

        //查询数据
    public List getAllUser(){
        List list = new ArrayList();

        //1.获取连接
        Connection conn = DBUtil.getMysqlConn();
        //2.设置sql语句
        String sql = "select * from user";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        //3.找下一位
            while (rs.next()){
                UserBean user = new UserBean();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setUpwd(rs.getString("upwd"));

                list.add(user);
            }
        }catch (SQLException e){
            System.out.println("查询失败！");
        }
        return list;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        userDao.insertUser("3","ww","123");
//        userDao.insertUser(userDao);


//        userDao.deleteUserId("3");
//        userDao.updateUser("zz","000","2");

        List list=userDao.getAllUser();
        for(int i=0;i<list.size();i++){
            UserBean user= (UserBean) list.get(i);
            System.out.print(user.getUid()+" ");
            System.out.print(user.getUname()+" ");
            System.out.println(user.getUpwd());
        }
    }
}
