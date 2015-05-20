/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.DAO;

import edu.uniminuto.persistencia.Cancion;
import edu.uniminuto.persistencia.HibernateUtil;
import edu.uniminuto.persistencia.Recocancion;
import edu.uniminuto.persistencia.Recopilacion;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class RecopilacionDAO {

    private final int maximodeRegistros = 20;

    private String error = "";

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    public RecopilacionDAO() {
    }

    public List<Recopilacion> getRecopilaciones() {
        return getRecopilaciones(0);
    }

    public List<Recopilacion> getRecopilaciones(int registroInicial) {
        List<Recopilacion> recopilaciones = new ArrayList<>();

        Session session = getSession();

        try {

            session.beginTransaction();
            session.setCacheMode(CacheMode.REFRESH);
            recopilaciones = session.createCriteria(Recopilacion.class).add(Restrictions.eq("publica", 1)).list();
            session.flush();
        } catch (Exception ex) {
            error = ex.getMessage();
        }

//        try {
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            
//            session.beginTransaction();
//            
//            Query query = session.createQuery("SELECT r FROM Recopilacion r");
////            query.setFirstResult(registroInicial);
////            query.setMaxResults(registroInicial + maximodeRegistros);
//            
//            recopilaciones = query.list();
//            session.flush();
//            session.close();
//        } catch (Exception ex) {
//            error = ex.getMessage();
//        }            
        return recopilaciones;
    }

    public Recopilacion getRecopilacion(int id) {
        Recopilacion recopilacion = new Recopilacion();
        try {
            Session session = getSession();
            session.beginTransaction();

            recopilacion = (Recopilacion) session.createCriteria(Recopilacion.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return recopilacion;
    }

    public boolean isValid(Recopilacion recopilacion) {
        boolean valido = true;

        if (recopilacion.getNombre() == null || recopilacion.getNombre().isEmpty()) {
            error = "El nombre no puede estar vacío";
            valido = false;
        }
        return valido;
    }

    public boolean agregarCanciones(Recopilacion recopilacion, long[] ids) {
        Session session = getSession();
        session.beginTransaction();

        String str = String.format("INSERT INTO Recocancion (recopilacion, cancion) SELECT %d, id FROM cancion WHERE id IN (", recopilacion.getId());

        for (int i = 0; i < ids.length; i++) {
            str  = str + ids[i]  +", ";
            
        }
        str = str.substring(0, str.length() - 2)  + ");";
        
        Query query = session.createQuery(str);
        query.executeUpdate();
        session.flush();
        return true;

//        List<Recocancion> rcs = query.list();
//
//        for (Recocancion rc : rcs) {
//            recos.add(rc.getRecopilacion());
//        }
//        rlast.getRecocancions().add(new Recocancion(rdao.getCancion(rcs.getId()), r));

    }

    public boolean guardar(Recopilacion recopilacion) {
        Session session = getSession();

        try {
            session.getTransaction().begin();
            session.saveOrUpdate(recopilacion);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            error = ex.getMessage();
            return false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return true;
    }

    public boolean guardar(Recocancion recocancion) {
        Session session = getSession();

        try {
            session.getTransaction().begin();
            session.saveOrUpdate(recocancion);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            error = ex.getMessage();
            return false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return true;
    }

    public boolean borrar(Recopilacion recopilacion) {

        Session session = getSession();

        try {

            session.getTransaction().begin();
            session.delete(recopilacion);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            error = ex.getMessage();
            return false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return true;
    }
    

    public boolean borrarRecocancion(Recocancion recocancion) {

        Session session = getSession();

        try {

            session.getTransaction().begin();
            session.delete(recocancion);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            error = ex.getMessage();
            return false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return true;
    }
    

    public boolean borrar(Recocancion recocancion) {

        Session session = getSession();

        try {

            session.getTransaction().begin();
            session.delete(recocancion);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            error = ex.getMessage();
            return false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return true;
    }

    public Cancion getCancion(long id) {
        Cancion cancion = new Cancion();
        try {
            Session session = getSession();
            session.beginTransaction();

            cancion = (Cancion) session.createCriteria(Cancion.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return cancion;
    }

    public Recocancion getRecocancion(long cancion, int recopilacion) {
        Recocancion recocancion = new Recocancion();
        try {
            Session session = getSession();
            session.beginTransaction();

            recocancion = (Recocancion) session.createCriteria(Recocancion.class).add(
                    Restrictions.eq("cancion.id", cancion)).
                    add(Restrictions.eq("recopilacion.id", recopilacion)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return recocancion;
    }

    public Recocancion getRecocancion(int id) {
        Recocancion recocancion = new Recocancion();
        try {
            Session session = getSession();
            session.beginTransaction();

            recocancion = (Recocancion) session.createCriteria(Recocancion.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return recocancion;
    }

    private Session getSession() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return session;
    }
}
