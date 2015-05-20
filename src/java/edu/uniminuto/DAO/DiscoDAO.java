/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.DAO;

import edu.uniminuto.persistencia.Disco;
import edu.uniminuto.persistencia.Discopropietario;
import edu.uniminuto.persistencia.Genero;
import edu.uniminuto.persistencia.HibernateUtil;
import edu.uniminuto.persistencia.Interprete;
import edu.uniminuto.persistencia.Persona;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jhon Gómez
 */
public class DiscoDAO {
    private String error;
    private static final int maximodeRegistros = 20;
    public DiscoDAO() { }
    
    public List<Disco> getDiscos() {
        return getDiscos("", 0);
    }
    
    public List<Disco> getDiscos(String letra) {
        return getDiscos(letra, 0);
    }
    
    public List<Disco> getDiscos(String letra, int registroInicial) {
        
        List<Disco> discos = new ArrayList<>();
        try {
            Session session = getSession();
            session.beginTransaction();
            
            String strQuery = "SELECT d FROM Disco d";
            if (letra != null && !letra.isEmpty()) {
                strQuery = "SELECT d FROM Disco d WHERE nombre LIKE ':nombre'";
            }
            Query query = session.createQuery(strQuery);
            if (letra != null && !letra.isEmpty()) 
                query.setParameter("nombre", letra + "%");
            
            query.setFirstResult(registroInicial);
            query.setMaxResults(registroInicial + maximodeRegistros);
            
            discos = query.list();
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return discos;
    }
    
    public Disco getDisco(int id) {
        
        Disco disco = new Disco();
        try {
            Session session = getSession();
            session.beginTransaction();
            
            disco = (Disco)
                    session.createCriteria(Disco.class).
                            add(Restrictions.eq("id", id)).
                            uniqueResult();
            
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return disco;
    }
    
    public Discopropietario getDiscoPropietario(int id) {
        
        Discopropietario discopropietario = new Discopropietario();
        try {
            Session session = getSession();
            session.beginTransaction();
            
            discopropietario = (Discopropietario)
                    session.createCriteria(Discopropietario.class).
                            add(Restrictions.eq("id", id)).
                            uniqueResult();
            
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return discopropietario;
    }
    
    public Persona getPersona(long id) {
        
        Persona persona = new Persona();
        try {
            Session session = getSession();
            session.beginTransaction();
            
            persona = (Persona)
                    session.createCriteria(Persona.class).
                            add(Restrictions.eq("id", id)).
                            uniqueResult();
            
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return persona;
    }
    
    public Genero getGenero(short id) {
        
        Genero genero = new Genero();
        try {
            Session session = getSession();
            session.beginTransaction();
            
            genero = (Genero)
                    session.createCriteria(Genero.class).
                            add(Restrictions.eq("id", id)).
                            uniqueResult();
            
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return genero;
    }
    
    public Interprete getInterprete(int id) {
        
        Interprete interprete = new Interprete();
        try {
            Session session = getSession();
            session.beginTransaction();
            
            interprete = (Interprete)
                    session.createCriteria(Interprete.class).
                            add(Restrictions.eq("id", id)).
                            uniqueResult();
            
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return interprete;
    }
    
    
    
    public boolean guardar(Disco disco) {
        Session session = getSession();
        
        try {    
            
            
            session.getTransaction().begin();
            session.saveOrUpdate(disco);
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
    
    public boolean borrar(Disco disco) {
        Session session = getSession();
        
        try {
            session.getTransaction().begin();
            session.delete(disco);
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
    
    public boolean borrar(Discopropietario discopropietario) {
        Session session = getSession();
        
        try {
            session.getTransaction().begin();
            session.delete(discopropietario);
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
    
    public boolean guardar(Discopropietario discoPropietario) {
        Session session = getSession();
        
        try {    
            
            session.getTransaction().begin();
            session.saveOrUpdate(discoPropietario);
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
    
    
    
    public List<Genero> getGeneros() {
        
        List<Genero> generos = new ArrayList<>();
        try {
            Session session = getSession();
            
            session.beginTransaction();
            generos = 
                    session.createCriteria(Genero.class).list();
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return generos;
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
            String error = ex.getMessage();
        }
        return interpretes;
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
