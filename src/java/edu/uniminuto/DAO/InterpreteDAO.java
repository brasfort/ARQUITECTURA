/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.DAO;

import edu.uniminuto.persistencia.HibernateUtil;
import edu.uniminuto.persistencia.Interprete;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jhon Gómez
 */
public class InterpreteDAO {
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
    public InterpreteDAO() { }
    
    
    public List<Interprete> getInterpretes() { 
        return getInterpretes(0);
    }
    
    
    public List<Interprete> getInterpretes(int registroInicial) {
        List<Interprete> interpretes = new ArrayList<>();
        try {
            Session session = getSession();
            session.beginTransaction();
            
            Query query = session.createQuery("SELECT i FROM Interprete i");
            query.setFirstResult(registroInicial);
            query.setMaxResults(registroInicial + maximodeRegistros);
//            interpretes = 
//                    session.createCriteria(Interprete.class).add(Restrictions.).list();
            
            interpretes = query.list();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return interpretes;
    }
    
    public Interprete getInterprete(int id) {
        Interprete interprete = new Interprete();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            interprete = (Interprete)
                    session.createCriteria(Interprete.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return interprete;
    }
    
    public boolean isValid(Interprete interprete) {
        boolean valido = true;
        
        if (interprete.getNombre() == null || interprete.getNombre().isEmpty()) {
            error = "El nombre no puede estar vacío";
            valido = false;
        }
        return valido;
    }
    
    public boolean guardar(Interprete interprete) {
        
        
        Session session = getSession();
        try {    
            
            session.getTransaction().begin();
            session.saveOrUpdate(interprete);
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
    
    public boolean borrar(Interprete interprete) {
        
        Session session = getSession();
        
        try {    
            
            
            session.getTransaction().begin();
            session.delete(interprete);
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
    
    
    
    private Session getSession() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return session;
    }
}
