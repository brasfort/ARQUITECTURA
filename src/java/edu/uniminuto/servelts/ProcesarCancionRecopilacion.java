/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.RecopilacionDAO;
import edu.uniminuto.persistencia.Persona;
import edu.uniminuto.persistencia.Recocancion;
import edu.uniminuto.persistencia.Recopilacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "ProcesarCancionRecopilacion", urlPatterns = {"/cancionreco"})
public class ProcesarCancionRecopilacion extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String url = "entrar.jsp";
        if (request.getSession().getAttribute("usuario") != null) {
            
            
            int lista = Integer.valueOf(getParameter(request, "recopilacion"));
            long cancion = Long.valueOf(getParameter(request, "cancion"));
            url = "cancion.jsp?id="+cancion;
            
            String operacion = getParameter(request, "accion");
            
            RecopilacionDAO rdao = new RecopilacionDAO();
            
            if (operacion.equals("agregar")) {
                Recocancion rc = new Recocancion(rdao.getCancion(cancion), rdao.getRecopilacion(lista));
                rdao.guardar(rc);
                
            } else {
                Recocancion rc = rdao.getRecocancion(cancion, lista);
                rdao.borrar(rc);
            }
        }
        
        response.sendRedirect(url);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "entrar.jsp";
        if (request.getSession().getAttribute("usuario") != null) {
            
            
            int lista = Integer.valueOf(getParameter(request, "recopilacion"));
            String nombre = getParameter(request, "nombre");
            
            Persona p = (Persona)request.getSession().getAttribute("usuario");
            
            Recopilacion r = new Recopilacion(p, nombre, new java.util.Date(), 1);
            
            
            RecopilacionDAO rdao = new RecopilacionDAO();
            
            
            
            
            
            if (rdao.guardar(r)) {
                
                
                Recopilacion rlast = rdao.getRecopilacion(lista);
                
                for (Recocancion rcs : rlast.getRecocancions()) {
                    rdao.guardar(new Recocancion(rdao.getCancion(rcs.getCancion().getId()), r));
                }
                
                
//                rdao.agregarCanciones(r, ids);
                
                url = "recopilacion.jsp?id="+r.getId();
            } else {
                url = "recopilaciones.jsp";
            }
            
            
//            url = "recopilacion.jsp?id="+lista;
//            if (operacion.equals("agregar")) {
//                Recocancion rc = new Recocancion(rdao.getCancion(cancion), rdao.getRecopilacion(lista));
//                rdao.guardar(rc);
//                
//            } else {
//                Recocancion rc = rdao.getRecocancion(cancion, lista);
//                rdao.borrar(rc);
//            }
        }
        
        response.sendRedirect(url);
        
    }
    
    private String getAttribute(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getAttribute(parametername) != null) {
            ret = request.getAttribute(parametername).toString();
        }
        return ret;
    }
    
    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
