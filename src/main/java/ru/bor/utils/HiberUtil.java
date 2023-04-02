package ru.bor.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberUtil {
    private static SessionFactory sessionFactory;

    private static void buildSessionFactory(Configuration config) {
        try {
            sessionFactory = config.buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Initial SessionFactory - failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(Configuration config) {
        if (sessionFactory == null) buildSessionFactory(config);
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }

}