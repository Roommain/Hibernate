package com.ltf.servlet;
import com.ltf.dao.Reg;
import com.ltf.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("过来了");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");

        //1.获取输入的数据
        String tpl = req.getParameter("tpl");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password");
        String email = req.getParameter("email");

        //1.创建核心文件对象
//        org.hibernate.cfg.Configuration configure = new org.hibernate.cfg.Configuration().configure();
//        //2.会话工厂
//        SessionFactory factory = configure.buildSessionFactory();
//        Session session = factory.openSession();

        Session session = HibernateUtil.getSession();

        Transaction tr = session.beginTransaction();
        //插入一条数据
        Reg reg = new Reg();
        reg.setTpl(tpl);
        reg.setNickname(nickname);
        reg.setPassword(password);
        reg.setEmail(email);
        session.save(reg);

        tr.commit();
        session.close();
//        factory.close();

        //3. 返回到成功页面
        //否则才更新并跳转到login.jsp
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
