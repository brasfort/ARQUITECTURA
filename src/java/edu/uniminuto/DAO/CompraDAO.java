/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.uniminuto.DAO;

import edu.uniminuto.persistencia.Compra;
import edu.uniminuto.persistencia.Compracancion;
import edu.uniminuto.persistencia.Compradisco;
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

public class CompraDAO {
    
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
    
    public CompraDAO() {
    }
    
    public List<Compra> getCompras(long id) {
        List<Compra> compras = new ArrayList<>();
        
       
        
        try {
            Session session = getSession();
            
            session.beginTransaction();
            
            
            Query query = session.createQuery("FROM Compra WHERE comprador = " + id);
            compras = query.list();
            
//            compras = session.createCriteria(Compra.class).add(
//                    Restrictions.eq("persona.id", id)).list();

            session.flush();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        
        
        
        return compras;
    }
    
    public Compra getCompra(int id) {
        Compra compra = new Compra();
        try {
            
            Session session = getSession();
            session.beginTransaction();
            
            compra = (Compra)
                    session.createCriteria(Compra.class).add(
                    Restrictions.eq("id", id)).uniqueResult();
            session.flush();
//            session.close();
        } catch (Exception ex) {
            setError(ex.getMessage());
        }
        return compra;
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
    
    
    public boolean guardar(Compra compra) {
        Session session = getSession();
        
        try {  
            
            session.getTransaction().begin();
            session.saveOrUpdate(compra);
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
    
    public boolean hacerCompra(Pedido pedido) {
        
        long total = 0;
        String comentarios = "";
        for (Pedidodisco pedidodisco : pedido.getPedidodiscos()) {
            if (pedidodisco.getEnviado() == 1
                    && pedidodisco.getRechazado() == 0) {
                total = total + pedidodisco.getDiscopropietario().getPrecio();
            }
            
            if (pedidodisco.getRechazado() == 1) {
                comentarios = comentarios + "<br />El disco " + pedidodisco.getDiscopropietario().getDisco().getNombre() + " fue rechazado. <br /> " + pedidodisco.getComentario();
            }
        }

        for (Pedidocancion pedidocancion : pedido.getPedidocancions()) {
            total = total + pedidocancion.getCancion().getPrecio();
        }
        
        
        Compra compra = new Compra(pedido.getPersona(), pedido.getFechapedido(), pedido.getTotal(), comentarios, null, null);
            
        if (!guardar(compra)) {
            return false;
        }
        
        


        for (Pedidocancion pc : pedido.getPedidocancions()) {
            guardar(new Compracancion(pc.getCancion(), compra, pc.getCancion().getPrecio()));
            
            borrar(pc);
        }

        for (Pedidodisco pd : pedido.getPedidodiscos()) {
            if (pd.getEnviado() == 1 && pd.getRechazado() == 0) {
                guardar(new Compradisco(compra, pd.getDiscopropietario().getDisco(), 
                        pd.getDiscopropietario().getPersona(), 
                        pd.getDiscopropietario().getPrecio(), 5));
            }

            borrar(pd.getDiscopropietario());
        }
        
        
        if (!borrar(pedido) ) {
            pedido.setDescartado(1);
            guardar(pedido);
        }
            
        return true;
    }

    
    
    
    
    public boolean guardar(Compracancion compraCancion) {
        Session session = getSession();
        
        try {  
            session.getTransaction().begin();
            session.saveOrUpdate(compraCancion);
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
    
    public boolean guardar(Compradisco compraDisco) {
        Session session = getSession();
        
        try {  
            session.getTransaction().begin();
            session.saveOrUpdate(compraDisco);
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
    
    
    
    
    public boolean borrar(Pedidodisco pedidoDisco) {
        
        Session session = getSession();
        
        try {
            
            session.getTransaction().begin();
            session.delete(pedidoDisco);
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
    
    public boolean borrar(Pedidocancion pedidoCancion) {
        
        Session session = getSession();
        
        try {    
            session.getTransaction().begin();
            session.delete(pedidoCancion);
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
    
    public boolean borrar(Pedido pedido) {
        
        Session session = getSession();
        
        try {    
            
            
            session.getTransaction().begin();
            session.delete(pedido);
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
    
    public boolean borrar(Discopropietario discoPropietario) {
        
        Session session = getSession();
        
        try {
            
            
            session.getTransaction().begin();
            session.delete(discoPropietario);
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
