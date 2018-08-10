package com.ltf.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Configuration configuration = new Configuration().configure();
    private static SessionFactory factory = configuration.buildSessionFactory();
    public static Session getSession(){
        return factory.openSession();
    }
}
