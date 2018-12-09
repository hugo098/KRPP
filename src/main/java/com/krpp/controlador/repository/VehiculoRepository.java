package com.krpp.controlador.repository;

import com.krpp.modelo.entidades.Empleado;
import com.krpp.modelo.entidades.Vehiculo;
import com.krpp.controlador.util.KrppHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class VehiculoRepository {

    protected final SessionFactory sessionFactory = KrppHibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(VehiculoRepository.class);
    private static VehiculoRepository instance;

    public static VehiculoRepository getInstance() {
        if (instance == null) {
            instance = new VehiculoRepository();
        }
        return instance;
    }

    //obtiene los vehiculos de la BD
    public List<Vehiculo> getAll() {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            List<Vehiculo> vehiculos = s.createNamedQuery("Vehiculo.getAll")
                    .getResultList();

            return vehiculos;
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
    
    public List<Vehiculo> getByEstado() {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            List<Vehiculo> vehiculos = s.createNamedQuery("Vehiculo.getByEstado")
                    .getResultList();

            return vehiculos;
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

    public boolean save(Vehiculo vehiculo) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //guardamos la entidad
            s.persist(vehiculo);
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

    public boolean update(Vehiculo vehiculo) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //actualizamos la entidad
            Vehiculo original = s.find(Vehiculo.class, vehiculo.getIdVehiculo());
            original.setAnho(vehiculo.getAnho());
            original.setChapa(vehiculo.getChapa());
            original.setColor(vehiculo.getColor());
            original.setEmpleado(vehiculo.getEmpleado());
            original.setModelo(vehiculo.getModelo());

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
    
    public boolean updateEstdo(Vehiculo vehiculo) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //actualizamos la entidad
            Vehiculo original = s.find(Vehiculo.class, vehiculo.getIdVehiculo());
            if (original.getEstado().equalsIgnoreCase("ESTACIONADO")){
                original.setEstado("NO ESTACIONADO");
            }else{
                original.setEstado("ESTACIONADO");
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

    public Vehiculo getById(Integer vehiculoId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //obtenemos la entidad
            Vehiculo vehiculo = s.find(Vehiculo.class, vehiculoId);

            return vehiculo;

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

    public boolean deleteById(Integer vehiculoId) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //eliminamos la entidad
            Vehiculo vehiculo = s.find(Vehiculo.class, vehiculoId);
            s.delete(vehiculo);
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
