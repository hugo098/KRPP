
package com.krpp.controlador.repository;


import com.krpp.modelo.entidades.Estacionamiento;
import com.krpp.modelo.entidades.Vehiculo;
import com.krpp.controlador.util.KrppHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EstacionamientoRepository {

    protected final SessionFactory sessionFactory = KrppHibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(EstacionamientoRepository.class);
    private static EstacionamientoRepository instance;

    public static EstacionamientoRepository getInstance() {
        if (instance == null) {
            instance = new EstacionamientoRepository();
        }
        return instance;
    }

    //obtiene los vehiculos de la BD
    public List<Estacionamiento> getAll() {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            List<Estacionamiento> estacionamientos = s.createNamedQuery("Estacionamiento.findByEstado")
                    .getResultList();

            return estacionamientos;
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

    public boolean save(Estacionamiento estacionamiento) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //guardamos la entidad
            s.persist(estacionamiento);
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

    public boolean update(Estacionamiento estacionamiento) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //actualizamos la entidad
            Estacionamiento original = s.find(Estacionamiento.class, estacionamiento.getIdEstacionamiento());
            original.setEstado("FINALIZADO");

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

    public Estacionamiento getById(Integer estacionamientoId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //obtenemos la entidad
            Estacionamiento estacionamiento = s.find(Estacionamiento.class, estacionamientoId);

            return estacionamiento;

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

    public boolean deleteById(Integer estacionamientoId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //eliminamos la entidad
            Estacionamiento estacionamiento = s.find(Estacionamiento.class, estacionamientoId);
            s.delete(estacionamiento);
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

