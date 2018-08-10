package com.ltf.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltf.dao.Reg;
import com.ltf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //1. 获取用户名和密码
        String tpl=req.getParameter("tpl");
        String password=req.getParameter("password");
        //2. 把该用户名和密码拿到数据库中进行查找
//        Configuration conf=new Configuration().configure();
//        SessionFactory factory=conf.buildSessionFactory();
//        Session session=factory.openSession();

        Session session = HibernateUtil.getSession();

        Transaction tr=session.beginTransaction();
        Query query=session.createQuery("from Reg where tpl=? and password=?");
        query.setString(0, tpl);
        query.setString(1, password);

        List<Reg> list=query.list();
        tr.commit();
        session.close();
        if(list.size()>0){
            session = HibernateUtil.getSession();
            tr=session.beginTransaction();
            query = session.createQuery("from Reg");
            list = query.list();
            tr.commit();
            session.close();
            req.setAttribute("reglist",list);
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }else{
            System.out.println("没找到!");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }

        //3.
        //2-1. 找到了 说明输入正确,跳转到 主页面main.jsp

        //2-2. 找不到 说明输入错误,跳转回login.jsp 进行重新登录
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}

