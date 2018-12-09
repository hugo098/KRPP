package com.krpp.controlador.repository;

import com.krpp.modelo.entidades.Usuario;
import com.krpp.controlador.util.KrppHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;

public class UsuarioRepository {

    protected final SessionFactory sessionFactory = KrppHibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(UsuarioRepository.class);
    private static UsuarioRepository instance;

    public static UsuarioRepository getInstance(){
        if(instance == null){
            instance = new UsuarioRepository();
        }
        return instance;
    }

    //consulta si existe un usuario
    public boolean existeUsuario(String usuario, String password) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();
            //verificamos si existe el usuario dado
            Usuario user = (Usuario) s.createNamedQuery("Usuario.getUsuarioByUserAndPass")
                    .setParameter("usuario", usuario)
                    .setParameter("password", password)
                    .getSingleResult();


            return true;
        } catch (NoResultException nre) {
            return false;
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


    //crea un usuario
    public boolean insertUsuario(String usuario, String password) {
        Session s = null;
        boolean wasRollback = false;
        try {

            s = beginTransaction();

            Usuario user = new Usuario();
            user.setUsuario(usuario);
            user.setPassword(password);

            s.persist(user);

            return true;
        } catch (NoResultException nre) {
            return false;
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
            }finally {
                s.close();
            }
        }
}
