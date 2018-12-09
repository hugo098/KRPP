package com.krpp.controlador.repository;

import com.krpp.modelo.entidades.Marca;
import com.krpp.modelo.entidades.Modelo;
import com.krpp.controlador.util.KrppHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ModeloRepository {

    protected final SessionFactory sessionFactory = KrppHibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(ModeloRepository.class);
    private static ModeloRepository instance;

    public static ModeloRepository getInstance() {
        if (instance == null) {
            instance = new ModeloRepository();
        }
        return instance;
    }

    //obtiene los modelos de la BD
    public List<Modelo> getAll() {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //verificamos si existe el usuario dado
            List<Modelo> modelos = s.createNamedQuery("Modelo.getAll")
                    .getResultList();

            return modelos;
        } catch (Throwable t) {
            wasRollback = true;
        } finally {
            try {
                endTransaction(s, wasRollback);
            } catch (Throwable t) {

            }
        }
        return null;
    }

    public boolean save(Modelo modelo) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //guardamos la entidad
            s.persist(modelo);
            return true;
        } catch (Throwable t) {
            wasRollback = true;
        } finally {
            try {
                endTransaction(s, wasRollback);
            } catch (Throwable t) {

            }
        }
        return false;
    }

    public boolean update(Modelo modelo) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //actualizamos la entidad
            Modelo original = s.find(Modelo.class, modelo.getIdModelo());
            original.setMarca(modelo.getMarca());
            original.setNombreModelo(modelo.getNombreModelo());

            s.merge(original);
            return true;
        } catch (Throwable t) {
            wasRollback = true;
        } finally {
            try {
                endTransaction(s, wasRollback);
            } catch (Throwable t) {

            }
        }
        return false;
    }

    public Modelo getById(Integer modeloId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //obtenemos la entidad
            Modelo modelo = s.find(Modelo.class, modeloId);

            return modelo;

        } catch (Throwable t) {
            wasRollback = true;
        } finally {
            try {
                endTransaction(s, wasRollback);
            } catch (Throwable t) {

            }
        }
        return null;
    }

    public boolean deleteById(Integer modeloId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //eliminamos la entidad
            Modelo modelo = s.find(Modelo.class, modeloId);
            s.delete(modelo);
            return true;

        } catch (Throwable t) {
            wasRollback = true;
        } finally {
            try {
                endTransaction(s, wasRollback);
            } catch (Throwable t) {

            }
        }
        return false;
    }


    public Session openSession() {
        return sessionFactory.openSession();
    }

    public Session beginTransaction() {
        try {
            Session s = openSession();
            s.beginTransaction();
            return s;
        } catch (Exception e) {
            throw new HibernateException(e);
        }

    }

    public void endTransaction(Session s, boolean wasRollback) {
        try {
            if (!wasRollback) {
                s.getTransaction().commit();
            } else {
                logger.warn(" endTransaction | se hizo ROLLBACK");
                s.getTransaction().rollback();
            }
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            s.close();
        }
    }
}
