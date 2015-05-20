    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.carro.Elemento;
import edu.uniminuto.carro.ListaElementos;
import edu.uniminuto.persistencia.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "AgregarMp3", urlPatterns = {"/agregarcancion"})
public class AgregarMp3 extends HttpServlet {


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
        
        
        String id = getParameter(request, "id");
        
        
        if (!id.isEmpty()) {
            String nombre = getParameter(request, "nombre");
            int precio = Integer.valueOf(getParameter(request, "precio"));
        
            Elemento elemento = new Elemento();
            elemento.setPrecio(precio);
            elemento.setCancionId(Long.valueOf(id));
            elemento.setDescripcion(nombre);
            
            ListaElementos le = new ListaElementos();
           
            if (request.getSession().getAttribute("carro") != null) {
                le = (ListaElementos) request.getSession().getAttribute("carro");
            } 
            
            
            le.getElementos().add(elemento);
            request.getSession().setAttribute("carro", le);
        }
        
        response.sendRedirect("/SongStock/canciones.jsp");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }


    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }

    // </editor-fold>

}
