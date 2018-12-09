package com.krpp.controlador.util;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

import java.util.Set;

public class KrppHibernateUtil {

    private static final SessionFactory sessionFactory;
    final static Logger logger = Logger.getLogger(KrppHibernateUtil.class);

    static {
        try {

            Configuration configuration = new Configuration();
            configuration.configure("hibernate-krpp.cfg.xml");

            Reflections reflections = new Reflections(
                    "com.krpp.modelo.entidades");

            Set<Class<?>> classes = reflections
                    .getTypesAnnotatedWith(javax.persistence.Entity.class);

            for (Class<?> clazz : classes) {
                configuration.addAnnotatedClass(clazz);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (HibernateException e) {
            logger.error(
                    "Ocurrio un error en la inicializacion de la SessionFactory",
                    e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
