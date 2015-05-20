/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.DAO;

import edu.uniminuto.persistencia.Cancion;
import edu.uniminuto.persistencia.Disco;
import edu.uniminuto.persistencia.Discocancion;
import edu.uniminuto.persistencia.HibernateUtil;
import edu.uniminuto.persistencia.Interprete;
import edu.uniminuto.persistencia.Recocancion;
import edu.uniminuto.persistencia.Recopilacion;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CancionDAO {

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

    public CancionDAO() {
    }

    public List<Cancion> getCanciones() {
        return getCanciones(0);
    }

    public List<Cancion> getCanciones(int registroInicial) {
        List<Cancion> canciones = new ArrayList<>();
        try {
            Session session = getSession();
            session.beginTransaction();

            Query query = session.createQuery("SELECT c FROM Cancion c");
            query.setFirstResult(registroInicial);
            query.setMaxResults(registroInicial + maximodeRegistros);
//            interpretes = 
//                    session.createCriteria(Interprete.class).add(Restrictions.).list();

            canciones = query.list();

            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return canciones;
    }

    public List<Interprete> getInterpretes() {
        List<Interprete> interpretes = new ArrayList<>();
        try {
            Session session = getSession();
            session.beginTransaction();

            interpretes = 
                    session.createCriteria(Interprete.class).list();

            session.flush();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return interpretes;
    }

    public List<Cancion> getCanciones(String busqueda) {
        List<Cancion> canciones = new ArrayList<>();

        if (busqueda.length() > 3) {
            try {
                Session session = getSession();
                session.beginTransaction();

                Query query = session.createQuery("SELECT c FROM Cancion c WHERE nombre LIKE '%" + busqueda + "%'");
                canciones = query.list();

                session.flush();
//            session.close();
            } catch (Exception ex) {
                error = ex.getMessage();
            }
        }
        return canciones;
    }

    public List<Disco> getListadeDiscos(long userId) {
        List<Disco> discos = new ArrayList<>();

        try {
            Session session = getSession();
            session.beginTransaction();

            Query query = session.createQuery("SELECT DISTINCT d FROM Disco d, Discopropietario dp WHERE d.id = dp.disco.id AND dp.persona.id = "+ userId);
            discos = query.list();

            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return discos;
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

    public Interprete getInterprete(int id) {
        Interprete interprete = new Interprete();
        try {

            Session session = getSession();
            session.beginTransaction();

            interprete = (Interprete) session.createCriteria(Interprete.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return interprete;
    }

    public Disco getDisco(int id) {
        Disco disco = new Disco();
        try {

            Session session = getSession();
            session.beginTransaction();

            disco = (Disco) session.createCriteria(Disco.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return disco;
    }

    public boolean isValid(Cancion cancion) {
        boolean valido = true;

        if (cancion.getNombre() == null || cancion.getNombre().isEmpty()) {
            error = "El nombre no puede estar vacío";
            valido = false;
        }
        return valido;
    }

    public boolean guardar(Cancion cancion) {
        Session session = getSession();

        try {
            session.getTransaction().begin();
            session.saveOrUpdate(cancion);
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

    public boolean guardar(Discocancion discocancion) {
        Session session = getSession();

        try {
            session.getTransaction().begin();
            session.saveOrUpdate(discocancion);
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

    public boolean borrar(Cancion cancion) {

        Session session = getSession();

        try {

            session.getTransaction().begin();
            session.delete(cancion);
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

    public List<Recopilacion> getRecopilacionesAgregadas(long personaId, Cancion cancion) {

        List<Recopilacion> recos = new ArrayList<>();
        try {

            Session session = getSession();
            session.beginTransaction();

            Query query = session.createQuery("SELECT DISTINCT rc FROM Recopilacion r, "
                    + "Recocancion rc WHERE r.persona.id = :id AND rc.cancion.id = :cancionid ");
            query.setLong("id", personaId);
            query.setLong("cancionid", cancion.getId());

            List<Recocancion> rcs = query.list();

            for (Recocancion rc : rcs) {
                recos.add(rc.getRecopilacion());
            }

//            recos = query.list();
//            recos = 
//                    session.createCriteria(Recopilacion.class).add(
//                    Restrictions.eq("persona.id", personaId)).add(Restrictions.ne("", recos)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return recos;
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
