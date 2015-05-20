/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package edu.uniminuto.DAO;

import edu.uniminuto.persistencia.Discopropietario;
import edu.uniminuto.persistencia.HibernateUtil;
import edu.uniminuto.persistencia.Pedido;
import edu.uniminuto.persistencia.Pedidocancion;
import edu.uniminuto.persistencia.Pedidodisco;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PedidoDAO {
    
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
    
    public PedidoDAO() {
    }
    
    public List<Pedido> getPedidos(long id) {
        List<Pedido> pedidos = new ArrayList<>();
        
       
        
        try {
            Session session = getSession();
            
            session.beginTransaction();
            
            
            Query query = session.createQuery("FROM Pedido WHERE descartado = 0 AND comprador = " + id);
            
//            pedidos = session.createCriteria(Pedido.class).add(
//                    Restrictions.eq("persona.id", id)).list();
            pedidos = query.list();
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        
        
        
        return pedidos;
    }
    
    public List<Pedidodisco> getSolicitudes(long id) {
        List<Pedidodisco> pedidoDiscos = new ArrayList<>();
        
       
        
        try {
              Session session = getSession();
            
            session.beginTransaction();
            
            
            Query query = session.createQuery("SELECT pd FROM Pedidodisco pd, Discopropietario dp WHERE dp.vendido = 0 AND pd.discopropietario.id = dp.id AND enviado = 0 AND rechazado = 0 AND dp.persona.id = " + id);
            
//            pedidos = session.createCriteria(Pedido.class).add(
//                    Restrictions.eq("persona.id", id)).list();
            pedidoDiscos = query.list();
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        
        
        
        return pedidoDiscos;
    }
    
    public List<Pedidodisco> getSolicitudes(long id, int discoId) {
        List<Pedidodisco> pedidoDiscos = new ArrayList<>();
        
       
        
        try {
              Session session = getSession();
            
            session.beginTransaction();
            
            
            Query query = session.createQuery("SELECT pd FROM Pedidodisco pd, Discopropietario dp WHERE pd.discopropietario.id = dp.id AND "
                    + "enviado = 0 AND rechazado = 0 AND dp.persona.id = " + id + " AND dp.disco.id = " + discoId);
            
//            pedidos = session.createCriteria(Pedido.class).add(
//                    Restrictions.eq("persona.id", id)).list();
            pedidoDiscos = query.list();
            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        
        
        
        return pedidoDiscos;
    }
    
    public Pedido getPedido(int id) {
        Pedido pedido = new Pedido();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            pedido = (Pedido)
                    session.createCriteria(Pedido.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            setError(ex.getMessage());
        }
        return pedido;
    }
    
    
    public boolean guardar(Pedido pedido) {
        Session session = getSession();
        
        try {  
            
            session.getTransaction().begin();
            session.saveOrUpdate(pedido);
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
    public boolean guardar(Discopropietario dpropietario) {
        Session session = getSession();
        
        try {  
            
            session.getTransaction().begin();
            session.saveOrUpdate(dpropietario);
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
    
    
    public boolean guardar(Pedidocancion pedidocancion) {
        Session session = getSession();
        
        try {
            session.getTransaction().begin();
            session.saveOrUpdate(pedidocancion);
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
    
    
    
    
    public boolean guardar(Pedidodisco pedidodisco) {
        Session session = getSession();
        
        try {
            
            session.getTransaction().begin();
            session.saveOrUpdate(pedidodisco);
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
    
    
    
    public Discopropietario getDiscopropietario(int id) {
        Discopropietario discoPropietario = new Discopropietario();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            discoPropietario = (Discopropietario)
                    session.createCriteria(Discopropietario.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            setError(ex.getMessage());
        }
        return discoPropietario;
    }
    
    public Pedidodisco getPedidoDisco(int id) {
        Pedidodisco pedidoDisco = new Pedidodisco();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            pedidoDisco = (Pedidodisco)
                    session.createCriteria(Pedidodisco.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            setError(ex.getMessage());
        }
        return pedidoDisco;
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
