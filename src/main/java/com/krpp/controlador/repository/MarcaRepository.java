package com.krpp.controlador.repository;

import com.krpp.modelo.entidades.Marca;
import com.krpp.controlador.util.KrppHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MarcaRepository {

    protected final SessionFactory sessionFactory = KrppHibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(MarcaRepository.class);
    private static MarcaRepository instance;

    public static MarcaRepository getInstance() {
        if (instance == null) {
            instance = new MarcaRepository();
        }
        return instance;
    }

    //obtiene las marcas de la BD
    public List<Marca> getAll() {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //verificamos si existe el usuario dado
            List<Marca> marcas = s.createNamedQuery("Marca.getAll")
                    .getResultList();

            return marcas;
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

    public boolean save(Marca marca) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //guardamos la entidad
            s.persist(marca);
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

    public boolean update(Marca marca) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //actualizamos la entidad
            Marca original = s.find(Marca.class, marca.getIdMarca());
            original.setNombreMarca(marca.getNombreMarca());

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

    public Marca getById(Integer marcaId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //obtenemos la entidad
            Marca marca = s.find(Marca.class, marcaId);

            return marca;

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

    public boolean deleteById(Integer marcaId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //eliminamos la entidad
            Marca marca = s.find(Marca.class, marcaId);
            s.delete(marca);
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
