package com.krpp.controlador.repository;

import com.krpp.modelo.entidades.Empleado;
import com.krpp.controlador.util.KrppHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmpleadoRepository {

    protected final SessionFactory sessionFactory = KrppHibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(EmpleadoRepository.class);
    private static EmpleadoRepository instance;
   

    public static EmpleadoRepository getInstance() {
        if (instance == null) {
            instance = new EmpleadoRepository();
        }
        return instance;
    }

    //obtiene los empleados de la BD
    public List<Empleado> getAll() {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            List<Empleado> empleados = s.createNamedQuery("Empleado.getAll")
                    .getResultList();

            return empleados;
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

    public boolean save(Empleado empleado) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //guardamos la entidad
            s.persist(empleado);
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

    public boolean update(Empleado empleado) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //actualizamos la entidad
            Empleado original = s.find(Empleado.class, empleado.getIdEmpleado());
            original.setEstadoCivil(empleado.getEstadoCivil());
            original.setFechaNacimiento(empleado.getFechaNacimiento());
            original.setNombreCompleto(empleado.getNombreCompleto());
            original.setNroCedula(empleado.getNroCedula());
            original.setSexo(empleado.getSexo());

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

    public Empleado getById(Integer empleadoId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //obtenemos la entidad
            Empleado empleado = s.find(Empleado.class, empleadoId);

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

    public boolean deleteById(Integer empleadoId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //eliminamos la entidad
            Empleado empleado = s.find(Empleado.class, empleadoId);
           
             s.delete(empleado);
            
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
