package com.krpp.controlador.repository;

import com.krpp.modelo.entidades.Lugar;
import com.krpp.controlador.util.KrppHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LugarRepository {

    protected final SessionFactory sessionFactory = KrppHibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(EmpleadoRepository.class);
    private static LugarRepository instance;

    public static LugarRepository getInstance() {
        if (instance == null) {
            instance = new LugarRepository();
        }
        return instance;
    }

    //obtiene los empleados de la BD
    public List<Lugar> getAll() {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            List<Lugar> lugar = s.createNamedQuery("Lugar.findAll")
                    .getResultList();

            return lugar;
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
    
    public List<Lugar> getAllLibre(){
        Session s = null;
        boolean wasRollback = false;
        try {
            s = beginTransaction();
            List<Lugar> lugar = s.createNamedQuery("Lugar.findByEstado")
                    .getResultList();

            return lugar;
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

    public boolean save(Lugar lugar) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //guardamos la entidad
            s.persist(lugar);
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

    public boolean update(Lugar lugar) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //actualizamos la entidad
            Lugar original = s.find(Lugar.class, lugar.getIdLugar());
            if (original.getEstado().equalsIgnoreCase("LIBRE")){
                original.setEstado("OCUPADO");
            }else{
                original.setEstado("LIBRE");
            }
                    
           
            
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

    public Lugar getById(Integer lugarId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //obtenemos la entidad
            Lugar empleado = s.find(Lugar.class,lugarId);

            return empleado;

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

    public boolean deleteById(Integer lugarId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //eliminamos la entidad
            Lugar lugar = s.find(Lugar.class, lugarId);
            s.delete(lugar);
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

