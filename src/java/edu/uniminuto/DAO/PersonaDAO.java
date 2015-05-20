/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.uniminuto.DAO;

import edu.uniminuto.persistencia.HibernateUtil;
import edu.uniminuto.persistencia.Persona;
import edu.uniminuto.persistencia.Rol;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PersonaDAO {
    
    private String error;

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
    
    public PersonaDAO() {
    }
    
    public Persona getPersona(long id) {
        Persona cancion = new Persona();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            cancion = (Persona)
                    session.createCriteria(Persona.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            setError(ex.getMessage());
        }
        return cancion;
    }
    
    public Rol getRol(int id) {
        Rol rol = new Rol();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            rol = (Rol)
                    session.createCriteria(Rol.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            setError(ex.getMessage());
        }
        return rol;
    }
    
    public Persona getPersona(String correo, String clave) {
        Persona persona = new Persona();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            persona = (Persona)
                    session.createCriteria(Persona.class).add(
                    Restrictions.eq("correo", correo)).add(
                    Restrictions.eq("clave", clave)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            setError(ex.getMessage());
        }
        return persona;
    }
    
    
    public boolean guardar(Persona persona) {
        Session session = getSession();
        boolean ret = true;
        try {    
            session.getTransaction().begin();
            session.merge(persona);
//            session.saveOrUpdate(persona);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            error = ex.getMessage();
            ret = false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return ret;
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
