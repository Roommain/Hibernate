package com.ltf.servlet;

import com.ltf.dao.Reg;
import com.ltf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Save extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取数据
        String id = req.getParameter("id");
        int id1 = Integer.parseInt(id);
        String tpl= req.getParameter("tpl");
        String nickname= req.getParameter("nickname");
        String password= req.getParameter("password");
        String email= req.getParameter("email");
        //2.处理数据
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

//        Reg reg = new Reg(id1,tpl,nickname,password,email);

        Reg reg=new Reg(id1,
                new String(tpl.getBytes("ISO-8859-1"),"utf-8"),
                new String(nickname.getBytes("ISO-8859-1"),"utf-8"),
                 new String(password.getBytes("ISO-8859-1"),"utf-8"),
                new String(email.getBytes("ISO-8859-1"),"utf-8"));

        session.update(reg);

        tr.commit();
        session.close();
        //3.跳转
        session = HibernateUtil.getSession();
        tr = session.beginTransaction();
        Query query = session.createQuery("from Reg");
        List list = query.list();
        tr.commit();
        session.close();
        req.setAttribute("reglist",list);

        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
