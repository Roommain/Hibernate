package com.ltf.servlet;
import com.ltf.dao.Reg;
import com.ltf.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Change extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("text/html;charset=UTF-8");

        //1.获取数据
        String id = req.getParameter("id");

        //2.处理数据
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        Reg reg = session.get(Reg.class,Integer.parseInt(id));

        req.setAttribute("reg",reg);

        tr.commit();
        session.close();
        //3.跳转
        req.getRequestDispatcher("change.jsp").forward(req, resp);
    }
}
